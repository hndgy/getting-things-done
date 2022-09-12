package fr.hndgy.todos.configurations;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import fr.hndgy.todos.entities.AppUser;
import fr.hndgy.todos.entities.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil {
    private static final long EPIRATION_DURATION = 24 * 60 * 60 * 1000;
    public static final String ISSUER = "Todo";
    private static final long REFRESH_EPIRATION_DURATION = 180 * 60 * 60 * 1000;
    public static final String ROLES_CLAIMS_NAME = "roles";

    @Value("${app.jwt.secret}")
    private String secretKey;


    public String generateAccessToken(AppUser user){

        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuer(ISSUER)
                .withClaim(ROLES_CLAIMS_NAME,
                        user.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.toList()))
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + EPIRATION_DURATION))
                .sign(Algorithm.HMAC256(secretKey));
    }
    public String generateRefreshToken(AppUser user){

        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_EPIRATION_DURATION))
                .sign(Algorithm.HMAC256(secretKey));
    }

    public AppUser verifyAccessToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        String username = decodedJWT.getSubject();
        AppUser user = new AppUser();
        user.setEmail(username);

        for (var roleName : decodedJWT.getClaim(ROLES_CLAIMS_NAME).asArray(String.class)){
            user.getRoles().add(new UserRole(null, roleName));
        }
        return user;

    }

    public String getUsername(String token) {
        return null;
    }
}
