'use client'
import Layout from "components/components/Layout";

export default function Page() {

    // func to clear storage
    async function clearStorage() {
        localStorage.clear();
        alert('Storage cleared!');
    }
        
    
    return (
        <Layout>
        <div>
            <h1>Login</h1>
            <p>storage cleared!</p>
            <button onClick={clearStorage}>Clear Storage</button>
        </div>
        </Layout>
    )
}