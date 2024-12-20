'use client'

import axios from "axios";
import ISignin from "components/app/interfaces/Signin";
import IToken from "components/app/interfaces/Token";
import Layout from "components/components/Layout";
import apiClient from "components/utils/axios";
import Link from "next/link";
import { useRouter } from "next/navigation";
import { useState } from "react";


export default function Signup() {
    const router = useRouter();
    const [state, setState] = useState<ISignin>({
        name: '',
        password: ''
    });

    function handleChange(e: React.ChangeEvent<HTMLInputElement>) {
        setState({...state, [e.target.name]: e.target.value});
    }

    async function handleSubmit(e: React.FormEvent<HTMLFormElement>) {
        try {
            e.preventDefault();
            const response = await axios.post(
                `${process.env.NEXT_PUBLIC_API_URL}/auth/login`,
                state,
                {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                }
            );
            const data: IToken = response.data;
            localStorage.setItem('token', JSON.stringify(data.token));
            alert(`Success, ${data.name} logged in`);
            router.push('/');
        } catch (error) {
            alert('Something went wrong');
        }
    }

    return (
        <Layout>
        <div>
            <h1>Login</h1>
            
            <form onSubmit={handleSubmit}>
                <div>

                <label htmlFor="name">Name</label>
                <input type="text" name="name" value={state.name} onChange={handleChange} />
                </div>
                <div>
                <label htmlFor="password">Password</label>
                <input type="password" name="password" value={state.password} onChange={handleChange} />
                </div>
                <button type="submit">Login</button>
            </form>
            <p>Don't have an account? <Link href="/signup">Sign up</Link></p>
        </div>
        </Layout>
    )
}