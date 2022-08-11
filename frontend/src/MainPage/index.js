import React from "react";
import "./style.css";
import axios from "axios";
import { API_SERVER } from "../config";
function MainPage() {
  const testApi = () => {
    const body = {
      name: "item1",
    };
    axios.post(API_SERVER + "/items", body).then((res) => {
      console.log(res.data);
    });
  };
  return (
    <div className="main-page-container">
      마켓컬리 메인페이지
      <button onClick={testApi}>API TEST</button>
    </div>
  );
}
export default MainPage;
