import React, { useState } from "react";
import { AiOutlineShoppingCart } from "react-icons/ai";
import Modal from "./Modal";
import "./style.css";

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
      {modalVisible ? (
        <Modal
          closeFunc={() => {
            setModalVisible(false);
          }}
        ></Modal>
      ) : (
        <></>
      )}
    </div>
  );
}
export default MainPage;
