import React, { useEffect, useState } from "react";
import axios from "axios";
import { API_SERVER } from "../../config";
import "./style.css";
import isLogin from "../../utils/isLogin";
const Modal = ({ closeFunc, itemId }) => {
  const id = itemId;
  const [name, setName] = useState(0);
  const [price, setPrice] = useState(0);
  const [quantity, setQuantity] = useState(1);
  const [totalPrice, setTotalPrice] = useState(0);
  const changePriceFormat = (price) => {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  };

  useEffect(() => {
    axios
      .get(API_SERVER + `/items/${itemId}`)
      .then((res) => {
        setName(res.data.name);
        setPrice(res.data.price);
        setTotalPrice(res.data.price);
      })
      .catch((err) => {
        console.log(err);
      });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  useEffect(() => {
    setTotalPrice(quantity * price);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [quantity]);

  const submitCart = () => {
    if (!isLogin()) {
      alert("로그인을 해주세요!");
      return;
    }
    let body = {
      customerId: window.localStorage.getItem("user-id"),
      itemId: id,
      quantity: quantity,
    };
    axios
      .post(API_SERVER + "/carts/add", body)
      .then((res) => {
        closeFunc();
      })
      .catch((err) => {
        console.log(err);
      });
  };
  const close = () => {
    closeFunc();
  };

  return (
    <div className="modal-background">
      <div className="modal">
        <div className="cart-modal">
          <div className="top">
            <div className="a">
              <div className="title-label">{name}</div>
              <div className="price-label">{changePriceFormat(price)}원</div>
            </div>
            <div className="b">
              <div className="count-container">
                <button
                  className="count-button"
                  disabled={quantity === 1}
                  onClick={() => {
                    setQuantity(quantity - 1);
                  }}
                >
                  -
                </button>
                <div className="count">{quantity}</div>
                <button
                  className="count-button"
                  onClick={() => {
                    setQuantity(quantity + 1);
                  }}
                >
                  +
                </button>
              </div>
            </div>
          </div>
          <div className="mid">
            <div className="price-container">
              <div className="total">합계</div>
              <div className="total-price">
                {changePriceFormat(totalPrice)}원
              </div>
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
                onClick={close}
              >
                취소
              </button>
              <button
                className="button1"
                style={{
                  width: "200px",
                  height: "50px",
                }}
                onClick={submitCart}
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
