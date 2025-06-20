import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const PostDetail = () => {
    const { id } = useParams(); // 게시물 ID 추출
    const [post, setPost] = useState(null);

    useEffect(() => {
        const token = localStorage.getItem("token");

        fetch(`http://localhost:8080/api/post/${id}`, {
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
        })
            .then((res) => {
                if (!res.ok) throw new Error("불러오기 실패");
                return res.json();
            })
            .then((data) => setPost(data))
            .catch((err) => console.error(err));
    }, [id]);

    if (!post) return <p>불러오는 중...</p>;

    return (
        <div>
            <h2>{post.title}</h2>
            <p>{post.content}</p>
            {post.imgUrl && <img src={post.imgUrl} alt="post-img" width="300" />}
            <p>작성일: {post.createdAt}</p>
        </div>
    );
};

export default PostDetail;