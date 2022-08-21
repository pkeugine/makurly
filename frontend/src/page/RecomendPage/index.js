import React, { useState, useEffect } from "react";
import axios from "axios";
import { useLocation } from "react-router-dom";
import { AiOutlineCheckCircle } from "react-icons/ai";
import RecommendCard from "./RecommendCard";
import { API_SERVER } from "../../config";

function RecommendPage() {
  const location = useLocation();
  const totalPrice = location.state.price;
  const id = location.state.id;
  const [recommends, setRecommends] = useState([]);

  useEffect(() => {
    axios
      .get(API_SERVER + `/recommend/${id}`)
      .then((res) => {
        setRecommends(res.data);
      })
      .catch((err) => {
        alert(err);
      });
  }, []);

  return (
    <div className="recommend-page-container">
      <div className="order-finish">
        <AiOutlineCheckCircle
          size="50"
          color="rgb(95, 0, 128)"
        ></AiOutlineCheckCircle>
        <div className="a">이지호님의 주문이 완료되었습니다.</div>
        <div className="b">내일 아침에 만나요!</div>
      </div>
      <div className="order-price">
        <div className="label">결제 완료</div>
        <div className="price-container">
          <div className="line">
            <div className="price-category">상품 금액</div>
            <div className="price-content">{totalPrice}원</div>
          </div>
          <div className="line">
            <div className="price-category">할인 금액</div>
            <div className="price-content">-0원</div>
          </div>
          <div className="line">
            <div className="price-category">배송 금액</div>
            <div className="price-content">+0원</div>
          </div>
          <div className="line big">
            <div className="final-category">총 결제금액</div>
            <div className="final-price">{totalPrice}원</div>
          </div>
        </div>
      </div>
      <div className="my-store">
        <div className="description">
          <div className="a">
            <span>고객님에게 맞춘&nbsp;</span>
            <span style={{ color: "rgb(95,0,128)", fontWeight: "bold" }}>
              {" "}
              특별한 상점
            </span>
          </div>
          <div className="b">
            {" "}
            방금 사신 상품에 대한 리뷰를 남겨주시면 아래 가격에 상품을 드려요
          </div>
        </div>
        <div className="cards">
          {recommends.map((recommend) => (
            <RecommendCard recommend={recommend}></RecommendCard>
          ))}
        </div>
      </div>
      <div className="button-container">
        <button className="button2 button">주문 상세보기</button>
        <button className="button1 button">쇼핑 계속하기</button>
      </div>
    </div>
  );
}
export default RecommendPage;
