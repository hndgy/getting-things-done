import React, { useEffect, useState } from 'react'
import { Badge, Button, Card, Form, InputGroup, ListGroup, Spinner } from 'react-bootstrap';
import FormCheckInput from 'react-bootstrap/esm/FormCheckInput';
import { Link } from 'react-router-dom';
import ProjectService from '../services/ProjectService';
import TodoService from '../services/TodoService';

function BoiteReception() {

    const [listEntrants, setListEntrants] = useState();
    const [listProjects, setListProjects] = useState([])

    useEffect(()=>{
        TodoService.getInbox().then((res) => res.json()).then(
            (data) => {
                setListEntrants(data);
            }
        );
        ProjectService.getAllProjects().then(resp=>resp.json())
              .then( data => setListProjects(data))
    }, []);
    

    const [newInput, setNewInput] = useState("");
    const [priorityCheck, setPriorityCheck] = useState(false);
    const [dueDateInput, setDueDateInput] = useState("");
    const handleAddEntrant = () => {

        if (newInput !== ""){
            TodoService.createTodo(
                {
                    title : newInput,
                    dueDate : dueDateInput,
                    priority : priorityCheck
                }
            ).then(res => res.json())
            .then(res => {
                console.log(res)
                const newListEntrants = [...listEntrants];
                newListEntrants.push(res);
                setListEntrants(newListEntrants);
                resetAddForm();
            })
        }
    }
    const resetAddForm = () => {
        setNewInput("")
        setDueDateInput("")
        setPriorityCheck(false);
    }

    const handleInputChange = (e) => {
        setNewInput(e.target.value)
    }
    const handleKeyUp = (e) => {
        if (e.keyCode === 13) {
            handleAddEntrant();
        }
    }

    const handleSuppress = (id) => {
        TodoService.deleteTodo(id).then(() => setListEntrants(listEntrants.filter(x => x.id !== id)))
        .catch(err => console.log(err))
    }
    const handleChangeProject = (evt,idTodo) => {
        setTimeout(() => {
            TodoService.updateTodoProject(idTodo, evt.target.value)
            .then(() => {
                setListEntrants(listEntrants.filter(x => x.id !== idTodo))
            }).catch((err) => alert(err));
        },1500)

    }

    const handleChangeComplete = (ev, id) => {
        const checked = ev.target.checked;
        TodoService.complete(id, checked).then(()=>{
            if(checked){
                setListEntrants(listEntrants.filter(x => x.id !== id))
            }
        });
    }


  return (
    <>
     <InputGroup className="mb-3">
            <Form.Control placeholder="💡 Une idée ? Un truc à faire ?"
            value={newInput}
            type="text" onChange={handleInputChange}
            onKeyUp={handleKeyUp} />

        <Form.Control value={dueDateInput} type="datetime-local" onChange={(e) => setDueDateInput(e.target.value)}/>
       
       <Button onClick={handleAddEntrant} variant="success">+</Button>
      </InputGroup>
      <Form.Check checked={priorityCheck} 
                onChange={(e) => setPriorityCheck(e.target.checked)} label="Prioritaire"/>
        <Card className="mt-5">
        <Card.Header>
            <Card.Title>Boîte de réception 📨 </Card.Title>
            <Link  to="/todos" className="btn btn-sm btn-outline-dark"> Tâches à faire </Link>
        </Card.Header>

            <Card.Body>
            <ListGroup>
            {listEntrants && listEntrants.map(
                (e) => {
                    return (
                        <ListGroup.Item key={e.id}>
                            <FormCheckInput onChange={(ev) => handleChangeComplete(ev,e.id)}/>
                        {e.priority &&
                            <Badge bg='danger' >Priorité</Badge>
                        }
                        <b className=""> {e.title} </b>
                        {e.dueDate && `, le ${e.dueDate}`}
                        <Form.Select onChange={(event) => handleChangeProject(event, e.id)}>
                    <option value="-1">Pas de projet</option>
                        {listProjects.length > 0 && listProjects.map(
                            (p) => {
                                return <option key={e.id+"-"+p.id} value={p.id}>{p.name}</option>
                            }
                        )}
                </Form.Select>

                            <Button variant="outline-secondary" href='#' onClick={() => {handleSuppress(e.id)}} className="btn-sm" >supprimer</Button>
                        </ListGroup.Item>
                    );
                }
            )}
            {!listEntrants &&
                        <ListGroup.Item className="text-center">
                            <Spinner animation='border'/>
                        </ListGroup.Item>
                }

        </ListGroup>
            </Card.Body>
      
        </Card>
      

    </>
  )
}

export default BoiteReception