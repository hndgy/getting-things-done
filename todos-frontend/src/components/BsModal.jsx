import React from 'react'
import { Button, Modal } from 'react-bootstrap';

function BsModal({show, setShow, title, body, handleConfirm}) {

  return (
    <>
     

      <Modal show={show} onHide={()=>setShow(false)} animation={false}>
        <Modal.Header closeButton>
          <Modal.Title>{title} </Modal.Title>
        </Modal.Header>
        <Modal.Body> {body}</Modal.Body>
        <Modal.Footer>
          <Button variant="light" onClick={()=>setShow(false)}>
            Annuler
          </Button>
          <Button variant="dark" onClick={handleConfirm}>
            Continuer
          </Button>
        </Modal.Footer>
      </Modal>
    </>
  )
}

export default BsModal