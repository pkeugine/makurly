import React, { useState, useEffect } from "react";
import "./style.css";
import axios from "axios";
import { API_SERVER } from "../../config";

function UserInfoPage() {
  const userId = window.localStorage.getItem("user-id");
  const [name, setName] = useState("");
  const [age, setAge] = useState("");
  const [gender, setGender] = useState("");

  useEffect(() => {
    axios.get(API_SERVER + `/customers/${userId}`).then((res) => {
      setName(res.data.name);
      setAge(res.data.age);
      setGender(res.data.gender);
    });
  });

  return (
    <div className="register-page-container">
      <div className="form-container">
        <div className="label-container">유저정보</div>
        <div className="input-layer">
          <div className="label-container">이름</div>
          <div className="input-container">
            <input
              type="text"
              placeholder={name}
              className="input-box"
              disabled
            ></input>
          </div>
        </div>
        <div className="input-layer">
          <div className="label-container">나이</div>
          <div className="input-container">
            <input
              type="text"
              placeholder={age}
              className="input-box"
              disabled
            ></input>
          </div>
        </div>
        <div className="input-layer">
          <div className="label-container">성별</div>
          <div className="input-container">
            <input
              type="text"
              placeholder={gender}
              className="input-box"
              disabled
            ></input>
          </div>
        </div>
      </div>
    </div>
  );
}
export default UserInfoPage;
