import React, { useState, useEffect } from "react";
import CartItem from "./CartItem";
import { GrLocation } from "react-icons/gr";
import "./style.css";
import isLogin from "../../utils/isLogin";
import { Navigate } from "react-router-dom";
import axios from "axios";
import { API_SERVER } from "../../config";

function CartPage() {
  const [mainAddress, setMainAddress] = useState("");
  const [detailedAddress, setDetailedAddress] = useState("");
  const [carts, setCarts] = useState([]);

  useEffect(() => {
    axios
      .get(API_SERVER + `/carts?id=${window.localStorage.getItem("user-id")}`)
      .then((res) => {
        const result = [];
        const arr = res.data;
        var tempTotalPrice = 0;
        arr.map((elem) => {
          var cart = elem;
          tempTotalPrice += cart.item.price * cart.quantity;
          cart.checked = true;
          result.push(cart);
        });
        setCarts(result);
      })
      .catch((err) => {
        console.log(err);
      });
    axios
      .get(API_SERVER + `/customers/${window.localStorage.getItem("user-id")}`)
      .then((res) => {
        setMainAddress(res.data.mainAddress);
        setDetailedAddress(res.data.detailedAddress);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const changePriceFormat = (price) => {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  };

  const changeCarts = (updateCart) => {
    const findIndex = carts.findIndex((cart) => cart.id === updateCart.id);
    let copiedCarts = [...carts];
    if (findIndex != -1) {
      copiedCarts[findIndex] = { ...copiedCarts[findIndex], ...updateCart };
    }
    setCarts(copiedCarts);
  };

  const finalPrice = () => {
    var price = 0;
    carts.map((cart) => {
      if (cart.checked) {
        price += cart.quantity * cart.item.price;
      }
    });
    return price;
  };

  if (!isLogin()) {
    alert("로그인이 필요합니다");
    return (
      <Navigate
        to={{
          pathname: "/sign-in",
        }}
      />
    );
  }

  return (
    <div className="cart-page-container">
      <div className="form-container">
        <div className="title-container">장바구니</div>
        <div className="cart-container">
          <div className="a">
            {carts.map((cart, idx) => (
              <CartItem key={idx} cart={cart} handler={changeCarts}></CartItem>
            ))}
          </div>
          <div className="b">
            <div className="destination-label">
              <GrLocation size="20"></GrLocation>
              <div className="destination-title">배송지</div>
            </div>
            <div className="destination-description">
              <div className="main">{mainAddress}</div>
              <div className="detail">{detailedAddress}</div>
            </div>
            <button className="destination-button button2">배송지 변경</button>
            <div className="price">
              <div>상품 금액</div>
              <div>{changePriceFormat(finalPrice())}원</div>
            </div>
            <button className="order-button button1">주문하기</button>
          </div>
        </div>
      </div>
    </div>
  );
}
export default CartPage;
