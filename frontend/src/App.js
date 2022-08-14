import "./App.css";
import { Routes, Route } from "react-router-dom";
import MainPage from "./page/MainPage";
import RegisterPage from "./page/RegisterPage";
import SignInPage from "./page/SignInPage";
import UserInfoPage from "./page/UserInfoPage";
import Header from "./component/Header";
import Footer from "./component/Footer";
function App() {
  return (
    <div className="App">
      <Header logoutFunc></Header>
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/sign-up" element={<RegisterPage />} />
        <Route path="/sign-in" element={<SignInPage />} />
        <Route path="/user-info" element={<UserInfoPage />} />
      </Routes>
      <Footer></Footer>
    </div>
  );
}

export default App;
