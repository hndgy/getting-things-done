import React, { useEffect, useState } from 'react'
import { Container } from 'react-bootstrap'
import TodoDetails from '../components/TodoDetails'
import TodoService from '../services/TodoService'
import ProjectService from '../services/ProjectService'

function Review() {
    const [listTodos, setListTodos] = useState([])
    const [listProjects, setListProjects] = useState([])

    useEffect(()=>{
        TodoService.getInbox().then(resp=>resp.json())
            .then(data => setListTodos(data))
        ProjectService.getAllProjects().then(resp=>resp.json())
            .then( data => setListProjects(data))

        
    },[])

  return (
    <Container>
        {listProjects && listTodos.length > 0 && listTodos.map( todo => {
            return (
                <TodoDetails todo={todo} listProjects={listProjects} key={todo.id}/>
            )
        })}
    </Container>
  )
}

export default Review