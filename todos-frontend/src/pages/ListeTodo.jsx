import React from 'react'
import { Container } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import Projects from '../components/Projects'

function ListeTodo() {
  return (
    <Container>
        <Link className='btn btn-outline-dark' to="/">Retour</Link>
        <Projects/>
    </Container>
  )
}

export default ListeTodo