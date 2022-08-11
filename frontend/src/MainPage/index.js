import React from "react";
import "./style.css";
import axios from "axios";
function MainPage() {
  const testApi = () => {
    axios.get(process.env);
  };
  return (
    <div className="main-page-container">
      마켓컬리 메인페이지
      <button onClick={testApi}>API TEST</button>
    </div>
  );
}
export default MainPage;
