import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import Layout from "../components/Layout";
import { getBikeAnalytics } from "../services/analyticsService";

function BikeAnalyticsPage() {
  const { bikeId } = useParams();

  const [analytics, setAnalytics] = useState(null);

  useEffect(() => {
    loadAnalytics();
  }, []);

  const loadAnalytics = async () => {
    try {
      const response = await getBikeAnalytics(bikeId);

      setAnalytics(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  if (!analytics) {
    return (
      <Layout>
        <h1
          className="text-5xl"
          style={{
            fontFamily: "Playfair Display",
          }}
        >
          Loading...
        </h1>
      </Layout>
    );
  }

  return (
    <Layout>
      <div
        className="
        mb-12
        "
      >
        <h1
          className="
          text-5xl
          mb-3
          "
          style={{
            fontFamily: "Playfair Display",
          }}
        >
          {analytics.bikeName}
        </h1>

        <p
          className="
          text-[#6B6762]
          "
        >
          Ride Analytics
        </p>
      </div>

      <div
        className="
        grid
        md:grid-cols-2
        lg:grid-cols-3
        gap-6
        "
      >
        <div className="bg-white rounded-3xl p-8 border border-[#E8E1D7] shadow-sm">
          <h2 className="text-4xl font-semibold">{analytics.totalRides}</h2>

          <p className="text-[#6B6762]">Total Rides</p>
        </div>

        <div className="bg-white rounded-3xl p-8 border border-[#E8E1D7] shadow-sm">
          <h2 className="text-4xl font-semibold">{analytics.totalDistance}</h2>

          <p className="text-[#6B6762]">Distance (km)</p>
        </div>

        <div className="bg-white rounded-3xl p-8 border border-[#E8E1D7] shadow-sm">
          <h2 className="text-4xl font-semibold">
            {analytics.averageRideDistance?.toFixed(1)}
          </h2>

          <p className="text-[#6B6762]">Average Ride</p>
        </div>

        <div className="bg-white rounded-3xl p-8 border border-[#E8E1D7] shadow-sm">
          <h2 className="text-4xl font-semibold">{analytics.longestRide}</h2>

          <p className="text-[#6B6762]">Longest Ride</p>
        </div>

        <div className="bg-white rounded-3xl p-8 border border-[#E8E1D7] shadow-sm">
          <h2 className="text-4xl font-semibold">{analytics.shortestRide}</h2>

          <p className="text-[#6B6762]">Shortest Ride</p>
        </div>

        <div className="bg-white rounded-3xl p-8 border border-[#E8E1D7] shadow-sm">
          <h2 className="text-lg font-medium">{analytics.firstRideDate}</h2>

          <p className="text-[#6B6762]">First Ride</p>

          <div className="h-4" />

          <h2 className="text-lg font-medium">{analytics.latestRideDate}</h2>

          <p className="text-[#6B6762]">Latest Ride</p>
        </div>
      </div>
    </Layout>
  );
}

export default BikeAnalyticsPage;
