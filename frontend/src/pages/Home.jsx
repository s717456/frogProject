import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

function Home() {

    const navigate = useNavigate();

    useEffect(() => {

        const token = localStorage.getItem("token");

        if (!token) {
            navigate("/", { replace: true });
        }

    }, [navigate]);

    function goToProducts() {
        navigate("/products");
    }

    function handleLogout() {

        localStorage.removeItem("token");

        navigate("/", { replace: true });
    }

    return (
        <div>

            <h1>首頁</h1>

            <button onClick={goToProducts}>
                產品
            </button>
            <br />

            <button onClick={() => navigate("/orders")}>
                訂單
            </button>
            <br />

            <button onClick={()=>navigate("/orders/create")}>
                建立訂單
            </button>
            <br />

            <button onClick={()=>navigate("/files/upload")}>
                檔案上傳
            </button>
            <br/>

            <button onClick={handleLogout}>
                登出
            </button>

        </div>
    );
}

export default Home;