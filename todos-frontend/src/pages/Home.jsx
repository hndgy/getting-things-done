import React from 'react'
import { Container} from 'react-bootstrap';
import BoiteReception from '../components/BoiteReception';

function Home() {
  /*const [listTodos, setListTodos] = useState([]);*/

  return (
    <Container>
      <BoiteReception/>
    </Container>
  );


  /*useEffect(()=>{
    TodoService.getAllTodos()
    .then((res) => {
      return res.json()
    })
    .then((data) => {
      console.log(data)
      setListTodos(data);
    })
  },[]);

  return (
    <Container>
      {listTodos && listTodos.map(todo => (
        <ListGroup key={todo.id}>
          <ListGroup.Item>
            <div className='fw-bold'>
              {todo.title}
            </div>
            <div>
              {todo.dateCreated}
            </div>
          </ListGroup.Item>
        </ListGroup>
        )
      )}
    </Container>
  )*/
}

export default Home