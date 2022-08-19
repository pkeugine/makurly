import React, { useEffect, useState } from "react";
import axios from "axios";
import { API_SERVER } from "../../config";
import "./style.css";
const Modal = ({ closeFunc, itemId }) => {
  const [id, setId] = useState(itemId);
  const [name, setName] = useState(0);
  const [price, setPrice] = useState(0);
  const [quantity, setQuantity] = useState(1);

  const changePriceFormat = (price) => {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  };

  useEffect(() => {
    axios
      .get(API_SERVER + `/items/${itemId}`)
      .then((res) => {
        setName(res.data.name);
        setPrice(res.data.price);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

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
                <button className="count-button">-</button>
                <div className="count">{quantity}</div>
                <button className="count-button">+</button>
              </div>
            </div>
          </div>
          <div className="mid">
            <div className="price-container">
              <div className="total">합계</div>
              <div className="total-price">{changePriceFormat(price)}원</div>
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
