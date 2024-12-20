'use client'

import ICreatePost from "components/app/interfaces/CreatePost";
import apiClient from "components/utils/axios";
import { useRouter } from "next/navigation";
import { useState, ChangeEvent, FormEvent } from "react";

export default function NewPost() {
    const router = useRouter();
    const [formData, setFormData] = useState<ICreatePost>({ title: '', content: '' });
    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        try {
            const { status } = await apiClient.post('/posts', formData, {
                headers: { 'Content-Type': 'application/json' }
            });

            if (status >= 200 && status < 300) {
                alert('Post created successfully!');
                router.push('/posts');
            }
        } catch (error) {
            console.error('Error creating post:', error);
        }
    };

    const handleChange = ({ target: { name, value } }: ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        setFormData(prev => ({ ...prev, [name]: value }));
    };

    return (
        <div>
            <h1>New Post</h1>
            <p>This is the new post page.</p>

            <form onSubmit={handleSubmit}>
                {[
                    { id: 'title', type: 'text', Component: 'input' },
                    { id: 'content', type: undefined, Component: 'textarea' }
                ].map(({ id, type, Component }) => (
                    <div key={id}>
                        <label htmlFor={id}>{id.charAt(0).toUpperCase() + id.slice(1)}</label>
                        <Component
                            type={type}
                            name={id}
                            id={id}
                            value={formData[id as keyof ICreatePost]}
                            onChange={handleChange}
                        />
                    </div>
                ))}
                <button type="submit">Submit</button>
            </form>
        </div>
    );
}