import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import { createRide } from "../services/rideService";

function CreateRidePage() {
  const { bikeId } = useParams();

  const navigate = useNavigate();

  const [title, setTitle] = useState("");

  const [rideDate, setRideDate] = useState("");

  const [startOdometer, setStartOdometer] = useState("");

  const [endOdometer, setEndOdometer] = useState("");

  const [notes, setNotes] = useState("");

  const handleSubmit = async () => {
    try {
      await createRide({
        bikeId: Number(bikeId),

        title,

        rideDate,

        startOdometer: Number(startOdometer),

        endOdometer: Number(endOdometer),

        notes,
      });

      alert("Ride created successfully");

      navigate(`/bikes/${bikeId}`);
    } catch (error) {
      console.error(error);

      alert(error.response?.data?.message || "Failed to create ride");
    }
  };

  return (
    <div>
      <h1>Add Ride</h1>

      <input
        placeholder="Ride Title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />

      <br />

      <input
        type="date"
        value={rideDate}
        onChange={(e) => setRideDate(e.target.value)}
      />

      <br />

      <input
        type="number"
        placeholder="Start Odometer"
        value={startOdometer}
        onChange={(e) => setStartOdometer(e.target.value)}
      />

      <br />

      <input
        type="number"
        placeholder="End Odometer"
        value={endOdometer}
        onChange={(e) => setEndOdometer(e.target.value)}
      />

      <br />

      <textarea
        placeholder="Notes"
        value={notes}
        onChange={(e) => setNotes(e.target.value)}
      />

      <br />

      <button onClick={handleSubmit}>Add Ride</button>
    </div>
  );
}

export default CreateRidePage;
