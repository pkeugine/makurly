import React from "react";
import "./style.css";
import { useNavigate } from "react-router-dom";
import isLogin from "../../utils/isLogin";

function MainPage() {
  const navigate = useNavigate();

  const directSignUp = () => {
    navigate("/sign-up");
  };
  const directSignIn = () => {
    navigate("/sign-in");
  };
  const directUserInfo = () => {
    navigate("/user-info");
  };
  const logout = () => {
    window.localStorage.setItem("isLogin", false);
    window.localStorage.removeItem("user-id");
    window.localStorage.removeItem("user-name");
    navigate("/");
  };

  return (
    <div className="main-page-container">
      <div>컬리 해커톤 1등팀</div>
      {isLogin() ? (
        <>
          {" "}
          <button onClick={directUserInfo}>user-info</button>
          <button onClick={logout}>logout</button>
        </>
      ) : (
        <>
          {" "}
          <button onClick={directSignUp}>sign up</button>
          <button onClick={directSignIn}>sign in</button>
        </>
      )}
    </div>
  );
}
export default MainPage;
