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
  }, []);

  return (
    <div className="userinfo-page-container">
      <div className="username-container">
        <div>User Name</div>
        <input type="text" disabled defaultValue={name}></input>
      </div>
      <div className="age-container">
        <div>Age</div>
        <input type="text" disabled defaultValue={age}></input>
      </div>
      <div className="gender-container">
        <div>Gender</div>
        <input type="text" disabled defaultValue={gender}></input>
      </div>
    </div>
  );
}
export default UserInfoPage;
