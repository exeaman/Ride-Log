import { useState } from "react";
import toast from "react-hot-toast";
import Layout from "../components/Layout";
import { createBike } from "../services/bikeService";

function CreateBikePage() {
  const [brand, setBrand] = useState("");

  const [model, setModel] = useState("");

  const [registrationNumber, setRegistrationNumber] = useState("");

  const [confirmRegistrationNumber, setConfirmRegistrationNumber] =
    useState("");

  const [chassisNumber, setChassisNumber] = useState("");

  const [year, setYear] = useState("");

  const [purchaseDate, setPurchaseDate] = useState("");

  const handleSubmit = async () => {
    try {
      await createBike({
        brand,
        model,
        registrationNumber,
        confirmRegistrationNumber,
        chassisNumber,

        year: Number(year),

        purchaseDate,
      });

      toast.success("Bike added successfully");
    } catch (error) {
      console.error(error);

      toast.error(error.response?.data?.message);
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
          Add Bike
        </h1>

        <div className="space-y-5">
          <input
            className="
            w-full
            p-4
            rounded-2xl
            border
            border-[#E8E1D7]
            "
            placeholder="Brand"
            value={brand}
            onChange={(e) => setBrand(e.target.value)}
          />

          <input
            className="
            w-full
            p-4
            rounded-2xl
            border
            border-[#E8E1D7]
            "
            placeholder="Model"
            value={model}
            onChange={(e) => setModel(e.target.value)}
          />

          <input
            className="
            w-full
            p-4
            rounded-2xl
            border
            border-[#E8E1D7]
            "
            placeholder="Registration Number"
            value={registrationNumber}
            onChange={(e) => setRegistrationNumber(e.target.value)}
          />

          <input
            className="
            w-full
            p-4
            rounded-2xl
            border
            border-[#E8E1D7]
            "
            placeholder="Confirm Registration Number"
            value={confirmRegistrationNumber}
            onChange={(e) => setConfirmRegistrationNumber(e.target.value)}
          />

          <input
            className="
            w-full
            p-4
            rounded-2xl
            border
            border-[#E8E1D7]
            "
            placeholder="Chassis Number"
            value={chassisNumber}
            onChange={(e) => setChassisNumber(e.target.value)}
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
            placeholder="Year"
            value={year}
            onChange={(e) => setYear(e.target.value)}
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
            value={purchaseDate}
            onChange={(e) => setPurchaseDate(e.target.value)}
          />

          <button
            onClick={handleSubmit}
            className="
            w-full
            p-4
            rounded-full
            bg-[#CBB99A]
            text-white
            "
          >
            Add Bike
          </button>
        </div>
      </div>
    </Layout>
  );
}

export default CreateBikePage;
