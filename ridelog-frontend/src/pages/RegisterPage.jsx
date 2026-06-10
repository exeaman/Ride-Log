import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";

import { register } from "../services/authService";
import toast from "react-hot-toast";

function RegisterPage() {
  const navigate = useNavigate();

  const [name, setName] = useState("");

  const [email, setEmail] = useState("");

  const [password, setPassword] = useState("");

  const handleRegister = async () => {
    try {
      await register({
        name,
        email,
        password,
      });

      toast.success("Account created successfully");

      navigate("/login");
    } catch (error) {
      console.error(error);

      toast.error(error.response?.data?.message || "Registration failed");
    }
  };

  return (
    <div
      className="
      min-h-screen
      bg-[#F6F4EF]
      flex
      items-center
      justify-center
      px-6
      "
    >
      <div
        className="
        w-full
        max-w-md
        bg-white
        rounded-3xl
        p-10
        border
        border-[#E8E1D7]
        shadow-sm
        "
      >
        <h1
          className="
          text-5xl
          mb-3
          text-center
          "
          style={{
            fontFamily: "Playfair Display",
          }}
        >
          RideLog
        </h1>

        <p
          className="
          text-center
          text-[#6B6762]
          mb-10
          "
        >
          Create your account
        </p>

        <div
          className="
          space-y-5
          "
        >
          <input
            className="
            w-full
            p-4
            rounded-2xl
            border
            border-[#E8E1D7]
            "
            placeholder="Full Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />

          <input
            className="
            w-full
            p-4
            rounded-2xl
            border
            border-[#E8E1D7]
            "
            type="email"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />

          <input
            className="
            w-full
            p-4
            rounded-2xl
            border
            border-[#E8E1D7]
            "
            type="password"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <button
            onClick={handleRegister}
            className="
            w-full
            p-4
            rounded-full
            bg-[#CBB99A]
            text-white
            "
          >
            Create Account
          </button>
        </div>

        <p
          className="
          text-center
          mt-8
          text-[#6B6762]
          "
        >
          Already have an account?{" "}
          <Link
            to="/login"
            className="
            text-[#2E2A26]
            font-medium
            "
          >
            Login
          </Link>
        </p>
      </div>
    </div>
  );
}

export default RegisterPage;
