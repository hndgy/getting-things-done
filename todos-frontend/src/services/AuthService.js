const API_URL = "http://localhost:8080"

class AuthService{
    login(username, password){
        return fetch(API_URL + "/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded;charset=UTF-8",
            },
            body: `username=${username}&password=${password}`
        }).then(resp => resp.json()).then(
            (data) => {
                if (data['access-token']) {
                    localStorage.setItem("accessToken", data['access-token']);
                    localStorage.setItem("refreshToken", data['refresh-token']);
                    return true;
                }
                return false;
            }
        ).catch(
            (error) => {
                return false;
            }
        );
    }

    isAuthenticated(){
        return localStorage.getItem("accessToken");
    }

    logout(){
        localStorage.removeItem("accessToken");
        localStorage.removeItem("refreshToken");
    }
}

export default new AuthService();