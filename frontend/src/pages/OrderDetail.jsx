import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { apiFetch } from "../api/apiFetch";

function OrderDetail() {

    const { id } = useParams();
    const [order, setOrder] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {

        // 切換訂單編號時，先清除上一筆資料
        setOrder(null);

        apiFetch(`http://localhost:8080/api/orders/${id}`, {
            headers: {
                "Accept": "application/json"
            }
        })
        .then(res => {

            if (!res.ok) {
                throw new Error(
                    "取得訂單明細失敗，HTTP 狀態：" + res.status
                );
            }

            return res.json();
        })
        .then(data => {

            setOrder(data);

        })
        .catch(error => {
            console.error("取得訂單明細失敗：", error);
        });

    }, [id]);

    if (!order) {
        return <p>載入中...</p>;
    }

    return (
        <div className="container mt-4">

            <button
                className="btn btn-secondary mb-3"
                onClick={() => navigate(-1)}
            >
                回上一頁
            </button>

            <h2>訂單明細</h2>

            <p>訂單編號：{order.id}</p>
            <p>使用者 ID：{order.userId}</p>
            <p>訂單時間：{order.orderDateTime}</p>
            <p>總金額：{order.totalPrice}</p>

            <table className="table table-bordered">

                <thead className="table-dark">
                    <tr>
                        <th>商品名稱</th>
                        <th>數量</th>
                        <th>單價</th>
                    </tr>
                </thead>

                <tbody>

                    {order.items?.map(item => (

                        <tr key={item.id}>
                            <td>{item.productName}</td>
                            <td>{item.quantity}</td>
                            <td>{item.unitPrice}</td>
                        </tr>

                    ))}

                </tbody>

            </table>

        </div>
    );
}

export default OrderDetail;