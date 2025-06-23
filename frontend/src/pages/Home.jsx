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
            {posts.map((post) => {
                console.log("post:", post);
                return (
                    <div key={post.postId}>
                        <a href={`/post/${post.postId}`}>{post.title}</a>
                        <p>{post.content}</p>
                        {post.imgUrl && <img src={post.imgUrl} alt="post-img" width="300"/>}
                    </div>
                );
            })}
        </div>
    );
}

export default Home;