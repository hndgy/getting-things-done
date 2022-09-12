import { useState } from "react";
import { BrowserRouter } from "react-router-dom";
import Header from "./components/Header";
import Login from "./pages/Login";
import Pages from "./pages/Pages";
import AuthService from "./services/AuthService";

function App() {
  const [isAuth, setIsAuth] = useState(AuthService.isAuthenticated());

    if(!isAuth){
        return <Login setIsAuth={setIsAuth}/>; 
    }

  return (
    <div className="App">
      <Header setIsAuth={setIsAuth}/>
      <BrowserRouter>
        <Pages/>
      </BrowserRouter>
    </div>
  );
}

export default App;
