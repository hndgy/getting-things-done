import React, { useEffect } from 'react'
import { useState } from 'react'
import { Badge, Button, ListGroup, Row, Spinner } from 'react-bootstrap'
import FormCheckInput from 'react-bootstrap/esm/FormCheckInput';
import ProjectService from '../services/ProjectService';
import TodoService from '../services/TodoService';

function Projects() {



    const [listTodosAsap, setListTodosAsap] = useState([]);
    const [listProjects, setListProjects] = useState([])

    useEffect(() => {
      TodoService.getAsap().then(r => r.json()).then(data => {
        setListTodosAsap(data);
        console.log(data)
      });
      ProjectService.getAllProjects().then(resp=>resp.json())
        .then( data => setListProjects(data))
    }, [])


    const handleSuppress = (id) => {
        TodoService.deleteTodo(id).then(() => setListTodosAsap(listTodosAsap.filter(x => x.id !== id)))
        .catch(err => console.log(err))
    }


    const handleChangeComplete = (ev, id) => {
        const checked = ev.target.checked;
        TodoService.complete(id, checked).then(()=>{
            if(checked){
                setTimeout(
                    () => {
                        setListTodosAsap(listTodosAsap.filter(x => x.id !== id))
                    },
                    1000
                );
            }
        });
    }

  return (
    <Row className="mt-4">
        <h1>Projets</h1>
        {listProjects.map( p =>
            <div key={p.id}>
                <h3>
                    {p.name}
                </h3>

                    <ListGroup>
            {listTodosAsap && listTodosAsap.filter( x => x.projectId === p.id).map(
                (e) => {
                    return (
                        <ListGroup.Item key={e.id}>
                            <FormCheckInput onChange={(ev) => handleChangeComplete(ev,e.id)} />
                        {e.priority &&
                            <Badge bg='danger' >Priorité</Badge>
                        }
                        <b className=""> {e.title} </b>
                        {e.dueDate && `, le ${e.dueDate}`}

                            <Button variant="outline-secondary" href='#' onClick={() => {handleSuppress(e.id)}} className="btn-sm" >supprimer</Button>
                        </ListGroup.Item>
                    );
                }
            )}
            {!listTodosAsap &&
                        <ListGroup.Item className="text-center">
                            <Spinner animation='border'/>
                        </ListGroup.Item>
                }
            </ListGroup>
            </div>
        )}
    </Row>
  )
}

export default Projects