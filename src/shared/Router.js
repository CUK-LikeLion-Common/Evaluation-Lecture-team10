import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Detail, Login, Main, Register, User, Write } from "../pages";
import Header from "./Header";
import Footer from "./Footer";

const Router = () => {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/write" element={<Write />} />
        <Route path="/:evaluationID" element={<Detail />} />
        <Route path="user/:id" element={<User />} />
      </Routes>
      <Footer />
    </BrowserRouter>
  );
};

export default Router;
