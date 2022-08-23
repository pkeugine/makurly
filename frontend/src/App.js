import "./App.css";
import { Routes, Route } from "react-router-dom";
import MainPage from "./page/MainPage";
import RegisterPage from "./page/RegisterPage";
import SignInPage from "./page/SignInPage";
import UserInfoPage from "./page/UserInfoPage";
import RecommendPage from "./page/RecomendPage";
import NotFoundPage from "./page/NotFoundPage";
import CartPage from "./page/CartPage";
import Header from "./component/Header";
import Footer from "./component/Footer";
import Banner from "./component/Banner";
import DashboardPage from "./page/DashboardPage";
function App() {
  return (
    <div className="App">
      <Banner></Banner>
      <Header logoutFunc></Header>
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/sign-up" element={<RegisterPage />} />
        <Route path="/sign-in" element={<SignInPage />} />
        <Route path="/user-info" element={<UserInfoPage />} />
        <Route path="/cart" element={<CartPage />} />
        <Route path="/recommend" element={<RecommendPage />} />
        <Route path="/dashboard" element={<DashboardPage />} />
        <Route path="/*" element={<NotFoundPage />} />
      </Routes>
      <Footer></Footer>
    </div>
  );
}

export default App;
