import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";


const PostDetail = () => {
    const navigate = useNavigate();
    const { postId } = useParams();
    const [post, setPost] = useState(null);
    const [liked, setLiked] = useState(false);
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
                setLiked(data);
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

    const fetchComments = async () => {
        const token = localStorage.getItem("token");

        const res = await fetch(`http://localhost:8080/api/comment/list?postId=${postId}`);
        if (res.ok) {
            const data = await res.json();

            const enrichedComments = await Promise.all(
                data.map(async (comment) => {
                    const countRes = await fetch(`http://localhost:8080/api/commentlike/count?commentId=${comment.commentId}`);
                    const likeCount = countRes.ok ? await countRes.json() : 0;

                    const likedRes = await fetch(`http://localhost:8080/api/commentlike/liked?commentId=${comment.commentId}`, {
                        headers: {
                            Authorization: `Bearer ${token}`,
                        },
                    });
                    const liked = likedRes.ok ? await likedRes.json() : false;

                    return {
                        ...comment,
                        likeCount,
                        liked,
                    };
                })
            );

            setComments(enrichedComments);
        }
    };

    const handleCommentSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem("token");

        const res = await fetch(
            `http://localhost:8080/api/comment/create?postId=${postId}&userId=8`, // 추후 userId는 토큰에서 추출
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
                body: JSON.stringify({ content: newComment }),
            }
        );

        if (res.ok) {
            setNewComment("");
            fetchComments();
        } else {
            alert("댓글 작성 실패");
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
            fetchComments();
        } else {
            alert("수정 실패");
        }
    };

    const handleDeleteComment = async (commentId) => {
        const token = localStorage.getItem("token");
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
            fetchComments();
        } else {
            alert("댓글 삭제 실패");
        }
    };

    const handleCommentLikeToggle = async (index) => {
        const token = localStorage.getItem("token");
        const comment = comments[index];

        if (!token || comment.liked === undefined) return;

        const url = comment.liked
            ? `http://localhost:8080/api/commentlike/unlikecomment?commentId=${comment.commentId}`
            : `http://localhost:8080/api/commentlike/addcommentlike?commentId=${comment.commentId}`;
        const method = comment.liked ? "DELETE" : "POST";

        const res = await fetch(url, {
            method,
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });

        if (res.ok) {
            const updated = [...comments];
            updated[index] = {
                ...comment,
                liked: !comment.liked,
                likeCount: comment.liked ? comment.likeCount - 1 : comment.likeCount + 1,
            };
            setComments(updated);
        }
    };

    const handleLikeToggle = async () => {
        const token = localStorage.getItem("token");

        if (!token) return;

        const url = liked
            ? `http://localhost:8080/api/postlike/unlikepost?postId=${postId}`
            : `http://localhost:8080/api/postlike/addpostlike?postId=${postId}`;
        const method = liked ? "DELETE" : "POST";

        const res = await fetch(url, {
            method,
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });

        if (res.ok) {
            setLiked(!liked);
            setLikeCount((prev) => prev + (liked ? -1 : 1));
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
            <button onClick={() => navigate(`/post/edit/${post.postId}`)}>수정</button>

            <h3>댓글</h3>
            {comments.length > 0 ? (
                <ul>
                    {comments.map((comment, index) => (
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
                                    <button type="button" onClick={() => setEditingCommentId(null)}>취소</button>
                                </form>
                            ) : (
                                <>
                                    {comment.content}
                                    <button onClick={() => handleEditClick(comment)}>수정</button>
                                    <button onClick={() => handleDeleteComment(comment.commentId)}>삭제</button>
                                </>
                            )}
                            <div>
                                ❤️ {comment.likeCount ?? 0}개
                                <button
                                    onClick={() => handleCommentLikeToggle(index)}
                                    disabled={comment.liked === undefined}
                                >
                                    {comment.liked ? "좋아요 취소" : "좋아요"}
                                </button>
                            </div>
                        </li>
                    ))}
                </ul>
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
            <button onClick={() => navigate("/home")}>
                목록으로 돌아가기
            </button>
        </div>
    );
};

export default PostDetail;