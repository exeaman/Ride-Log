import { useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

import Layout from "../components/Layout";
import { createRide } from "../services/rideService";
import toast from "react-hot-toast";

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

      toast.success("Ride created successfully");

      navigate(`/bikes/${bikeId}`);
    } catch (error) {
      console.error(error);

      toast.error(error.response?.data?.message || "Failed to create ride");
    }
  };

  return (
    <Layout>
      <div
        className="
        max-w-2xl
        mx-auto
        bg-white
        rounded-3xl
        p-10
        border
        border-[#E8E1D7]
        shadow-sm
        "
      >
        <h1
          className="
          text-5xl
          mb-10
          "
          style={{
            fontFamily: "Playfair Display",
          }}
        >
          Add Ride
        </h1>

        <div
          className="
          space-y-5
          "
        >
          <input
            className="
            w-full
            p-4
            rounded-2xl
            border
            border-[#E8E1D7]
            "
            placeholder="Ride Title"
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />

          <input
            className="
            w-full
            p-4
            rounded-2xl
            border
            border-[#E8E1D7]
            "
            type="date"
            value={rideDate}
            onChange={(e) => setRideDate(e.target.value)}
          />

          <input
            className="
            w-full
            p-4
            rounded-2xl
            border
            border-[#E8E1D7]
            "
            type="number"
            placeholder="Start Odometer"
            value={startOdometer}
            onChange={(e) => setStartOdometer(e.target.value)}
          />

          <input
            className="
            w-full
            p-4
            rounded-2xl
            border
            border-[#E8E1D7]
            "
            type="number"
            placeholder="End Odometer"
            value={endOdometer}
            onChange={(e) => setEndOdometer(e.target.value)}
          />

          <textarea
            className="
            w-full
            p-4
            rounded-2xl
            border
            border-[#E8E1D7]
            min-h-32
            resize-none
            "
            placeholder="Ride Notes"
            value={notes}
            onChange={(e) => setNotes(e.target.value)}
          />

          <button
            onClick={handleSubmit}
            className="
            w-full
            p-4
            rounded-full
            bg-[#CBB99A]
            text-white
            transition
            hover:opacity-90
            "
          >
            Create Ride
          </button>
        </div>
      </div>
    </Layout>
  );
}

export default CreateRidePage;
