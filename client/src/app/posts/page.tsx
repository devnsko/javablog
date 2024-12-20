'use client'

import { useEffect, useState } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import LayoutAuthenticated from "components/components/LayoutAuthenticated";
import IPost from "../interfaces/Post";
import PostCard from "components/components/PostCard";
import apiClient from "components/utils/axios";


export default function Home() {
  const router = useRouter();
  const [posts, setPosts] = useState<IPost[]>([]);
  const params = useSearchParams();
      
  useEffect(() => {
    apiClient.get(`http://localhost:8080/posts${params ? "?" + params.toString() : ""}`)
    .then((response) => response.data)
    .then((data: IPost[]) => setPosts(data));
  }, [params]);
  

  return (
    <>
    <LayoutAuthenticated >
      
    <div className="container">
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
        { posts ? posts.map((post) => (
          <PostCard post={post} key={post.id}/>
          )) : null }
      </div>
      </div>
      </LayoutAuthenticated>
        </>
  );
}
