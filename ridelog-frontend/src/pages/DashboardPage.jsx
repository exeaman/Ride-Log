import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import { useAuth } from "../context/AuthContext";
import { getUserAnalytics } from "../services/analyticsService";

function DashboardPage() {
  const { user, logout } = useAuth();

  const [analytics, setAnalytics] = useState(null);

  useEffect(() => {
    loadAnalytics();
  }, []);

  const loadAnalytics = async () => {
    try {
      const response = await getUserAnalytics(user.id);

      setAnalytics(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div>
      <h1>Welcome {user?.name}</h1>

      <p>{user?.email}</p>

      <hr />

      {analytics && (
        <div>
          <h2>Your Stats</h2>

          <p>Total Bikes: {analytics.totalBikes}</p>

          <p>Total Rides: {analytics.totalRides}</p>

          <p>
            Total Distance: {analytics.totalDistance}
            km
          </p>

          <p>
            Average Ride: {analytics.averageRideDistance}
            km
          </p>

          <p>
            Longest Ride: {analytics.longestRide}
            km
          </p>
        </div>
      )}

      <hr />

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
