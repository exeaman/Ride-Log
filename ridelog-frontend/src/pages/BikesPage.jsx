import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import { getMyBikes } from "../services/bikeService";

function BikesPage() {
  const [bikes, setBikes] = useState([]);
  const loadBikes = async () => {
    try {
      const response = await getMyBikes();

      setBikes(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  useEffect(() => {
    loadBikes();
  }, []);

  return (
    <div>
      <h1>My Bikes</h1>

      {bikes.map((bike) => (
        <div key={bike.id}>
          <Link to={`/bikes/${bike.id}`}>
            <h3>{bike.brand}</h3>

            <p>{bike.model}</p>
          </Link>
        </div>
      ))}
    </div>
  );
}

export default BikesPage;
