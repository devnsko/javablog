import IUser from "components/app/interfaces/User";
import Link from "next/link";
import { JSX } from "react";

interface HeaderProps {
    user: IUser | null;
  }

export default function Header(props: HeaderProps) {
    let fetched = false;
    const user = props.user;
    fetched = true;
    let authBlock: JSX.Element;
    if (user) {
        authBlock = (
            <div className="relative group">
                <button className="flex items-center space-x-2 bg-blue-700 rounded-full px-4 py-2 hover:bg-blue-800 transition">
                    <span className="font-medium">{user.name ?? 'error'}</span>
                    <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M19 9l-7 7-7-7" />
                    </svg>
                </button>
                <div className="absolute right-0 mt-2 w-48 bg-white rounded-lg shadow-lg py-2 hidden group-hover:block">
                    <Link href="/profile" className="block px-4 py-2 text-gray-800 hover:bg-gray-100">Profile</Link>
                    <Link href="/settings" className="block px-4 py-2 text-gray-800 hover:bg-gray-100">Settings</Link>
                    <Link href="/logout" className="block px-4 py-2 text-red-600 hover:bg-gray-100">Logout</Link>
                </div>
            </div>);}
    else {
        authBlock = (
        <>
        <Link href="/login" className="bg-white text-blue-600 px-4 py-2 rounded-full font-medium hover:bg-gray-100 transition">
            Sign in
        </Link>
        <Link href="/signup" className="bg-blue-500 text-white px-4 py-2 rounded-full font-medium hover:bg-blue-400 transition">
            Sign up
        </Link>
    </>); }
    return (
        <header className="bg-blue-600 text-white py-4 shadow-md">
            <div className="container mx-auto flex justify-between items-center">
                <h1 className="text-xl font-bold"><Link href={"/"}>My Blog</Link></h1>
                <div className="flex items-center space-x-4">
                    { fetched ? authBlock : null }
                </div>
            </div>
        </header>

    )
}