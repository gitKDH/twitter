import { useState } from "react";

const CreatePost = () => {
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const [imgUrl, setImgUrl] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem("token");

        if (!token) {
            alert("로그인이 필요합니다.");
            window.location.href = "/login";
            return;
        }

        const res = await fetch("http://localhost:8080/api/post/create", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify({
                title,
                content,
                imgUrl,
            }),
        });

        if (res.ok) {
            alert("게시물 작성 완료!");
            window.location.href = "/home";
        } else {
            alert("작성 실패");
        }
    };

    return (
        <div>
            <h2>게시물 작성</h2>
            <form onSubmit={handleSubmit}>
                <input
                    type="text"
                    placeholder="제목"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                /><br />
                <textarea
                    placeholder="내용"
                    value={content}
                    onChange={(e) => setContent(e.target.value)}
                /><br />
                <input
                    type="text"
                    placeholder="이미지 URL"
                    value={imgUrl}
                    onChange={(e) => setImgUrl(e.target.value)}
                /><br />
                <button type="submit">작성</button>
            </form>
        </div>
    );
};

export default CreatePost;