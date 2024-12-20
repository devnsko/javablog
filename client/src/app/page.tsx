'use client'

import Link from "next/link";
import Layout from "components/components/Layout";


export default function Home() {
  return (
  <>    
    <Layout>

    <div className="container">
      <div className="flex items-center justify-center min-h-screen">
        <div>
          <h1 className="text-6xl font-bold"></h1>
        <p>This is the home page.</p>
          </div>
        
        <div>
          <Link href="/posts">Posts</Link>
        </div>
      </div>
    </div>
    </Layout>
  </>
  );
}
