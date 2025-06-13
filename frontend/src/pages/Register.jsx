// src/pages/Register.jsx
import React, { useState } from "react";

const Register = () => {
    const [username, setUsername] = useState("");
    const [email, setEmail] = useState("");
    const [bio,setBio] = useState("");
    const [password, setPassword] = useState("");

    const handleRegister = async (e) => {
        e.preventDefault();

        const res = await fetch("http://localhost:8080/api/user/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({
                username,
                email,
                bio,
                password,
            }),
        });

        if (res.ok) {
            alert("회원가입 성공!");
        } else {
            alert("회원가입 실패!");
        }
    };

    return (
        <div>
            <h2>회원가입</h2>
            <form onSubmit={handleRegister}>
                <input
                    type="text"
                    placeholder="이름"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                /><br />
                <input
                    type="email"
                    placeholder="이메일"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                /><br />
                <input
                    type="text"
                    placeholder="자기소개"
                    value={bio}
                    onChange={(e) => setBio(e.target.value)}
                /><br />
                <input
                    type="password"
                    placeholder="비밀번호"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                /><br />
                <button type="submit">회원가입</button>
            </form>
        </div>
    );
};

export default Register;