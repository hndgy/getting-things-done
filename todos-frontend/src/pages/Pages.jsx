import { Route, Routes } from 'react-router-dom'
import Home from './Home'
import ListeTodo from './ListeTodo'
import NotFound from './NotFound'

function Pages() {

    return (
    <Routes>
        <Route path='/' element={<Home />}/>
        <Route path='/todos' element={<ListeTodo/>}/>
    </Routes>
    )
}

export default Pages