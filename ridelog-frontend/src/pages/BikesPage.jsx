import { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import ConfirmModal from "../components/ConfirmModal";
import Layout from "../components/Layout";

import { getMyBikes, deleteBike } from "../services/bikeService";

import toast from "react-hot-toast";

function BikesPage() {
  const [bikes, setBikes] = useState([]);

  const [bikeToDelete, setBikeToDelete] = useState(null);

  useEffect(() => {
    loadBikes();
  }, []);

  const loadBikes = async () => {
    try {
      const response = await getMyBikes();

      setBikes(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleDelete = async (bikeId) => {
    try {
      await deleteBike(bikeId);

      toast.success("Bike deleted successfully");

      loadBikes();
    } catch (error) {
      console.error(error);

      toast.error(error.response?.data?.message || "Failed to delete bike");
    }
  };

  if (bikes.length === 0) {
    return (
      <Layout>
        <div
          className="
          bg-white
          rounded-3xl
          p-12
          text-center
          border
          border-[#E8E1D7]
          "
        >
          <h2
            className="text-3xl mb-3"
            style={{
              fontFamily: "Playfair Display",
            }}
          >
            No Bikes Yet
          </h2>

          <p
            className="
            text-[#6B6762]
            mb-6
            "
          >
            Start your riding journey.
          </p>

          <Link
            to="/bikes/new"
            className="
            px-6
            py-3
            rounded-full
            bg-[#CBB99A]
            text-white
            "
          >
            Add Your First Bike
          </Link>
        </div>
      </Layout>
    );
  }

  return (
    <Layout>
      <h1
        className="
        text-5xl
        mb-12
        "
        style={{
          fontFamily: "Playfair Display",
        }}
      >
        My Bikes
      </h1>

      <div
        className="
        grid
        md:grid-cols-2
        lg:grid-cols-3
        gap-8
        "
      >
        {bikes.map((bike) => (
          <div
            key={bike.id}
            className="
            bg-white
            rounded-3xl
            p-8
            shadow-sm
            border
            border-[#E8E1D7]

            transition-all
            duration-300

            hover:-translate-y-1
            hover:shadow-md
            "
          >
            <h2
              className="
              text-3xl
              mb-2
              "
              style={{
                fontFamily: "Playfair Display",
              }}
            >
              {bike.brand}
            </h2>

            <p
              className="
              text-[#6B6762]
              mb-8
              "
            >
              {bike.model}
            </p>

            <div
              className="
              flex
              gap-4
              "
            >
              <Link
                to={`/bikes/${bike.id}`}
                className="
                px-5
                py-3
                rounded-full
                bg-[#CBB99A]
                text-white
                "
              >
                View Bike
              </Link>

              <button
                onClick={() => setBikeToDelete(bike.id)}
                className="
                px-5
                py-3
                rounded-full
                border
                border-[#E8E1D7]
                "
              >
                Delete
              </button>
            </div>
          </div>
        ))}
      </div>

      <ConfirmModal
        isOpen={bikeToDelete !== null}
        title="Delete Bike"
        message="This bike will be removed from your garage."
        onCancel={() => setBikeToDelete(null)}
        onConfirm={() => {
          handleDelete(bikeToDelete);

          setBikeToDelete(null);
        }}
      />
    </Layout>
  );
}

export default BikesPage;
