import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

const PostDetail = () => {
    const { postId } = useParams();
    const [post, setPost] = useState(null);
    const [liked, setLiked] = useState(false); // 좋아요 상태
    const [likeCount, setLikeCount] = useState(0);
    const [comments, setComments] = useState([]);
    const [newComment, setNewComment] = useState("");
    const [editingCommentId, setEditingCommentId] = useState(null);
    const [editedContent, setEditedContent] = useState("");

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
        fetchComments();
    }, [postId]);

    const handleCommentSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem("token");

        if (!token) return;

        const res = await fetch(
            `http://localhost:8080/api/comment/create?postId=${postId}&userId=8`, // 여기서 userId는 나중에 백엔드에서 토큰으로 처리할 수도 있어!
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify({
                    content: newComment,
                }),
            }
        );

        if (res.ok) {
            setNewComment("");
            fetchComments();
        } else {
            alert("댓글 작성 실패");
        }
    };

    const handleLikeToggle = async () => {
        const token = localStorage.getItem("token");

        if (!token) return;

        if (liked) {
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

    const handleEditClick = (comment) => {
        setEditingCommentId(comment.commentId);
        setEditedContent(comment.content);
    };

    const handleEditSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem("token");

        const res = await fetch(`http://localhost:8080/api/comment/update?commentId=${editingCommentId}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
                Authorization: `Bearer ${token}`,
            },
            body: JSON.stringify({ content: editedContent }),
        });

        if (res.ok) {
            setEditingCommentId(null);
            setEditedContent("");
            fetchComments(); // 댓글 새로고침
        } else {
            alert("수정 실패");
        }
    };

    const handleDeleteComment = async (commentId) => {
        const token = localStorage.getItem("token");
        if (!token) return;

        const confirmed = window.confirm("댓글을 삭제하시겠습니까?");
        if (!confirmed) return;

        const res = await fetch(`http://localhost:8080/api/comment/delete?commentId=${commentId}`, {
            method: "DELETE",
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });

        if (res.ok) {
            alert("댓글이 삭제되었습니다.");
            fetchComments(); // 삭제 후 다시 댓글 목록 불러오기
        } else {
            alert("댓글 삭제 실패");
        }
    };

    const fetchComments = async () => {
        const res = await fetch(`http://localhost:8080/api/comment/list?postId=${postId}`);
        if (res.ok) {
            const data = await res.json();
            setComments(data);
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

            {comments.length > 0 ? (
                <div>
                    <h3>댓글</h3>
                    <ul>
                        {comments.map((comment) => (
                            <li key={comment.commentId}>
                                <strong>{comment.username}</strong>:
                                {editingCommentId === comment.commentId ? (
                                    <form onSubmit={handleEditSubmit} style={{ display: "inline" }}>
                                        <input
                                            type="text"
                                            value={editedContent}
                                            onChange={(e) => setEditedContent(e.target.value)}
                                        />
                                        <button type="submit">저장</button>
                                        <button onClick={() => setEditingCommentId(null)}>취소</button>
                                    </form>
                                ) : (
                                    <>
                                        {comment.content}
                                        <button onClick={() => handleEditClick(comment)}>수정</button>
                                        <button onClick={() => handleDeleteComment(comment.commentId)}>삭제</button>
                                    </>
                                )}
                            </li>
                        ))}
                    </ul>
                </div>
            ) : (
                <p>댓글이 없습니다.</p>
            )}
            <form onSubmit={handleCommentSubmit}>
                <input
                    type="text"
                    value={newComment}
                    onChange={(e) => setNewComment(e.target.value)}
                    placeholder="댓글을 입력하세요"
                    required
                />
                <button type="submit">작성</button>
            </form>
        </div>
    );


};


export default PostDetail;