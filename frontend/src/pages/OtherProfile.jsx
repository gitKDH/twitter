import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const UserProfile = () => {
    const { userId } = useParams(); // URL의 사용자 ID
    const [user, setUser] = useState(null);
    const [isFollowing, setIsFollowing] = useState(false);

    const token = localStorage.getItem("token");

    useEffect(() => {
        fetch(`http://localhost:8080/api/user/${userId}`, {
            headers: { Authorization: `Bearer ${token}` },
        })
            .then(res => res.json())
            .then(data => setUser(data));

        fetch(`http://localhost:8080/api/follow/check?followingId=${userId}`, {
            headers: { Authorization: `Bearer ${token}` },
        })
            .then(res => res.json())
            .then(data => setIsFollowing(data));
    }, [userId]);

    const handleFollowToggle = async () => {
        const url = isFollowing
            ? `http://localhost:8080/api/follow/unfollow-by-userid?followingId=${userId}`
            : `http://localhost:8080/api/follow/follow?followingId=${userId}`;

        const method = isFollowing ? "DELETE" : "POST";

        const res = await fetch(url, {
            method,
            headers: { Authorization: `Bearer ${token}` },
        });

        if (res.ok) {
            setIsFollowing(!isFollowing);
        }
    };

    if (!user) return <p>불러오는 중...</p>;

    return (
        <div>
            <h2>{user.username}</h2>
            <p>{user.bio}</p>
            <p>{user.email}</p>

            {userId !== localStorage.getItem("userId") && (
                <button onClick={handleFollowToggle}>
                    {isFollowing ? "언팔로우" : "팔로우"}
                </button>
            )}
        </div>
    );
};

export default UserProfile;