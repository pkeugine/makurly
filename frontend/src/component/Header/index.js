import React from "react";
import "./style.css";
import isLogin from "../../utils/isLogin";
import { useNavigate } from "react-router-dom";
import {
  AiOutlineSearch,
  AiOutlineHeart,
  AiOutlineShoppingCart,
  AiOutlineMenu,
} from "react-icons/ai";
import { GrLocation } from "react-icons/gr";

function Header(logoutFunc) {
  const navigate = useNavigate();
  const logout = () => {
    window.localStorage.setItem("isLogin", false);
    window.localStorage.removeItem("user-id");
    window.localStorage.removeItem("user-name");
    navigate("/");
  };
  return (
    <>
      <div className="header">
        <div className="top">
          {isLogin() ? (
            <>
              <a href="/user-info" className="direct">
                구매내역
              </a>
              <div className="bar"></div>
              <button
                style={{
                  backgroundColor: "transparent",
                  border: "none",
                  padding: "0px",
                  fontSize: "12px",
                }}
                className="direct"
                onClick={logout}
              >
                로그아웃
              </button>
              <div className="bar"></div>
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
              <div className="bar"></div>
            </>
          )}
          <a
            href="/dashboard"
            style={{ color: "rgb(95,0,126", fontWeight: "bold" }}
            className="direct"
          >
            대시보드
          </a>
        </div>
        <div className="mid">
          <div className="title">
            {" "}
            <a href="/">
              <img
                src="./img/makurly.png"
                width="90"
                height="51"
                className="logo"
                alt="logo"
              ></img>
            </a>
            <div className="title-korean">맠컬리</div>
          </div>
          <div className="search-engine">
            <div className="input-container">
              <input
                className="input"
                placeholder="검색어를 입력하세요"
              ></input>
              <AiOutlineSearch
                size="20"
                color="rgb(95, 0, 128)"
              ></AiOutlineSearch>
            </div>
          </div>

          <div className="icons">
            <div className="icons-container">
              {" "}
              <GrLocation size="20"></GrLocation>
              <AiOutlineHeart size="20"></AiOutlineHeart>
              <AiOutlineShoppingCart
                className="button"
                onClick={() => {
                  navigate("/cart");
                }}
                size="20"
              ></AiOutlineShoppingCart>
            </div>
          </div>
        </div>
      </div>
      <div className="navbar-container">
        <div className="navbar">
          <div className="category">
            <AiOutlineMenu size="20" color="rgb(95, 0, 128)"></AiOutlineMenu>
            <div className="text">카테고리</div>
          </div>
          <div className="menu">
            <div className="menu-item">신상품</div>
            <div className="menu-item">베스트</div>
            <div className="menu-item">알뜰쇼핑</div>
            <div className="menu-item">특가/혜택</div>
          </div>
          <div className="blank"></div>
        </div>
      </div>
    </>
  );
}
export default Header;
