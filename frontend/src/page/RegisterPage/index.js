import React, { useState } from "react";
import "./style.css";
import axios from "axios";
import { AiOutlineCheckCircle } from "react-icons/ai";
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
              <div className="content-name">
                <div className="name">이름</div>
              </div>
              <div className="content-input">
                <input
                  type="text"
                  className="input"
                  placeholder="이름을 입력해주세요"
                ></input>
              </div>
            </div>
            <div className="b"></div>
          </div>
          <div className="input-layer">
            <div className="a">
              <div className="content-name">
                <div className="name">생년월일</div>
              </div>
              <div className="content-input">
                <div className="input">
                  <input className="input2" placeholder="YYYY"></input>

                  <input className="input3" placeholder="MM"></input>

                  <input className="input2" placeholder="DD"></input>
                </div>
              </div>
            </div>
            <div className="b"></div>
          </div>
          <div className="input-layer">
            <div className="a">
              <div className="content-name">
                <div className="name">성별</div>
              </div>
              <div className="content-input">
                <div
                  className="input"
                  style={{ border: "none", padding: "0px" }}
                >
                  <div className="radio-input">
                    <input
                      type="radio"
                      id="MALE"
                      name="gender"
                      checked={gender === "MALE"}
                      style={{ width: "1px", height: "1px" }}
                    ></input>
                    {gender === "MALE" ? (
                      <span className="span-clicked">
                        <div className="span-inner"></div>
                      </span>
                    ) : (
                      <span
                        className="span-unclicked"
                        onClick={() => {
                          setGender("MALE");
                        }}
                      >
                        <div className="span-inner"></div>
                      </span>
                    )}

                    <div className="label">남자</div>
                  </div>
                  <div className="radio-input">
                    <input
                      type="radio"
                      id="FEMALE"
                      name="gender"
                      checked={gender === "FEMALE"}
                      style={{ width: "1px", height: "1px" }}
                    ></input>
                    {gender === "FEMALE" ? (
                      <span className="span-clicked">
                        <div className="span-inner"></div>
                      </span>
                    ) : (
                      <span
                        className="span-unclicked"
                        onClick={() => {
                          setGender("FEMALE");
                        }}
                      >
                        <div className="span-inner"></div>
                      </span>
                    )}
                    <div className="label">여자</div>
                  </div>
                  <div className="radio-input">
                    <input
                      type="radio"
                      id="NULL"
                      name="gender"
                      checked={gender === null}
                      style={{ width: "1px", height: "1px" }}
                    ></input>
                    {gender === null ? (
                      <span className="span-clicked">
                        <div className="span-inner"></div>
                      </span>
                    ) : (
                      <span
                        className="span-unclicked"
                        onClick={() => {
                          setGender(null);
                        }}
                      >
                        <div className="span-inner"></div>
                      </span>
                    )}
                    <div className="label">상관 없음</div>
                  </div>
                </div>
              </div>
            </div>
            <div className="b"></div>
          </div>
          <div className="input-layer">
            <div className="a">
              <div className="content-name2">
                <div className="name">주소</div>
              </div>
              <div className="content-input2">
                <div className="inner">
                  <input
                    type="text"
                    className="input"
                    placeholder="주소를 검색해주세요"
                    disabled
                  ></input>
                </div>
                <div className="inner">
                  <input
                    type="text"
                    className="input"
                    placeholder="상세주소를 입력하세요"
                  ></input>
                </div>
              </div>
            </div>
            <div className="b">
              <div className="button-container">
                <div className="inner">
                  <button className="button2 search">찾아보기</button>
                </div>
                <div className="inner"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div className="agreement-container">
        <div className="a">
          <div className="label">이용약관동의</div>
        </div>
        <div className="b">
          <div className="main-agree">
            <AiOutlineCheckCircle
              size="30"
              color="rgb(217,217,217)"
              style={{ marginLeft: "5px", marginRight: "5px" }}
            ></AiOutlineCheckCircle>
            전체 동의합니다
          </div>
          <div className="sub-agree">
            <AiOutlineCheckCircle
              size="30"
              color="rgb(217,217,217)"
              style={{ marginLeft: "5px", marginRight: "5px" }}
            ></AiOutlineCheckCircle>
            이용약관 동의(필수)
          </div>
          <div className="sub-agree">
            <AiOutlineCheckCircle
              size="30"
              color="rgb(217,217,217)"
              style={{ marginLeft: "5px", marginRight: "5px" }}
            ></AiOutlineCheckCircle>
            개인정보 수집 이용 동의(필수)
          </div>
          <div className="sub-agree">
            <AiOutlineCheckCircle
              size="30"
              color="rgb(217,217,217)"
              style={{ marginLeft: "5px", marginRight: "5px" }}
            ></AiOutlineCheckCircle>
            개인정보 수집 이용 동의(필수)
          </div>
        </div>
      </div>
      <div className="register-button-container">
        <button className="button1 register-button">가입하기</button>
      </div>
    </div>
  );
}
export default RegisterPage;
