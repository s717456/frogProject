import { Routes, Route } from "react-router-dom";

import Login from "./pages/Login";
import Products from "./pages/Products";
import Home from "./pages/Home";
import Orders  from "./pages/Orders";
import OrderDetail  from "./pages/OrderDetail";
import CreateOrder from "./pages/CreateOrder";
import FileUpload from "./pages/FileUpload";
import ProtectedRoute from "./pages/ProtectedRoute";

function App() {
  return (
    <Routes>
      <Route path="/" element={<Login />} />
      <Route element={<ProtectedRoute/>}>
        <Route path="/home" element={<Home />} />
        <Route path="/products" element={<Products />} />
        <Route path="/orders" element={<Orders/>}></Route>
        <Route path="/orders/:id" element={<OrderDetail />} />
        <Route path="/orders/create" element={<CreateOrder />}/>
        <Route path="/files/upload" element={<FileUpload/>}></Route>
      </Route>
    </Routes>
  );
}

export default App;