import React from 'react'
import { Card, Form } from 'react-bootstrap'

function TodoDetails({todo, listProjects}) {

  return (
    <Card className="mb-3" key={todo.id}>
        <Card.Header>
            <Card.Title>
                <Form.Control type='text'
                value={todo.title} />
            </Card.Title>
        </Card.Header>
        <Card.Body>
            <Card.Text>
                <Form.Control
                type="textarea"
                value={todo.description}/>
                <Form.Select>
                    <option value="-1">Pas de projet</option>
                    {listProjects.length > 0 && listProjects.map(
                        (p) => {
                            return <option value={p.id}>{p.name}</option>
                        }
                    )}
                </Form.Select>
            </Card.Text>
        </Card.Body>
    </Card>
  )
}

export default TodoDetails