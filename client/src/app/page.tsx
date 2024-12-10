'use client'

import IPost from "./interfaces/Post";
import { useEffect, useState } from "react";
import PostCard from "components/components/PostCard";
import { useSearchParams } from "next/navigation";
import IUser from "./interfaces/User";
import Header from "components/components/Header";


export default function Home() {
  const [posts, setPosts] = useState<IPost[]>([]);
  const [user, setUser] = useState<IUser>();
  const params = useSearchParams();
      
  useEffect(() => {
    fetch(`http://localhost:8080/posts?${params}`)
    .then((response) => response.json())
    .then((data) => setPosts(data));
    fetch(`http://localhost:8080/myuser`)
    .then((response) => response.json())
    .then((data) => setUser(data))
  }, [params]);


  return (
    <>
    <Header user={user ?? null}/>
    <div className="container">
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        {posts.map((post) => (
          <PostCard 
          key={post.id}
          id={post.id}
          title={post.title}
          content={post.content}
          author_name={post.author_name}
          author_id={post.author_id}
          createdAt={post.createdAt}
          updatedAt={post.updatedAt}
          />
        ))}
      </div>
      </div>
        </>
  );
}
