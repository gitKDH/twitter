import { BrowserRouter as Router, Routes, Route } from 'react-router-dom'
import Login from './pages/Login'
import Register from './pages/Register'
import Home from "./pages/Home.jsx";
import CreatePost from "./pages/CreatePost.jsx";
import PostDetail from "./pages/PostDetail.jsx";
import EditPost from "./pages/EditPost.jsx";
import UserList from "./pages/UserList.jsx";
import MyProfile from "./pages/MyProfile.jsx";

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
                <Route path="/post/edit/:postId" element={<EditPost />} />
                <Route path="/users" element={<UserList />} />
                <Route path="/me" element={<MyProfile />} />
            </Routes>
        </Router>
    );
}

export default App;