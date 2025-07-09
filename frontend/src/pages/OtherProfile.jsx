import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";

const OtherProfile = () => {
    const { userId } = useParams();
    const [user, setUser] = useState(null);

    useEffect(() => {
        const fetchUser = async () => {
            const token = localStorage.getItem("token");
            const res = await fetch(`http://localhost:8080/api/user/${userId}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            if (res.ok) {
                const data = await res.json();
                setUser(data);
            }
        };
        fetchUser();
    }, [userId]);

    if (!user) return <p>불러오는 중...</p>;

    return (
        <div>
            <h2>{user.username}님의 프로필</h2>
            <p>소개: {user.bio}</p>
            <p>이메일: {user.email}</p>
        </div>
    );
};

export default OtherProfile;