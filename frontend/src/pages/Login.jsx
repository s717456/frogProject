import { useState } from "react";
import {useNavigate}from "react-router-dom";

function Login(){
    const [username,setUsername]=useState("");
    const [password,setPassword]=useState("");
    const [message,setMessage]=useState("");
    const navigate=useNavigate();
    function handleLogin(){
        fetch("http://localhost:8080/api/users/login",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify({
                username:username,
                password:password
            })
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            }

        throw new Error("登入失敗");
        })
        .then(data => {

            if (data.success) {

                localStorage.setItem("token", data.token);

                navigate("/home");

            } 
            else {

                 setMessage("帳號或密碼錯誤");
            }

        })
    }
    return(
        <div>
            <h1>會員登入</h1>
            <div>
                <label>帳號:</label>
                <input
                    type="text"
                    value={username}
                    onChange={e=>setUsername(e.target.value)}
                />
            </div>
            <br/>
            <div>
                <label>密碼:</label>
                <input
                    type="password"
                    value={password}
                    onChange={e=>setPassword(e.target.value)}
                />
            </div>
            <br/>
            <button onClick={handleLogin}>登入</button>
            <p>{message}</p>
        </div>
    );
}
export default Login;