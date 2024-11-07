import Link from "next/link";

export function Footer() {
    return (
        <footer className="py-8 text-center text-gray-600 border-t border-gray-100">
            <p className="text-sm">
                Built with ❤️ by the {' '}
                <Link href="/contributors" className="text-blue-600 hover:text-blue-800 transition-colors">
                    MRT Buddy Contributors
                </Link>{' | '}
                <span>
                    Website by{' '}
                    <a
                        href="https://irfanhasan.vercel.app"
                        target="_blank"
                        rel="noopener noreferrer"
                        className="text-blue-600 hover:text-blue-800 transition-colors"
                    >
                        Irfan
                    </a>
                </span>
            </p>
            <br></br>
            <p className="text-sm">Copyright &copy; 2024 Aniruddha Adhikary</p>
        </footer>
    );
};