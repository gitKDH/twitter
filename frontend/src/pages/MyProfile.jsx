import { useEffect, useState } from "react";

const MyProfile = () => {
    const [user, setUser] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (!token) return;

        fetch("http://localhost:8080/api/user/me", {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
            .then((res) => {
                if (!res.ok) throw new Error("사용자 정보 불러오기 실패");
                return res.json();
            })
            .then((data) => setUser(data))
            .catch((err) => console.error(err));
    }, []);

    if (!user) return <p>불러오는 중...</p>;

    return (
        <div>
            <h2>내 프로필</h2>
            <p><strong>닉네임:</strong> {user.username}</p>
            <p><strong>자기소개:</strong> {user.bio}</p>
            <p><strong>이메일:</strong> {user.email}</p>
        </div>
    );
};

export default MyProfile;