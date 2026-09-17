import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { apiFetch } from "../api/apiFetch";

function Orders() {

    const [orders, setOrders] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {

    apiFetch("http://localhost:8080/api/orders", {
        headers: {
            "Accept": "application/json"
        }
    })
    .then(res => {

        if (!res.ok) {
            throw new Error(
                "取得訂單失敗，HTTP 狀態：" + res.status
            );
        }

        return res.json();
    })
    .then(data => {

        setOrders(data);

    })
    .catch(error => {
        console.error("取得訂單失敗：", error);
    });

}, []);

    return (
        <div className="container mt-4">

            <button
                className="btn btn-secondary mb-3"
                onClick={() => navigate("/home")}
            >
                首頁
            </button>

            <h2 className="mb-4">訂單列表</h2>

            <table className="table table-bordered table-hover">

                <thead className="table-dark">
                    <tr>
                        <th>訂單編號</th>
                        <th>使用者 ID</th>
                        <th>訂單時間</th>
                        <th>總金額</th>
                        <th>產品詳細</th>
                    </tr>
                </thead>

                <tbody>

                    {orders.map(order => (

                        <tr key={order.id}>
                            <td>{order.id}</td>
                            <td>{order.userId}</td>
                            <td>{order.orderDateTime}</td>
                            <td>{order.totalPrice}</td>

                            <td>
                                <button
                                    className="btn btn-primary"
                                    onClick={() =>
                                        navigate(`/orders/${order.id}`)
                                    }
                                >
                                    查看明細
                                </button>
                            </td>
                        </tr>

                    ))}

                </tbody>

            </table>

        </div>
    );
}

export default Orders;