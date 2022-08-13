import React, { useState } from "react";
import "./style.css";
import axios from "axios";
import { API_SERVER } from "../../config";
import { useNavigate } from "react-router-dom";

function RegisterPage() {
  const [name, setName] = useState("");
  const [age, setAge] = useState("");
  const [gender, setGender] = useState("MALE");
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
      gender: gender,
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
      <div className="form-container">
        <div className="title-container">회원가입</div>
        <div className="content-container">
          <div className="input-layer">
            <div className="a">
              <div className="content-name"></div>
              <div className="content-input"></div>
            </div>
            <div className="b"></div>
          </div>
        </div>
      </div>
    </div>
  );
}
export default RegisterPage;
