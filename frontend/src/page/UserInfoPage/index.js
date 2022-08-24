import React, { useState, useEffect } from "react";
import "./style.css";
import Interaction from "./Interaction";
import axios from "axios";
import { API_SERVER } from "../../config";

function UserInfoPage() {
  //state
  const userId = window.localStorage.getItem("user-id");
  const [interactions, setInteractions] = useState([]);
  useEffect(() => {
    axios.get(API_SERVER + `/interactions/${userId}`).then((res) => {
      console.log(res.data);
      setInteractions(res.data);
    });
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <div className="userinfo-page-container">
      <div
        style={{
          fontWeight: "bold",
          fontSize: "30px",
          color: "rgb(95,0,128)",
          padding: "20px",
        }}
      >
        구매 내역
      </div>
      {interactions.map((interaction) => (
        <Interaction interaction={interaction}></Interaction>
      ))}
    </div>
  );
}
export default UserInfoPage;
