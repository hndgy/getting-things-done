import authHeader from "../utils/auth-header";

const API_URL = "http://localhost:8080/api/v1"


class ProjectService{
    getAllProjects(){
        return  fetch(`${API_URL}/project/`, {
            method: "GET",
            headers: authHeader()
        });
    }
}

export default new ProjectService();