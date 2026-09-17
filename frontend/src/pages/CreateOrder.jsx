import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { apiFetch } from "../api/apiFetch";

function CreateOrder() {

    const [productId, setProductId] = useState("");
    const [quantity, setQuantity] = useState("1");

    const navigate = useNavigate();

    const createOrder = async (e) => {
        e.preventDefault();

        // 沒有 token，直接返回登入頁
        const token = localStorage.getItem("token");

        if (!token) {
            navigate("/", { replace: true });
            return;
        }

        try {
            const response = await apiFetch(
                "http://localhost:8080/api/orders",
                {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify({
                        items: [
                            {
                                productId: Number(productId),
                                quantity: Number(quantity)
                            }
                        ]
                    })
                }
            );

            const text = await response.text();

            if (!response.ok) {
                throw new Error(text);
            }

            alert(text);

            setProductId("");
            setQuantity("1");

        } catch (error) {

            // apiFetch 已處理 JWT 失效與跳轉
            // 避免額外顯示「登入已過期」的 alert
            if (error.message === "登入已過期，請重新登入") {
                return;
            }

            alert(error.message);
        }
    };

    return (
        <div className="container mt-4">

            <h2>建立訂單</h2>

            <form onSubmit={createOrder}>

                <div className="mb-3">
                    <label className="form-label">
                        商品 ID
                    </label>

                    <input
                        type="number"
                        className="form-control"
                        value={productId}
                        onChange={(e) =>
                            setProductId(e.target.value)
                        }
                        required
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label">
                        數量
                    </label>

                    <input
                        type="number"
                        className="form-control"
                        min="1"
                        value={quantity}
                        onChange={(e) =>
                            setQuantity(e.target.value)
                        }
                        required
                    />
                </div>

                <button
                    type="submit"
                    className="btn btn-primary me-2"
                >
                    建立訂單
                </button>

                <button
                    type="button"
                    className="btn btn-secondary"
                    onClick={() => navigate("/home")}
                >
                    回首頁
                </button>

            </form>

        </div>
    );
}

export default CreateOrder;