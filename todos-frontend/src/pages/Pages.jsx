import { Route, Routes } from 'react-router-dom'
import Home from './Home'
import Review from './Review'

function Pages() {

    return (
    <Routes>
        <Route path='/' element={<Home />}/>
        <Route path='/review' element={<Review />}/>
    </Routes>
    )
}

export default Pages