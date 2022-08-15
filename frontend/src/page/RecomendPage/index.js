import React, { useState, useEffect } from "react";
import "./style.css";

function RecommendPage() {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <div className="recommend-page-container">
      <div
        class="flip"
        onClick={() => {
          setIsOpen(true);
        }}
      >
        <div class={isOpen ? "card test" : "card"}>
          <div class="front"></div>
          <div class="back"></div>
        </div>
      </div>
    </div>
  );
}
export default RecommendPage;
