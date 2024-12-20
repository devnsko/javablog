import IPost from "components/app/interfaces/Post";
import Link from "next/link";

interface PostCardProps {
    post: IPost;
}

export default function PostCard(props: PostCardProps) {
    const post = props.post;
    return (
        <>
        <Link href={`/posts/${post.id}`}>
            <div className="p-4 border rounded-md shadow-sm hover:shadow-md">
                <h2 className="text-lg font-bold">{post.title}</h2>
                <p className="text-gray-600 mt-2">{post.content}</p>
                <span>{post.author_name} | {post.updatedAt} 
                    {post.createdAt!=post.updatedAt ? 'updated' : 'published'}</span>
                <span> {post.likes.length} likes</span>
            </div>
        </Link>
        </>
      );
}