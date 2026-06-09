function DashboardPage() {

    const token =
        localStorage.getItem("token");

    return (
        <div>
            <h1>Dashboard</h1>

            <p>
                Logged In:
                {token ? " Yes" : " No"}
            </p>
        </div>
    );
}

export default DashboardPage;