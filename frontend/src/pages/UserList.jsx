import { useEffect, useState } from "react";

const UserList = () => {
    const [users, setUsers] = useState([]);
    const [followingIds, setFollowingIds] = useState([]);
    const [currentUserId, setCurrentUserId] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem("token");
        if (!token) return;

        const fetchUsers = async () => {
            const res = await fetch("http://localhost:8080/api/user/list", {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            if (res.ok) {
                const data = await res.json();
                console.log("유저 목록:", data);
                setUsers(data);
            }
        };

        const fetchFollowing = async () => {
            const res = await fetch("http://localhost:8080/api/follow/following", {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            if (res.ok) {
                const data = await res.json();
                setFollowingIds(data);
            }
        };

        const fetchCurrentUser = async () => {
            const res = await fetch("http://localhost:8080/api/user/me", {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            if (res.ok) {
                const data = await res.json();
                setCurrentUserId(data.userId);
            }
        };

        fetchUsers();
        fetchFollowing();
        fetchCurrentUser();
    }, []);

    const handleFollowToggle = async (targetUserId) => {
        const token = localStorage.getItem("token");
        if (!token) return;

        const isFollowing = followingIds.includes(targetUserId);

        const res = await fetch(
            `http://localhost:8080/api/follow/${isFollowing ? "unfollow-by-userid" : "follow"}?followingId=${targetUserId}`,
            {
                method: isFollowing ? "DELETE" : "POST",
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            }
        );

        if (res.ok) {
            setFollowingIds((prev) =>
                isFollowing
                    ? prev.filter((id) => id !== targetUserId)
                    : [...prev, targetUserId]
            );
        }
    };

    return (
        <div>
            <h2>전체 사용자 목록</h2>
            <ul>
                {users
                    .filter((user) => user.userId !== currentUserId)
                    .map((user) => (
                        <li key={user.userId}>
                            {user.username}{" "}
                            <button onClick={() => handleFollowToggle(user.userId)}>
                                {followingIds.includes(user.userId) ? "팔로우 취소" : "팔로우"}
                            </button>
                        </li>
                    ))}
            </ul>
        </div>
    );
};

export default UserList;