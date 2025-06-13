import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Profile from "./pages/Profile";
import PostDetail from "./pages/PostDetail";

const HomePage = () => <h1>홈</h1>;
const LoginPage = () => <h1>로그인</h1>;
const SignupPage = () => <h1>회원가입</h1>;

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/profile" element={<Profile />} />
                <Route path="/post/:postId" element={<PostDetail />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;