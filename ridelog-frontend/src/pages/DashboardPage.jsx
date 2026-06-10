import { Link } from "react-router-dom";

import { useAuth } from "../context/AuthContext";

function DashboardPage() {
  const { user, logout } = useAuth();

  return (
    <div>
      <h1>Welcome {user?.name}</h1>

      <p>{user?.email}</p>

      <br />

      <Link to="/bikes">My Bikes</Link>

      <br />

      <Link to="/bikes/new">Add Bike</Link>

      <br />
      <br />

      <button onClick={logout}>Logout</button>
    </div>
  );
}

export default DashboardPage;
