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

  return (
    <div className="main-page-container">
      <div className="recommend-container">
        <div className="label-container">
          <div>추천1</div>
        </div>
        <div className="images-container">
          <div className="image-container">
            <img alt="default" className="img" src="./img/default.jpg"></img>
          </div>
          <div className="image-container">
            <img alt="default" className="img" src="./img/default.jpg"></img>
          </div>
          <div className="image-container">
            <img alt="default" className="img" src="./img/default.jpg"></img>
          </div>
          <div className="image-container">
            <img alt="default" className="img" src="./img/default.jpg"></img>
          </div>
        </div>
      </div>
      <div className="recommend-container">
        <div className="label-container">
          <div>추천2</div>
        </div>
        <div className="images-container">
          <div className="image-container">
            <img alt="default" className="img" src="./img/default.jpg"></img>
          </div>
          <div className="image-container">
            <img alt="default" className="img" src="./img/default.jpg"></img>
          </div>
          <div className="image-container">
            <img alt="default" className="img" src="./img/default.jpg"></img>
          </div>
          <div className="image-container">
            <img alt="default" className="img" src="./img/default.jpg"></img>
          </div>
        </div>
      </div>
    </div>
  );
}
export default MainPage;
