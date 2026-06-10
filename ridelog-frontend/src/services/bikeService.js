import axiosClient from "../api/axiosClient";

export const getMyBikes = async () => {
  const response = await axiosClient.get("/bikes/my");

  return response.data;
};

export const createBike = async (bikeData) => {
  const response = await axiosClient.post("/bikes", bikeData);

  return response.data;
};
export const deleteBike = async (bikeId) => {
  const response = await axiosClient.delete(`/bikes/${bikeId}`);

  return response.data;
};
