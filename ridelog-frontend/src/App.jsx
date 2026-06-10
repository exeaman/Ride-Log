import { BrowserRouter } from "react-router-dom";
import { Toaster } from "react-hot-toast";

import AppRoutes from "./routes/AppRoutes";

function App() {
    return (
        <BrowserRouter>

            <Toaster
                position="top-center"
                toastOptions={{
                    style: {
                        background: "#FFFFFF",
                        color: "#2E2A26",
                        border: "1px solid #E8E1D7"
                    }
                }}
            />

            <AppRoutes />

        </BrowserRouter>
    );
}

export default App;