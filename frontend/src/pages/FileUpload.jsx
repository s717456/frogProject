import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { apiFetch } from "../api/apiFetch";

function FileUpload() {

    const [file, setFile] = useState(null);
    const navigate = useNavigate();

    const uploadFile = async () => {

        if (!file) {
            alert("請選擇檔案");
            return;
        }

        const token = localStorage.getItem("token");

        if (!token) {
            navigate("/", { replace: true });
            return;
        }

        const formData = new FormData();
        formData.append("file", file);

        try {

            const response = await apiFetch(
                "http://localhost:8080/api/files/upload",
                {
                    method: "POST",
                    body: formData
                }
            );

            const text = await response.text();

            if (!response.ok) {
                throw new Error(text);
            }

            alert(text);

        } catch (error) {

            // JWT 失效已由 apiFetch 處理，避免重複跳出警告
            if (error.message === "登入已過期，請重新登入") {
                return;
            }

            alert(error.message);
        }
    };

    return (
        <div className="container mt-4">

            <h2>檔案上傳</h2>

            <div className="mb-3">
                <label className="form-label">
                    選擇檔案
                </label>

                <input
                    type="file"
                    className="form-control"
                    accept=".pdf,.doc,.docx,.png,.jpg,.jpeg"
                    onChange={(e) => setFile(e.target.files[0] ?? null)}
                />
            </div>

            <button
                className="btn btn-primary me-2"
                onClick={uploadFile}
            >
                上傳檔案
            </button>

            <button
                className="btn btn-secondary"
                onClick={() => navigate("/home")}
            >
                回首頁
            </button>

        </div>
    );
}

export default FileUpload;