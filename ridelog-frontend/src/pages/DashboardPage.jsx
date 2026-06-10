import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import Layout from "../components/Layout";
import { useAuth } from "../context/AuthContext";
import { getUserAnalytics } from "../services/analyticsService";

function DashboardPage() {
  const { user } = useAuth();

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
    <Layout>
      <h1 className="text-5xl mb-4">Welcome, {user?.name}</h1>

      <p className="text-[#6B6762] mb-12">{user?.email}</p>

      {analytics && (
        <div className="grid grid-cols-3 gap-6">
          <div className="bg-white rounded-3xl p-8 shadow-sm">
            <h2 className="text-4xl font-semibold">{analytics.totalBikes}</h2>

            <p>Total Bikes</p>
          </div>

          <div className="bg-white rounded-3xl p-8 shadow-sm">
            <h2 className="text-4xl font-semibold">{analytics.totalRides}</h2>

            <p>Total Rides</p>
          </div>

          <div className="bg-white rounded-3xl p-8 shadow-sm">
            <h2 className="text-4xl font-semibold">
              {analytics.totalDistance}
            </h2>

            <p>Total Distance (km)</p>
          </div>
        </div>
      )}
    </Layout>
  );
}

export default DashboardPage;
