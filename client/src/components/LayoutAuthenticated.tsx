import IUser from "components/app/interfaces/User";
import apiClient from "components/utils/axios";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import Header from "./Header";

export default function LayoutAuthenticated({ children }: { children: React.ReactNode }) {
    const [profile, setProfile] = useState<IUser | null>(null);
    const router = useRouter();

    useEffect(() => {
        const fetchProfile = async () => {
            try {
                const response = await apiClient.get(`${process.env.NEXT_PUBLIC_API_URL}/users/me`);
                setProfile(response.data);
            } catch (error) {
                router.push('/login');
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
        <Header user={profile}/> 
        <main className="p-4">
            {children}
        </main>
        </div>
    )
}