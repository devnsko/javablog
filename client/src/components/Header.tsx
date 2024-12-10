import IUser from "components/app/interfaces/User";

interface HeaderProps {
    user: IUser | null;
  }

export default function Header(props: HeaderProps) {
    return (
        <header className="bg-blue-600 text-white py-4 shadow-md">
            <div className="container mx-auto flex justify-between items-center">
                <h1 className="text-xl font-bold">My Blog</h1>
                <div className="flex items-center space-x-4">
                    <span className="font-medium">{props?.user?.name ?? 'Guest'}</span>
                </div>
            </div>
        </header>
    )
}