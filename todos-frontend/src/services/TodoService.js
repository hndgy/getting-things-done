import authHeader from "../utils/auth-header";

const API_URL = "http://localhost:8080/api/v1"

class TodoService{
    getAllTodos(){
        return fetch(`${API_URL}/todo/`, {
            method: "GET",
            headers: authHeader()
        });
    }

    getPlanned(){
        return fetch(`${API_URL}/todo/planned`, {
            method: "GET",
            headers: authHeader()
        });
    }

    getInbox(){
        return fetch(`${API_URL}/todo/inbox`, {
            method: "GET",
            headers: authHeader()
        });
    }

    getAsap(){
        return fetch(`${API_URL}/todo/asap`, {
            method: "GET",
            headers: authHeader()
        });
    }
    
    createTodo(todo){
        console.log(todo)
        return fetch(`${API_URL}/todo/`, {
            method: "POST",
            headers: authHeader(),
            body: JSON.stringify(todo)
        });
    }

    deleteTodo(id){
        return fetch(`${API_URL}/todo/${id}`, {
            method: "DELETE",
            headers: authHeader()
        });
    }

    updateTodoProject(id, idProject){
        return fetch(`${API_URL}/todo/${id}/assignProject`,{
            method: 'PUT',
            headers : authHeader(),
            body : JSON.stringify({idProject : idProject})
        })
    }  
    
    complete(id, isCompleted){
        return fetch(`${API_URL}/todo/${id}/complete`,{
            method: 'PUT',
            headers : authHeader(),
            body : JSON.stringify({isCompleted : isCompleted})
        })
    }

}
export default new TodoService();