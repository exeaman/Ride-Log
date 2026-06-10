import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { getRidesByBike } from "../services/rideService";

function BikeDetailsPage() {
  const { bikeId } = useParams();

  const [rides, setRides] = useState([]);

  const loadRides = async () => {
    try {
      const response = await getRidesByBike(bikeId);

      setRides(response.data);
    } catch (error) {
      console.error(error);
    }
  };
  useEffect(() => {
    loadRides();
  }, []);

  return (
    <div>
      <h1>Bike Details</h1>

      <h2>
        Bike Id:
        {bikeId}
      </h2>
      <Link to={`/bikes/${bikeId}/rides/new`}>Add Ride</Link>

      <br />

      <Link to={`/bikes/${bikeId}/analytics`}>View Analytics</Link>

      <hr />

      <hr />
      <Link to={`/bikes/${bikeId}/rides/new`}>Add Ride</Link>

      <hr />
      <h2>Rides</h2>

      {rides.map((ride) => (
        <div key={ride.id}>
          <h3>{ride.title}</h3>

          <p>
            Distance:
            {ride.distance} km
          </p>

          <p>
            Date:
            {ride.rideDate}
          </p>
        </div>
      ))}
    </div>
  );
}

export default BikeDetailsPage;
