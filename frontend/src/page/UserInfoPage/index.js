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
      // console.log(interactions);
    });
  }, []);

  return (
    <div className="userinfo-page-container">
      {interactions.map((interaction) => (
        <Interaction interaction={interaction}></Interaction>
      ))}
    </div>
  );
}
export default UserInfoPage;
