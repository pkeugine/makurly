import React, { useState } from "react";
import { AiOutlineShoppingCart } from "react-icons/ai";
import "./style.css";

const Modal = () => {
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
              <div className="count-container"></div>
            </div>
          </div>
          <div className="mid">
            <div className="price-container">
              <div className="total"></div>
              <div className="total-price"></div>
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

function MainPage() {
  const [modalVisible, setModalVisible] = useState(false);
  const arr = Array.from(Array(9).keys());

  return (
    <div className="main-page-container">
      <div className="recommend-container">
        <div className="label-container">
          <div>밀키트를 골라주세요</div>
        </div>
        <div className="items-container">
          {arr.map(() => (
            <div className="item-container">
              <div className="image-container">
                <span
                  className="icon"
                  onClick={() => {
                    setModalVisible(true);
                  }}
                >
                  <AiOutlineShoppingCart
                    size="24"
                    color="white"
                  ></AiOutlineShoppingCart>
                </span>
                <img
                  alt="default"
                  className="img"
                  src="./img/default.jpg"
                ></img>
              </div>

              <div className="info-container">
                <div className="title-container">
                  [Test] 테스트 아이템 입니다
                </div>
                <div className="price-container">10000원</div>
              </div>
            </div>
          ))}
        </div>
      </div>
      {modalVisible ? <Modal></Modal> : <></>}
    </div>
  );
}
export default MainPage;
