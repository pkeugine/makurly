import React, { useState, useEffect } from "react";
import { AiOutlineShoppingCart } from "react-icons/ai";
import axios from "axios";
import Modal from "./Modal";
import { API_SERVER } from "../../config";
import "./style.css";

function MainPage() {
  const [modalVisible, setModalVisible] = useState(false);
  const [items, setItems] = useState([]);
  const [clickItem, setClickItem] = useState(0);

  useEffect(() => {
    axios
      .get(API_SERVER + "/items")
      .then((res) => {
        console.log(res.data);
        setItems(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  const changePriceFormat = (price) => {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  };

  return (
    <div className="main-page-container">
      <div className="recommend-container">
        <div className="label-container">
          <div>밀키트를 골라주세요</div>
        </div>
        <div className="items-container">
          {items.map((item) => (
            <div className="item-container">
              <div className="image-container">
                <span
                  className="icon"
                  onClick={() => {
                    setClickItem(item.id);
                    setModalVisible(true);
                  }}
                >
                  <AiOutlineShoppingCart
                    size="24"
                    color="white"
                  ></AiOutlineShoppingCart>
                </span>
                <img alt="default" className="img" src={item.imageUrl}></img>
              </div>

              <div className="info-container">
                <div className="title-container">{item.name}</div>
                <div className="price-container">
                  {changePriceFormat(item.price)}원
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
      {modalVisible ? (
        <Modal
          itemId={clickItem}
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
