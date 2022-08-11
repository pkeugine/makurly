import React, { useState } from "react";
import "./style.css";
import axios from "axios";
import { API_SERVER } from "../../config";
import { useNavigate } from "react-router-dom";

function SignInPage() {
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
      .post(API_SERVER + "/customers/login", body)
      .then((res) => {
        window.localStorage.setItem("isLogin", "true");
        window.localStorage.setItem("user-id", "1");
        window.localStorage.setItem("user-name", res.data.name);
        alert("로그인 완료");
        navigate("/");
      })
      .catch((err) => {
        console.log(err);
      });
  };

  return (
    <div className="signin-page-container">
      <div className="username-container">
        <div>User Name</div>
        <input type="text" onChange={nameHandler}></input>
      </div>
      <button onClick={signIn}>sign-in</button>
    </div>
  );
}
export default SignInPage;
