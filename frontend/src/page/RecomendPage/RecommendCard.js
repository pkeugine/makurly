import React, { useState } from "react";
import { AiFillCaretUp } from "react-icons/ai";
import ReactCardFlip from "react-card-flip";
import "./style.css";
const RecommendCard = ({ recommend }) => {
  const [isFlipped, setIsFlipped] = useState(false);
  const click = () => {
    setIsFlipped(!isFlipped);
  };
  const discountPrice = () => {
    return recommend.item.price * ((100 - recommend.discountRate) / 100);
  };
  const changePriceFormat = (price) => {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  };
  return (
    <div className="card">
      <ReactCardFlip isFlipped={isFlipped} flipDirection="horizontal">
        <div key="front">
          <div className="card-container">
            <img
              src="./img/back.png"
              alt="backCard"
              onClick={click}
              className="image"
            ></img>
          </div>
        </div>

        <div key="back">
          <div className="card-container">
            <img
              src={recommend.item.imageUrl}
              alt="frontCard"
              className="image2"
            ></img>
            <img src="./img/front.png" alt="frontCard" className="image"></img>
            <div className="price-container">
              <div className="discount-rate">{recommend.discountRate}%</div>
              <div className="off">off</div>
            </div>
          </div>
        </div>
      </ReactCardFlip>
      {isFlipped ? (
        <div className="item-info">
          <AiFillCaretUp size="24" color="rgb(95,0,128)"></AiFillCaretUp>
          <div className="item-name">{recommend.item.name}</div>
          <div className="price-info">
            <span className="rate">{recommend.discountRate}%&nbsp;</span>
            <span className="cur-price">
              {changePriceFormat(discountPrice())}원&nbsp;
            </span>
            <span className="before-price">
              {changePriceFormat(recommend.item.price)}원
            </span>
          </div>
        </div>
      ) : (
        <div className="item-info"></div>
      )}
    </div>
  );
};
export default RecommendCard;
