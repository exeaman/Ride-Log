import axiosClient from "../api/axiosClient";

export const getBikeAnalytics = async (bikeId) => {
  const response = await axiosClient.get(`/analytics/bike/${bikeId}`);

  return response.data;
};
