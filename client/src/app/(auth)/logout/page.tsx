'use client'

import { useRouter } from "next/navigation";

export default function Logout() {
    localStorage.removeItem('token');
    useRouter().push('/');
    return (
        <div>
            <h1>Logout</h1>
        </div>
    )
}