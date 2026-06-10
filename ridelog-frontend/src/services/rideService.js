import axiosClient from "../api/axiosClient";

export const getRidesByBike = async (bikeId) => {
  const response = await axiosClient.get(`/rides/bike/${bikeId}`);

  return response.data;
};

export const createRide = async (rideData) => {
  const response = await axiosClient.post("/rides", rideData);

  return response.data;
};
