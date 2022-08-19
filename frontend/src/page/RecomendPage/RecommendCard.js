import React, { useState } from "react";
import { AiFillCaretUp } from "react-icons/ai";
import ReactCardFlip from "react-card-flip";
import "./style.css";
const RecommendCard = () => {
  const [isFlipped, setIsFlipped] = useState(false);
  const click = () => {
    setIsFlipped(!isFlipped);
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
              src="./img/default.jpg"
              alt="frontCard"
              className="image2"
            ></img>
            <img src="./img/front.png" alt="frontCard" className="image"></img>
            <div className="price-container">
              <div className="discount-rate">30%</div>
              <div className="off">off</div>
            </div>
          </div>
        </div>
      </ReactCardFlip>
      {isFlipped ? (
        <div className="item-info">
          <AiFillCaretUp size="24" color="rgb(95,0,128)"></AiFillCaretUp>
          <div className="item-name">[Test]테스트 아이템입니다</div>
          <div className="price-info">
            <span className="rate">30%&nbsp;</span>
            <span className="cur-price">7000원&nbsp;</span>
            <span className="before-price">10000원</span>
          </div>
        </div>
      ) : (
        <div className="item-info"></div>
      )}
    </div>
  );
};
export default RecommendCard;
