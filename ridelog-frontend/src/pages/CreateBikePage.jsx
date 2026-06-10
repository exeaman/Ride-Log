import { useState } from "react";

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
      const response = await createBike({
        brand,
        model,
        registrationNumber,
        confirmRegistrationNumber,
        chassisNumber,
        year: Number(year),

        purchaseDate,
      });

      console.log(response);

      alert("Bike added successfully");
    } catch (error) {
      console.error(error);

      alert(error.response?.data?.message);
    }
  };

  return (
    <div>
      <h1>Add Bike</h1>

      <input
        placeholder="Brand"
        value={brand}
        onChange={(e) => setBrand(e.target.value)}
      />

      <br />

      <input
        placeholder="Model"
        value={model}
        onChange={(e) => setModel(e.target.value)}
      />

      <br />

      <input
        placeholder="Registration Number"
        value={registrationNumber}
        onChange={(e) => setRegistrationNumber(e.target.value)}
      />

      <br />

      <input
        placeholder="Confirm Registration Number"
        value={confirmRegistrationNumber}
        onChange={(e) => setConfirmRegistrationNumber(e.target.value)}
      />

      <br />

      <input
        placeholder="Chassis Number"
        value={chassisNumber}
        onChange={(e) => setChassisNumber(e.target.value)}
      />

      <br />

      <input
        type="number"
        placeholder="Year"
        value={year}
        onChange={(e) => setYear(e.target.value)}
      />

      <br />

      <input
        type="date"
        value={purchaseDate}
        onChange={(e) => setPurchaseDate(e.target.value)}
      />

      <br />

      <button onClick={handleSubmit}>Add Bike</button>
    </div>
  );
}

export default CreateBikePage;
