import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

const EditPost = () => {
    const { postId } = useParams();
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const [imgUrl, setImgUrl] = useState("");
    const navigate = useNavigate();

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
                setTitle(data.title);
                setContent(data.content);
                setImgUrl(data.imgUrl || "");
            } else {
                alert("게시물을 불러오지 못했습니다.");
            }
        };

        fetchPost();
    }, [postId]);

    const handleUpdate = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem("token");

        const res = await fetch(`http://localhost:8080/api/post/update?userId=8`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify({
                postId: Number(postId),
                title,
                content,
                imgUrl,
            }),
        });

        if (res.ok) {
            alert("수정 완료!");
            navigate(`/post/${postId}`);
        } else {
            alert("수정 실패");
        }
    };

    return (
        <div>
            <h2>게시물 수정</h2>
            <form onSubmit={handleUpdate}>
                <input
                    type="text"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    placeholder="제목"
                    required
                />
                <br />
                <textarea
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                    placeholder="내용"
                    required
                />
                <br />
                <input
                    type="text"
                    value={imgUrl}
                    onChange={(e) => setImgUrl(e.target.value)}
                    placeholder="이미지 URL"
                />
                <br />
                <button type="submit">수정 완료</button>
            </form>
        </div>
    );
};

export default EditPost;