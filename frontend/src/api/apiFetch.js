export async function apiFetch(url, options = {}) {

    // 取得 JWT
    const token = localStorage.getItem("token");

    // 保留原本請求的 headers
    const headers = new Headers(options.headers);

    // 自動加入 JWT
    if (token) {
        headers.set("Authorization", "Bearer " + token);
    }

    // 發送 API 請求
    const response = await fetch(url, {
        ...options,
        headers: headers
    });

    // JWT 過期或驗證失敗
    if (response.status === 401) {

        // 清除無效 token
        localStorage.removeItem("token");

        // 返回登入頁，且不能按上一頁回來
        window.location.replace("/");

        // 中止後續資料處理
        throw new Error("登入已過期，請重新登入");
    }

    return response;
}