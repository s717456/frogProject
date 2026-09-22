import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { apiFetch } from "../api/apiFetch";

function Products() {

    const [products, setProducts] = useState([]);
    const navigate = useNavigate();

    // 目前頁數
    const [currentPage, setCurrentPage] = useState(1);

    // 每頁顯示 10 筆
    const productsPerPage = 10;


    // 匯出訂單 PDF
    const downloadReport = () => {

        apiFetch("http://localhost:8080/api/orders/report", {
            headers: {
                "Accept": "application/pdf"
            }
        })
        .then(response => {

            if (!response.ok) {
                throw new Error(
                    "訂單 PDF 產生失敗，HTTP 狀態：" + response.status
                );
            }

            return response.blob();
        })
        .then(blob => {

            const url = window.URL.createObjectURL(blob);

            const a = document.createElement("a");

            a.href = url;
            a.download = "order_report.pdf";

            document.body.appendChild(a);

            a.click();

            a.remove();

            window.URL.revokeObjectURL(url);
        })
        .catch(error => {
            console.error("下載訂單 PDF 失敗：", error);
        });
    };


    // 匯出產品 PDF
    const downloadProductReport = () => {

        apiFetch("http://localhost:8080/api/products/report", {
            headers: {
                "Accept": "application/pdf"
            }
        })
        .then(response => {

            if (!response.ok) {
                throw new Error(
                    "PDF 產生失敗，HTTP 狀態：" + response.status
                );
            }

            return response.blob();
        })
        .then(blob => {

            const url = window.URL.createObjectURL(blob);

            const a = document.createElement("a");

            a.href = url;
            a.download = "product_report.pdf";

            document.body.appendChild(a);

            a.click();

            a.remove();

            window.URL.revokeObjectURL(url);
        })
        .catch(error => {
            console.error("下載產品 PDF 失敗：", error);
        });
    };


    // 取得產品
    useEffect(() => {

        apiFetch(
            `http://localhost:8080/api/products?page=${currentPage}&size=${productsPerPage}`,
            {
                headers: {
                    "Accept": "application/json"
                }
            }
        )
        .then(response => {

            if (!response.ok) {
                throw new Error(
                    "取得產品失敗，HTTP 狀態：" + response.status
                );
            }

            return response.json();
        })
        .then(data => {

            setProducts(data);

        })
        .catch(error => {
            console.error("取得產品失敗：", error);
        });

    }, [currentPage]);


    // 回首頁
    function goHome() {

        navigate("/home");
    }


    // 登出
    function handleLogout() {

        localStorage.removeItem("token");

        navigate("/", { replace: true });
    }


    return (
        <div className="container mt-4">

        <h1 className="text-center mb-4">
            3C 商品列表
        </h1>

        {/* Bootstrap 商品表格 */}
        <table className="table table-bordered table-striped table-hover text-center">

            <thead className="table-dark">
                <tr>
                    <th>商品編號</th>
                    <th>商品名稱</th>
                    <th>分類</th>
                    <th>價格</th>
                    <th>庫存</th>
                </tr>
            </thead>

            <tbody>
                {products.map(product => (
                    <tr key={product.id}>
                        <td>{product.id}</td>
                        <td>{product.name}</td>
                        <td>{product.category}</td>
                        <td>{product.price}</td>
                        <td>{product.stock}</td>
                    </tr>
                ))}
            </tbody>

        </table>

        <br />

        <button
            onClick={() => setCurrentPage(1)}
            disabled={currentPage === 1}>
            前10筆
        </button>

        <button
            onClick={() => setCurrentPage(2)}
            disabled={currentPage === 2}>
            後10筆
        </button>

        <br /><br />

        <button onClick={downloadReport}>
            匯出訂單 PDF
        </button>

        <br />

        <button onClick={downloadProductReport}>
            匯出產品 PDF
        </button>

        <br /><br />

        <button onClick={goHome}>
            回首頁
        </button>

        <br />

        <button onClick={handleLogout}>
            登出
        </button>

        </div>
    );
}

export default Products;