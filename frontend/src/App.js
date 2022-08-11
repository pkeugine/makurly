import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import MainPage from "./page/MainPage";
import RegisterPage from "./page/RegisterPage";
import SignInPage from "./page/SignInPage";
import UserInfoPage from "./page/UserInfoPage";
function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<MainPage />} />
          <Route path="/sign-up" element={<RegisterPage />} />
          <Route path="/sign-in" element={<SignInPage />} />
          <Route path="/user-info" element={<UserInfoPage />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
