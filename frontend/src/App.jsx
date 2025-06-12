import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

// 임시 컴포넌트 (테스트용으로 먼저 생성해둘 거야)
const HomePage = () => <h1>홈</h1>;
const LoginPage = () => <h1>로그인</h1>;
const SignupPage = () => <h1>회원가입</h1>;

function App() {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="/login" element={<LoginPage />} />
                <Route path="/signup" element={<SignupPage />} />
            </Routes>
        </Router>
    );
}

export default App;