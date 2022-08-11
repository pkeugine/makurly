import React from "react";
import "./style.css";
import { useNavigate } from "react-router-dom";
function MainPage() {
  const navigate = useNavigate();

  const directSignUp = () => {
    navigate("/sign-up");
  };
  const directSignIn = () => {
    navigate("/sign-in");
  };
  return (
    <div className="main-page-container">
      <div>컬리 해커톤 1등팀</div>
      <button onClick={directSignUp}>sign up</button>
      <button onClick={directSignIn}>sign in</button>
    </div>
  );
}
export default MainPage;
