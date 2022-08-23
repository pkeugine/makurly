import React, { useState, useEffect } from "react";
import { AiOutlineShoppingCart } from "react-icons/ai";
import axios from "axios";
import Modal from "./Modal";
import { API_SERVER } from "../../config";
import "./style.css";

function MainPage() {
  //state
  const [modalVisible, setModalVisible] = useState(false);
  const [items, setItems] = useState([]);
  const [clickItem, setClickItem] = useState(0);
  const [selectedCategory, setSelectedCategory] = useState("샐러드·닭가슴살");
  const categories = [
    "샐러드·닭가슴살",
    "도시락·밥류",
    "떡볶이·튀김·순대",
    "피자·핫도그·만두",
    "죽·스프·카레",
    "폭립·떡갈비·안주",
    "파스타·면류",
    "선식·시리얼",
    "레드와인",
    "화이트와인",
    "샴페인",
    "막걸리·탁주",
    "증류주·약주·청주",
    "생수·탄산수",
    "음료·주스",
    "우유·두유·요거트",
    "커피",
    "차",
  ];

  useEffect(() => {
    axios
      .get(API_SERVER + "/items")
      .then((res) => {
        setItems(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }, []);

  useEffect(() => {
    if (selectedCategory === "전체보기") {
      axios
        .get(API_SERVER + "/items")
        .then((res) => {
          setItems(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    } else {
      console.log(API_SERVER + `/items/filter?category=${selectedCategory}`);
      axios
        .get(API_SERVER + `/items/filter?category=${selectedCategory}`)
        .then((res) => {
          setItems(res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }, [selectedCategory]);

  const categoryHandler = (e) => {
    setSelectedCategory(e.target.value);
  };
  const changePriceFormat = (price) => {
    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
  };

  return (
    <div className="main-page-container">
      <div className="recommend-container">
        <div className="label-container">
          <div>상품을 골라주세요</div>
        </div>
        <div className="category-container">
          {categories.map((category) => (
            <button
              className="category"
              value={category}
              key={category}
              onClick={categoryHandler}
            >
              {" "}
              {category}
            </button>
          ))}
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
