import React, { useState } from "react";
import "./style.css";
import axios from "axios";
import { API_SERVER } from "../../config";
import { useNavigate } from "react-router-dom";

function SignInPage() {
  const [name, setName] = useState("");
  const [age, setAge] = useState("");
  const navigate = useNavigate();
  const nameHandler = (e) => {
    setName(e.target.value);
  };
  const ageHandler = (e) => {
    setAge(e.target.value);
  };
  const register = () => {
    const body = {
      name: name,
      age: age,
    };
    axios
      .post(API_SERVER + "/customers", body)
      .then((res) => {
        alert("회원가입 완료");
        navigate("/");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div className="register-page-container">
      <div className="username-container">
        <div>User Name</div>
        <input type="text" onChange={nameHandler}></input>
      </div>
      <div className="age-container">
        <div>Age</div>
        <input type="text" onChange={ageHandler}></input>
      </div>
      <button onClick={register}>sign-up</button>
    </div>
  );
}
export default SignInPage;
