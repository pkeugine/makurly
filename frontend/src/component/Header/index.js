import React from "react";
import "./style.css";
import isLogin from "../../utils/isLogin";
import { useNavigate } from "react-router-dom";

function Header(logoutFunc) {
  const navigate = useNavigate();
  const logout = () => {
    window.localStorage.setItem("isLogin", false);
    window.localStorage.removeItem("user-id");
    window.localStorage.removeItem("user-name");
  };
  return (
    <div className="header">
      <div className="top">
        {isLogin() ? (
          <>
            <a href="/user-info" className="direct">
              회원가입
            </a>
            <div className="bar"></div>
            <button
              style={{
                backgroundColor: "white",
                border: "none",
                padding: "0px",
              }}
              className="direct"
              onClick={logout}
            >
              로그아웃
            </button>
          </>
        ) : (
          <>
            <a href="/sign-up" className="direct">
              회원가입
            </a>
            <div className="bar"></div>
            <a href="/sign-in" className="direct">
              로그인
            </a>
          </>
        )}
      </div>
      <div className="title">
        {" "}
        <img
          src="./img/kurly.png"
          width="100"
          height="50"
          className="logo"
          alt="logo"
        ></img>
      </div>
    </div>
  );
}
export default Header;
