import axiosClient from "../api/axiosClient";

export const getBikeAnalytics = async (bikeId) => {
  const response = await axiosClient.get(`/analytics/bike/${bikeId}`);

  return response.data;
};

export const getUserAnalytics = async (userId) => {
  const response = await axiosClient.get(`/analytics/user/${userId}`);

  return response.data;
};
