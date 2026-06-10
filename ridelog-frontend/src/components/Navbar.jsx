import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { useState } from "react";
import ConfirmModal from "./ConfirmModal";

function Navbar() {
  const { user, logout } = useAuth();
  const [showLogoutModal, setShowLogoutModal] = useState(false);

  return (
    <nav
      className="
            h-24
            border-b
            border-[#E8E1D7]
            bg-[#FAF8F3]
            flex
            items-center
            justify-between
            px-16
            "
    >
      <Link
        to="/dashboard"
        className="
text-5xl
font-semibold
tracking-tight
"
        style={{
          fontFamily: "Playfair Display",
        }}
      >
        RideLog
      </Link>

      <div
        className="
  flex
  gap-4
  text-[#6B6762]
  "
      >
        <Link
          to="/dashboard"
          className="
    px-5
    py-3
    rounded-full
    transition-all
    duration-300
    hover:bg-[#CBB99A]
    hover:text-white
    "
        >
          Dashboard
        </Link>

        <Link
          to="/bikes"
          className="
    px-5
    py-3
    rounded-full
    transition-all
    duration-300
    hover:bg-[#CBB99A]
    hover:text-white
    "
        >
          My Bikes
        </Link>

        <Link
          to="/bikes/new"
          className="
    px-5
    py-3
    rounded-full
    transition-all
    duration-300
    hover:bg-[#CBB99A]
    hover:text-white
    "
        >
          Add Bike
        </Link>
      </div>

      <div
        className="
                flex
                items-center
                gap-6
                "
      >
        <span>{user?.name}</span>

        <button
          onClick={() => setShowLogoutModal(true)}
          className="
                    px-6
                    py-3
                    rounded-full
                    bg-[#CBB99A]
                    text-white
                    "
        >
          Logout
        </button>
      </div>
      <ConfirmModal
        isOpen={showLogoutModal}
        title="Logout"
        message="Are you sure you want to logout?"
        onCancel={() => setShowLogoutModal(false)}
        onConfirm={() => {
          logout();

          setShowLogoutModal(false);
        }}
      />
    </nav>
  );
}

export default Navbar;
