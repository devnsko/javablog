import { useEffect, useState } from "react";
import Header from "./Header";
import { useRouter } from "next/navigation";
import apiClient from "components/utils/axios";
import IUser from "components/app/interfaces/User";

export default function Layout({ children }: { children: React.ReactNode }) {
  const [profile, setProfile] = useState<IUser | null>(null);
    const router = useRouter();

    useEffect(() => {
        const fetchProfile = async () => {
            const response = await apiClient.get(`${process.env.NEXT_PUBLIC_API_URL}/users/me`);
            if(response.status!=200){ 
              setProfile(null);
            }
            else {
              setProfile(response.data);
            }

        };

        fetchProfile();
    }, [router]);

    function logout() {
        localStorage.removeItem('token');
        router.push('/');
    }

  return (
    <div className="flex flex-col min-h-screen">
      <Header user={profile ?? null}/> 
      <main className="p-4">
        {children}
      </main>
    </div>
  );
}