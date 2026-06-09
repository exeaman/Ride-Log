import axiosClient from "../api/axiosClient";

export const login = async (credentials) => {

    const response = await axiosClient.post(
        "/auth/login",
        credentials
    );

    return response.data;
};

export const register = async (userData) => {

    const response = await axiosClient.post(
        "/auth/register",
        userData
    );

    return response.data;
};