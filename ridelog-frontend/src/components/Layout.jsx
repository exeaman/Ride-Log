import Navbar from "./Navbar";

function Layout({ children }) {
  return (
    <div
      className="
            min-h-screen
            bg-[#F6F4EF]
            "
    >
      <Navbar />

      <main
        className="
                max-w-7xl
                mx-auto
                px-8
                py-12
                "
      >
        {children}
      </main>
    </div>
  );
}

export default Layout;
