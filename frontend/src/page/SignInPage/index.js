import React, { useState } from "react";
import "./style.css";
import axios from "axios";
import { API_SERVER } from "../../config";
import { useNavigate } from "react-router-dom";

function SignInPage() {
  //state
  const [name, setName] = useState("");
  const navigate = useNavigate();
  const nameHandler = (e) => {
    setName(e.target.value);
  };

  const signIn = () => {
    const body = {
      name: name,
    };
    axios
      .post(API_SERVER + "/customers/sign-in", body)
      .then((res) => {
        var userId = res.data.id;
        var userIdString = userId.toString();
        window.localStorage.setItem("isLogin", "true");
        window.localStorage.setItem("user-id", userIdString);
        window.localStorage.setItem("user-name", res.data.name);
        navigate("/");
      })
      .catch((err) => {
        alert(err.response.data.message);
      });
  };

  return (
    <div className="signin-page-container">
      <div className="form-container">
        <div className="label-container">로그인</div>
        <div className="input-layer">
          <input
            type="text"
            onChange={nameHandler}
            className="input-box"
            placeholder="이름을 입력해주세요"
          ></input>
        </div>
        <div className="input-layer">
          {" "}
          <button
            onClick={signIn}
            style={{
              fontSize: "20px",
              height: "50px",
              borderRadius: "10px",
            }}
            className="button1"
          >
            로그인
          </button>
        </div>
      </div>
    </div>
  );
}
export default SignInPage;
