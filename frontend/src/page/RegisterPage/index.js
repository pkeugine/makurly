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
        <div className="label-container">회원가입</div>
        <div className="input-layer">
          <div className="label-container">이름</div>
          <div className="input-container">
            <input
              type="text"
              onChange={nameHandler}
              placeholder="이름을 입력해주세요"
              className="input-box"
            ></input>
          </div>
        </div>
        <div className="input-layer">
          <div className="label-container">나이</div>
          <div className="input-container">
            <input
              type="text"
              placeholder="나이를 입력해주세요"
              onChange={ageHandler}
              className="input-box"
            ></input>
          </div>
        </div>
        <div className="input-layer">
          <div className="label-container">성별</div>
          <div className="input-container">
            <div className="radio-input">
              <div className="label">남자</div>

              <input
                type="radio"
                id="MALE"
                name="gender"
                checked={gender === "MALE"}
                onChange={() => {
                  setGender("MALE");
                }}
              ></input>
            </div>
            <div className="radio-input">
              <div className="label">여자</div>
              <input
                type="radio"
                id="FEMALE"
                name="gender"
                checked={gender === "FEMALE"}
                onChange={() => {
                  setGender("FEMALE");
                }}
              ></input>
            </div>
            <div className="radio-input">
              <div className="label">선택안함</div>
              <input
                type="radio"
                id="NULL"
                name="gender"
                checked={gender === null}
                onChange={() => {
                  setGender(null);
                }}
              ></input>
            </div>
          </div>
        </div>
        <div className="input-layer">
          <button
            className="button1"
            style={{
              fontSize: "20px",
              height: "50px",
              borderRadius: "10px",
            }}
            onClick={register}
          >
            가입하기
          </button>
        </div>
      </div>
    </div>
  );
}
export default RegisterPage;
