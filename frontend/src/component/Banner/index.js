import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import isLogin from "../../utils/isLogin";
import { AiOutlineClose } from "react-icons/ai";
import "./style.css";

function Banner() {
  const location = useLocation();
  const navigate = useNavigate();
  const [isVisible, setIsVisible] = useState(true);

  if (location.pathname === "/") {
    if (!isLogin()) {
      return (
        <>
          {isVisible ? (
            <div
              className="Banner button"
              onClick={() => {
                navigate("/sign-up");
              }}
            >
              <div className="inner">
                <div className="blank"></div>
                <div>회원가입을 해주세요!</div>
                <div
                  className="exit-container button"
                  onClick={(event) => {
                    event.stopPropagation();
                    setIsVisible(false);
                  }}
                >
                  {" "}
                  <AiOutlineClose size="20" className="exit"></AiOutlineClose>
                </div>
              </div>
            </div>
          ) : (
            <></>
          )}
        </>
      );
    } else {
      return (
        <>
          {isVisible ? (
            <div
              className="Banner button"
              onClick={() => {
                navigate("/cart");
              }}
            >
              <div className="inner">
                <div className="blank"></div>
                <div>장바구니에 상품을 넣고 장바구니를 확인하세요!</div>
                <div
                  className="exit-container button"
                  onClick={(event) => {
                    event.stopPropagation();
                    setIsVisible(false);
                  }}
                >
                  {" "}
                  <AiOutlineClose size="20" className="exit"></AiOutlineClose>
                </div>
              </div>
            </div>
          ) : (
            <></>
          )}
        </>
      );
    }
  } else if (location.pathname === "/sign-up") {
    return (
      <>
        {isVisible ? (
          <div
            className="Banner button"
            onClick={() => {
              navigate("/sign-in");
            }}
          >
            <div className="inner">
              <div className="blank"></div>
              <div>
                회원가입을 해주세요! 이미 가입된 회원이면 로그인을 해주세요!
              </div>
              <div
                className="exit-container button"
                onClick={(event) => {
                  event.stopPropagation();
                  setIsVisible(false);
                }}
              >
                {" "}
                <AiOutlineClose size="20" className="exit"></AiOutlineClose>
              </div>
            </div>
          </div>
        ) : (
          <></>
        )}
      </>
    );
  } else if (location.pathname === "/sign-in") {
    return (
      <>
        {isVisible ? (
          <div
            className="Banner button"
            onClick={() => {
              navigate("/sign-up");
            }}
          >
            <div className="inner">
              <div className="blank"></div>
              <div>로그인을 해주세요! 회원이 아니시면 회원가입을 해주세요!</div>
              <div
                className="exit-container button"
                onClick={(event) => {
                  event.stopPropagation();
                  setIsVisible(false);
                }}
              >
                {" "}
                <AiOutlineClose size="20" className="exit"></AiOutlineClose>
              </div>
            </div>
          </div>
        ) : (
          <></>
        )}
      </>
    );
  } else if (location.pathname === "/cart") {
    return (
      <>
        {isVisible ? (
          <div className="Banner button">
            <div className="inner">
              <div className="blank"></div>
              <div>상품을 구입하세요!</div>
              <div
                className="exit-container button"
                onClick={(event) => {
                  event.stopPropagation();
                  setIsVisible(false);
                }}
              >
                {" "}
                <AiOutlineClose size="20" className="exit"></AiOutlineClose>
              </div>
            </div>
          </div>
        ) : (
          <></>
        )}
      </>
    );
  } else {
    return (
      <>
        {isVisible ? (
          <div className="Banner button">
            <div className="inner">
              <div className="blank"></div>
              <div>추천을 확인하세요!</div>
              <div
                className="exit-container button"
                onClick={(event) => {
                  event.stopPropagation();
                  setIsVisible(false);
                }}
              >
                {" "}
                <AiOutlineClose size="20" className="exit"></AiOutlineClose>
              </div>
            </div>
          </div>
        ) : (
          <></>
        )}
      </>
    );
  }
}
export default Banner;
