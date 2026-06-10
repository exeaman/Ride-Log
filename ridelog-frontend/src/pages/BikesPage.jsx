import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import { getMyBikes, deleteBike } from "../services/bikeService";

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


  const handleDelete = async (bikeId) => {
    const confirmed = window.confirm("Delete this bike?");

    if (!confirmed) {
      return;
    }

    try {
      await deleteBike(bikeId);

      loadBikes();
    } catch (error) {
      console.error(error);

      alert(error.response?.data?.message || "Failed to delete bike");
    }
  };

  return (
    <div>
      <h1>My Bikes</h1>

      {bikes.map((bike) => (
        <div key={bike.id}>
          <Link to={`/bikes/${bike.id}`}>
            <h3>{bike.brand}</h3>

            <p>{bike.model}</p>
          </Link>

          <button onClick={() => handleDelete(bike.id)}>Delete</button>

          <hr />
        </div>
      ))}
    </div>
  );
}

export default BikesPage;
