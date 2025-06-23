import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Login from './pages/Login'
import Register from './pages/Register'
import Home from "./pages/Home.jsx";
import CreatePost from "./pages/CreatePost.jsx";
import PostDetail from "./pages/PostDetail.jsx";

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<div>메인화면</div>} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/home" element={<Home />} />
                <Route path="/create" element={<CreatePost />} />
                <Route path="/post/:postId" element={<PostDetail />} />
            </Routes>
        </Router>
    );
}

export default App;