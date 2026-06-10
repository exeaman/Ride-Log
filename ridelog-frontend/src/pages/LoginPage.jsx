import { useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import toast from "react-hot-toast";
import { login } from "../services/authService";
import { useAuth } from "../context/AuthContext";

function LoginPage() {
  const [email, setEmail] = useState("");

  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const { login: loginUser } = useAuth();

  const handleLogin = async () => {
    try {
      const response = await login({
        email,
        password,
      });

      loginUser(response.data.token, response.data.user);

      navigate("/dashboard");
    } catch (error) {
      console.error(error);

      toast.error(error.response?.data?.message || "Login failed.");
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
          Welcome back
        </p>

        <div className="space-y-5">
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
            onClick={handleLogin}
            className="
            w-full
            p-4
            rounded-full
            bg-[#CBB99A]
            text-white
            "
          >
            Login
          </button>
        </div>

        <p
          className="
          text-center
          mt-8
          text-[#6B6762]
          "
        >
          New here?{" "}
          <Link
            to="/register"
            className="
            text-[#2E2A26]
            font-medium
            "
          >
            Create account
          </Link>
        </p>
      </div>
    </div>
  );
}

export default LoginPage;
