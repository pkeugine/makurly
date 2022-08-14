import React, { useState } from "react";
import { AiFillCheckCircle } from "react-icons/ai";
import { GrLocation } from "react-icons/gr";
import "./style.css";

function CartPage() {
  const arr = Array.from(Array(4).keys());

  return (
    <div className="cart-page-container">
      <div className="form-container">
        <div className="title-container">장바구니</div>
        <div className="cart-container">
          <div className="a">
            {arr.map(() => (
              <div className="item-container">
                <div className="check-container">
                  <AiFillCheckCircle
                    size="30"
                    color="rgb(95, 0, 128)"
                  ></AiFillCheckCircle>
                </div>
                <div className="image-container">
                  <div className="img">
                    <img
                      className="image"
                      alt="item"
                      src="./img/default.jpg"
                      style={{ height: "90px" }}
                    ></img>
                  </div>
                </div>
                <div className="name-container">[Test]테스트 상품입니다</div>
                <div className="count-container">
                  <div className="count-box">
                    <button className="count-button">-</button>
                    <div className="count">1</div>
                    <button className="count-button">+</button>
                  </div>
                </div>
                <div className="price-container">10000원</div>
              </div>
            ))}
          </div>
          <div className="b">
            <div className="destination-label">
              <GrLocation size="20"></GrLocation>
              <div className="destination-title">배송지</div>
            </div>
            <div className="destination-description">
              <div className="main">경기도 고양시 일산동구 중앙로1129</div>
              <div className="detail">호수마을</div>
            </div>
            <button className="destination-button button2">배송지 변경</button>
            <div className="price">
              <div>상품 금액</div>
              <div>10000원</div>
            </div>
            <button className="order-button button1">주문하기</button>
          </div>
        </div>
      </div>
    </div>
  );
}
export default CartPage;
