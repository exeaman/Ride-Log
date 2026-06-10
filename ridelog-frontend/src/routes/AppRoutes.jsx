import { Routes, Route, Navigate } from "react-router-dom";

import LoginPage from "../pages/LoginPage";
import RegisterPage from "../pages/RegisterPage";
import DashboardPage from "../pages/DashboardPage";
import BikesPage from "../pages/BikesPage";
import CreateBikePage from "../pages/CreateBikePage";
import BikeDetailsPage from "../pages/BikeDetailsPage";
import CreateRidePage from "../pages/CreateRidePage";
import BikeAnalyticsPage from "../pages/BikeAnalyticsPage";

import ProtectedRoute from "./ProtectedRoute";

function AppRoutes() {
  return (
    <Routes>
      <Route
        path="/bikes/:bikeId/analytics"
        element={
          <ProtectedRoute>
            <BikeAnalyticsPage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/bikes/:bikeId/rides/new"
        element={
          <ProtectedRoute>
            <CreateRidePage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/bikes/:bikeId"
        element={
          <ProtectedRoute>
            <BikeDetailsPage />
          </ProtectedRoute>
        }
      />
      <Route
        path="/bikes/new"
        element={
          <ProtectedRoute>
            <CreateBikePage />
          </ProtectedRoute>
        }
      />

      <Route path="/" element={<Navigate to="/login" />} />

      <Route
        path="/bikes"
        element={
          <ProtectedRoute>
            <BikesPage />
          </ProtectedRoute>
        }
      />

      <Route path="/login" element={<LoginPage />} />

      <Route path="/register" element={<RegisterPage />} />

      <Route
        path="/dashboard"
        element={
          <ProtectedRoute>
            <DashboardPage />
          </ProtectedRoute>
        }
      />
    </Routes>
  );
}

export default AppRoutes;
