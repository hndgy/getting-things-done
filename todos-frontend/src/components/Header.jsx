import React, { useState } from "react";
import { Container, Navbar, Button } from "react-bootstrap";
import AuthService from "../services/AuthService";
import BsModal from "./BsModal";

function Header({ setIsAuth }) {
  const [showModal, setShowModal] = useState(false);

  const handleLogout = () => {
    AuthService.logout();
    setIsAuth(false);
  };
  return (
    <>
      <Navbar bg="light" expend="lg" className="mb-3">
        <Container>
          <Navbar.Brand>
            <b>G</b><small>etting</small>
          <b>T</b><small>hings</small>
          <b>D</b><small>one</small>
          </Navbar.Brand>
          <Navbar.Toggle />
          <Navbar.Collapse className="d-flex justify-content-end">
            <Button
              onClick={() => setShowModal(true)}
              className="btn-sm "
              variant="dark"
            >Déconnecter</Button>
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <BsModal
        title="Déconnexion en cours..."
        body="Êtes-vous sûr de vouloir vous déconnecter ?"
        show={showModal}
        setShow={setShowModal}
        handleConfirm={handleLogout}
      />
    </>
  );
}

export default Header;
