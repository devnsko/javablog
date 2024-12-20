'use client'

import axios from "axios";
import ISignup from "components/app/interfaces/Signup";
import Layout from "components/components/Layout";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useState } from "react";


export default function Signup() {
    const router = useRouter();
    const [state, setState] = useState<ISignup>({
        name: '',
        email: '',
        password: ''
    });

    function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
        setState({...state, [e.target.name]: e.target.value});
    }

    async function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
        try {
            e.preventDefault();
            const response = await axios.post(`${process.env.NEXT_PUBLIC_API_URL}/auth/signup`,
                state,
                {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }
            );
            alert('Success');
            router.push('/login');

        } catch (error: any) {
            alert('Error:' + error);
            router.push('/signup');
        }
}

    return (
        <Layout>
        <div>
            <h1>Signup</h1>
            
            <form onSubmit={handleSubmit}>
                <label htmlFor="name">Name</label>
                <input type="text" name="name" value={state.name} onChange={handleChange} />
                <label htmlFor="email">Email</label>
                <input type="text" name="email" value={state.email} onChange={handleChange} />
                <label htmlFor="password">Password</label>
                <input type="password" name="password" value={state.password} onChange={handleChange} />
                <button type="submit">Submit</button>
            </form>
            <p>Already have an account? <Link href="/login">Login</Link></p>
        </div>
        </Layout>
    )
}