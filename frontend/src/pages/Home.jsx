import { useEffect, useState } from "react";

function Home() {
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        const fetchTimeline = async () => {
            const token = localStorage.getItem("token");

            if (!token) {
                console.error("토큰이 존재하지 않습니다.");
                return;
            }

            const res = await fetch("http://localhost:8080/api/post/timeline", {
                method: "GET",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`,
                },
            });

            if (res.ok) {
                const data = await res.json()
                console.log("받아온 게시물 목록:", data);
                setPosts(data);
            } else {
                console.error("타임라인 불러오기 실패");
            }
        };

        fetchTimeline();
    }, []);

    return (
        <div>
            <h2>타임라인</h2>
            {posts.map((post, index) => (
                <div key={post.postId ?? index}>
                    <h3>{post.title}</h3>
                    <p>{post.content}</p>
                </div>
            ))}
        </div>
    );
}

export default Home;