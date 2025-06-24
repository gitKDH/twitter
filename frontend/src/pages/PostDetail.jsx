import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const PostDetail = () => {
    const { postId } = useParams();
    const [post, setPost] = useState(null);
    const [liked, setLiked] = useState(false); // 좋아요 상태
    const [likeCount, setLikeCount] = useState(0);

    useEffect(() => {
        const token = localStorage.getItem("token");

        const fetchPost = async () => {
            const res = await fetch(`http://localhost:8080/api/post/${postId}`, {
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
            });

            if (res.ok) {
                const data = await res.json();
                setPost(data);
            }
        };

        const fetchLiked = async () => {
            const res = await fetch(`http://localhost:8080/api/postlike/liked?postId=${postId}`, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });
            if (res.ok) {
                const data = await res.json();
                setLiked(data); // true or false
            }
        };

        const fetchLikeCount = async () => {
            const res = await fetch(`http://localhost:8080/api/postlike/count?postId=${postId}`);
            if (res.ok) {
                const count = await res.json();
                setLikeCount(count);
            }
        };

        fetchPost();
        fetchLiked();
        fetchLikeCount();
    }, [postId]);

    const handleLikeToggle = async () => {
        const token = localStorage.getItem("token");

        if (!token) return;

        if (liked) {
            // 좋아요 취소
            const res = await fetch(
                `http://localhost:8080/api/postlike/unlikepost?postId=${postId}`,
                {
                    method: "DELETE",
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );
            if (res.ok) {
                setLiked(false);
                setLikeCount((prev) => prev - 1);
            }
        } else {
            // 좋아요 등록
            const res = await fetch(
                `http://localhost:8080/api/postlike/addpostlike?postId=${postId}`,
                {
                    method: "POST",
                    headers: {
                        Authorization: `Bearer ${token}`,
                    },
                }
            );
            if (res.ok) {
                setLiked(true);
                setLikeCount((prev) => prev + 1);
            }
        }
    };

    if (!post) return <p>불러오는 중...</p>;

    return (
        <div>
            <h2>{post.title}</h2>
            <p>{post.content}</p>
            {post.imgUrl && <img src={post.imgUrl} alt="post-img" width="300" />}
            <p>작성일: {post.createdAt}</p>
            <p>❤️ 좋아요 {likeCount}개</p>
            <button onClick={handleLikeToggle}>
                {liked ? "좋아요 취소" : "좋아요"}
            </button>
        </div>
    );
};

export default PostDetail;