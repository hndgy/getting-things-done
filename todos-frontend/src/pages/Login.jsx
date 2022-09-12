import React, { useState } from "react";
import AuthService from "../services/AuthService";
import PropTypes from "prop-types";
import { Button, Card, Container, Form, FormControl, Spinner } from "react-bootstrap";

function Login({ setIsAuth }) {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const onChangeEmail = (e) => {
    setEmail(e.target.value);
  };

  const onChangePassword = (e) => {
    setPassword(e.target.value);
  };

  const onSubmit = (e) => {
    e.preventDefault();
    setIsLoading(true)
    AuthService.login(email, password).then((isAuth) => {
      setIsAuth(isAuth);
      setIsLoading(false);
    });
  };

  return (
    <Container>
      <Card className="m-5">
        <Card.Body>
          <Form>
            <h1>Veuillez vous connecter</h1>
            <Form.Group className="mb-3">
              <FormControl
                className=""
                type="text"
                value={email}
                placeholder="Email"
                onChange={onChangeEmail}
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <FormControl
                type="password"
                value={password}
                placeholder="Password"
                onChange={onChangePassword}
              />
            </Form.Group>
            <Form.Group>
              <Button variant="outline-dark" onClick={onSubmit} with="100px">
                {!isLoading &&  ("Se connecter")}
                {isLoading &&  (<Spinner animation="border"  size="sm"/>)}
              </Button>
            <Form.Text> ou <a href="/">créer un compte</a></Form.Text>

            </Form.Group>

          </Form>
        </Card.Body>
      </Card>
    </Container>
  );
}
Login.propTypes = {
  setIsAuth: PropTypes.func.isRequired,
};

export default Login;
