'use client'

import { useEffect, useState } from "react";
import { useRouter, useSearchParams } from "next/navigation";
import LayoutAuthenticated from "components/components/LayoutAuthenticated";
import PostCard from "components/components/PostCard";
import apiClient from "components/utils/axios";
import IPost from "components/app/interfaces/Post";


export default function Home({ params }: { params: { id: string } }) {
  const router = useRouter();
  const [post, setPost] = useState<IPost>();
      
  useEffect(() => {
    apiClient.get(`http://localhost:8080/posts/${params.id}`)
    .then((response) => response.data)
    .then((data: IPost) => setPost(data));
  }, [params]);
  

  return (
    <>
        <LayoutAuthenticated >
            <div className="container">
                <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
                    { post ?
                    <PostCard post={post}/>
                        : null }
                </div>
            </div>
        </LayoutAuthenticated>
    </>
  );
}
