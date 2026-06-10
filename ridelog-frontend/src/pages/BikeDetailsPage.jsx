import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";

import Layout from "../components/Layout";
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
    <Layout>
      <div
        className="
                bg-white
                rounded-3xl
                p-10
                border
                border-[#E8E1D7]
                shadow-sm
                mb-12
                transition-all
duration-300

hover:-translate-y-1
hover:shadow-md
                "
      >
        <p
          className="
                    text-sm
                    text-[#6B6762]
                    mb-3
                    "
        >
          Motorcycle
        </p>

        <h1
          className="
                    text-5xl
                    mb-2
                    "
          style={{
            fontFamily: "Playfair Display",
          }}
        >
          Bike #{bikeId}
        </h1>

        <p
          className="
                    text-[#6B6762]
                    mb-8
                    "
        >
          Your riding companion
        </p>

        <div
          className="
                    flex
                    gap-4
                    "
        >
          <Link
            to={`/bikes/${bikeId}/rides/new`}
            className="
                        px-6
                        py-3
                        rounded-full
                        bg-[#CBB99A]
                        text-white
                        "
          >
            Add Ride
          </Link>

          <Link
            to={`/bikes/${bikeId}/analytics`}
            className="
                        px-6
                        py-3
                        rounded-full
                        border
                        border-[#E8E1D7]
                        "
          >
            View Analytics
          </Link>
        </div>
      </div>

      <h2
        className="
                text-4xl
                mb-8
                "
        style={{
          fontFamily: "Playfair Display",
        }}
      >
        Ride History
      </h2>

      {rides.length === 0 && (
        <div
          className="
                    bg-white
                    rounded-3xl
                    p-10
                    border
                    border-[#E8E1D7]
                    "
        >
          <p>No rides recorded yet.</p>
        </div>
      )}

      <div
        className="
                grid
                md:grid-cols-2
                gap-6
                "
      >
        {rides.map((ride) => (
          <div
            key={ride.id}
            className="
                        bg-white
                        rounded-3xl
                        p-8
                        border
                        border-[#E8E1D7]
                        shadow-sm
                        "
          >
            <h3
              className="
                            text-2xl
                            mb-4
                            "
              style={{
                fontFamily: "Playfair Display",
              }}
            >
              {ride.title}
            </h3>

            <p
              className="
                            text-[#6B6762]
                            mb-2
                            "
            >
              Distance
            </p>

            <p
              className="
                            text-3xl
                            mb-6
                            "
            >
              {ride.distance} km
            </p>

            <p
              className="
                            text-[#6B6762]
                            "
            >
              {ride.rideDate}
            </p>
          </div>
        ))}
      </div>
    </Layout>
  );
}

export default BikeDetailsPage;
