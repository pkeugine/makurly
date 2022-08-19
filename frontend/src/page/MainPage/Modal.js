import React from "react";
import "./style.css";
const Modal = ({ closeFunc }) => {
  return (
    <div className="modal-background">
      <div className="modal">
        <div className="cart-modal">
          <div className="top">
            <div className="a">
              <div className="title-label">[Test] 테스트 아이템 입니다</div>
              <div className="price-label">10000원</div>
            </div>
            <div className="b">
              <div className="count-container">
                <button className="count-button">-</button>
                <div className="count">1</div>
                <button className="count-button">+</button>
              </div>
            </div>
          </div>
          <div className="mid">
            <div className="price-container">
              <div className="total">합계</div>
              <div className="total-price">10000원</div>
            </div>
          </div>
          <div className="bottom">
            <div className="button-container">
              <button
                className="button2"
                style={{
                  width: "200px",
                  height: "50px",
                }}
                onClick={() => {
                  closeFunc();
                }}
              >
                취소
              </button>
              <button
                className="button1"
                style={{
                  width: "200px",
                  height: "50px",
                }}
              >
                장바구니 담기
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default Modal;
