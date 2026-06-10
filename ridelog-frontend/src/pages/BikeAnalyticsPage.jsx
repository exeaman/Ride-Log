import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";

import { getBikeAnalytics } from "../services/analyticsService";

function BikeAnalyticsPage() {
  const { bikeId } = useParams();

  const [analytics, setAnalytics] = useState(null);
  const loadAnalytics = async () => {
    try {
      const response = await getBikeAnalytics(bikeId);

      setAnalytics(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    loadAnalytics();
  }, []);

  if (!analytics) {
    return <h1>Loading...</h1>;
  }

  return (
    <div>
      <h1>Bike Analytics</h1>

      <p>Bike: {analytics.bikeName}</p>

      <p>Total Rides: {analytics.totalRides}</p>

      <p>Total Distance: {analytics.totalDistance} km</p>

      <p>Average Ride Distance: {analytics.averageRideDistance} km</p>

      <p>Longest Ride: {analytics.longestRide} km</p>

      <p>Shortest Ride: {analytics.shortestRide} km</p>

      <p>First Ride: {analytics.firstRideDate}</p>

      <p>Latest Ride: {analytics.latestRideDate}</p>
    </div>
  );
}

export default BikeAnalyticsPage;
