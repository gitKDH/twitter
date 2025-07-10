import { useEffect, useState } from "react";
import { Card, CardContent, CardHeader } from "@/components/ui/card";
import { Avatar, AvatarFallback } from "@/components/ui/avatar";
import { Button } from "@/components/ui/button";
import { Separator } from "@/components/ui/separator";
import { Link } from "react-router-dom";

function Home() {
    const [posts, setPosts] = useState([]);

    useEffect(() => {
        const fetchTimeline = async () => {
            const token = localStorage.getItem("token");
            if (!token) return;

            const res = await fetch("http://localhost:8080/api/post/timeline", {
                headers: {
                    "Content-Type": "application/json",
                    Authorization: `Bearer ${token}`,
                },
            });

            if (res.ok) {
                const data = await res.json();
                setPosts(data);
            }
        };

        fetchTimeline();
    }, []);

    return (
        <div className="max-w-xl mx-auto mt-10 space-y-6">
            <h2 className="text-2xl font-bold mb-4">📸 타임라인</h2>

            {posts.map((post) => (
                <Card key={post.postId} className="rounded-2xl shadow-md">
                    <CardHeader className="flex flex-row items-center space-x-4">
                        <Avatar>
                            <AvatarFallback>{post.username?.charAt(0) ?? "U"}</AvatarFallback>
                        </Avatar>
                        <div>
                            <p className="font-medium">{post.username ?? "사용자"}</p>
                            <p className="text-sm text-gray-500">{post.createdAt}</p>
                        </div>
                    </CardHeader>

                    <CardContent className="space-y-4">
                        <div>
                            <p className="text-base font-semibold">{post.title}</p>
                            <p className="text-sm text-gray-800">{post.content}</p>
                            {post.imgUrl && (
                                <img
                                    src={post.imgUrl}
                                    alt="post-img"
                                    className="w-full h-auto mt-2 rounded-md"
                                />
                            )}
                        </div>

                        <Separator />

                        <div className="flex justify-between items-center">
                            <Button variant="outline" className="text-red-500">
                                ❤️ 좋아요
                            </Button>
                            <Link to={`/post/${post.postId}`} className="text-sm text-blue-500">
                                댓글 보기
                            </Link>
                        </div>
                    </CardContent>
                </Card>
            ))}
        </div>
    );
}

export default Home;