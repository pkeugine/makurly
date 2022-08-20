import React, { useState } from "react";
import { AiFillCheckCircle, AiOutlineCheckCircle } from "react-icons/ai";
import "./style.css";

function CartItem({ cart, handler }) {
  const [cartInfo, setCartInfo] = useState(cart);
  const [checked, setChecked] = useState(cart.checked);
  const [quantity, setQuantity] = useState(cart.quantity);
  const changeCheck = () => {
    var copiedCartInfo = cartInfo;
    copiedCartInfo.checked = !checked;
    setChecked(!checked);
    setCartInfo(copiedCartInfo);
    handler(copiedCartInfo);
  };
  const addQuantity = () => {
    var copiedCartInfo = cartInfo;
    copiedCartInfo.quantity = quantity + 1;
    setQuantity(quantity + 1);
    setCartInfo(copiedCartInfo);
    handler(copiedCartInfo);
  };
  const subQuantity = () => {
    var copiedCartInfo = cartInfo;
    copiedCartInfo.quantity = quantity - 1;
    setQuantity(quantity - 1);
    setCartInfo(copiedCartInfo);
    handler(copiedCartInfo);
  };
  const totalPrice = () => {
    return quantity * cartInfo.item.price;
  };
  const changePriceFormat = (price) => {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  };

  return (
    <div className="item-container">
      <div className="check-container">
        {checked ? (
          <AiFillCheckCircle
            size="30"
            color="rgb(95, 0, 128)"
            onClick={changeCheck}
          ></AiFillCheckCircle>
        ) : (
          <AiOutlineCheckCircle
            size="30"
            color="rgb(150, 150, 150)"
            onClick={changeCheck}
          ></AiOutlineCheckCircle>
        )}
      </div>
      <div className="image-container">
        <div className="img">
          <img
            className="image"
            alt="item"
            src={cartInfo.item.imageUrl}
            style={{ height: "90px" }}
          ></img>
        </div>
      </div>
      <div className="name-container">{cartInfo.item.name}</div>
      <div className="count-container">
        <div className="count-box">
          <button
            className="count-button"
            disabled={quantity < 2}
            onClick={subQuantity}
          >
            -
          </button>
          <div className="count">{quantity}</div>
          <button className="count-button" onClick={addQuantity}>
            +
          </button>
        </div>
      </div>
      <div className="price-container">{changePriceFormat(totalPrice())}원</div>
    </div>
  );
}
export default CartItem;
