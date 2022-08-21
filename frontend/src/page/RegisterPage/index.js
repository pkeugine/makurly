import React, { useState } from "react";
import "./style.css";
import axios from "axios";
import { AiOutlineCheckCircle } from "react-icons/ai";
import { API_SERVER } from "../../config";
import DaumPost from "./DaumPost";
import { useNavigate } from "react-router-dom";

function RegisterPage() {
  //state
  const [name, setName] = useState("");
  const [year, setYear] = useState("");
  const [month, setMonth] = useState("");
  const [day, setDay] = useState("");
  const [gender, setGender] = useState("MALE");
  const [device, setDevice] = useState("Android");
  const [mainAddress, setMainAddress] = useState("");
  const [detailedAddress, setDetailedAddress] = useState("");
  const [isOpenPost, setIsOpenPost] = useState(false);

  const onChangeOpenPost = () => {
    setIsOpenPost(!isOpenPost);
  };
  const navigate = useNavigate();
  const nameHandler = (e) => {
    setName(e.target.value);
  };
  const yearHandler = (e) => {
    const value = e.target.value;
    const onlyNumber = value.replace(/[^0-9]/g, "");
    setYear(onlyNumber);
  };
  const monthHandler = (e) => {
    const value = e.target.value;
    const onlyNumber = value.replace(/[^0-9]/g, "");
    setMonth(onlyNumber);
  };
  const dayHandler = (e) => {
    const value = e.target.value;
    const onlyNumber = value.replace(/[^0-9]/g, "");
    setDay(onlyNumber);
  };
  const detailedAddressHandler = (e) => {
    setDetailedAddress(e.target.value);
  };

  const submit = () => {
    if (!name.replace(/\s/g, "").length) {
      alert("이름은 필수입니다.");
      return;
    }
    if (
      !year.replace(/\s/g, "").length ||
      !month.replace(/\s/g, "").length ||
      !day.replace(/\s/g, "").length
    ) {
      alert("생년월일은 필수입니다.");
      return;
    }
    if (year.length < 4 || month.length < 2 || day.length < 2) {
      alert("생년월일의 형식이 올바르지 않습니다.");
      return;
    }
    if (
      !mainAddress.replace(/\s/g, "").length ||
      !detailedAddress.replace(/\s/g, "").length
    ) {
      alert("주소는 필수입니다.");
      return;
    }
    let birthDate = year + "-" + month + "-" + day;
    let body = {
      name: name,
      gender: gender,
      birthDate: birthDate,
      device: device,
      mainAddress: mainAddress,
      detailedAddress: detailedAddress,
    };
    axios
      .post(API_SERVER + "/customers", body)
      .then((res) => {
        alert("회원가입을 축하드립니다!");
        navigate("/sign-in");
      })
      .catch((err) => {
        alert(err.response.data.message);
      });
  };

  return (
    <>
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
                    onChange={nameHandler}
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
                    <input
                      className="input2"
                      placeholder="YYYY"
                      maxLength={4}
                      onChange={yearHandler}
                      value={year}
                    ></input>

                    <input
                      className="input3"
                      placeholder="MM"
                      maxLength={2}
                      onChange={monthHandler}
                      value={month}
                    ></input>

                    <input
                      className="input2"
                      placeholder="DD"
                      maxLength={2}
                      onChange={dayHandler}
                      value={day}
                    ></input>
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
                <div className="content-name">
                  <div className="name">기종</div>
                </div>
                <div className="content-input">
                  <div
                    className="input"
                    style={{ border: "none", padding: "0px" }}
                  >
                    <div className="radio-input">
                      <input
                        type="radio"
                        id="Android"
                        name="gender"
                        checked={device === "Android"}
                        style={{ width: "1px", height: "1px" }}
                      ></input>
                      {device === "Android" ? (
                        <span className="span-clicked">
                          <div className="span-inner"></div>
                        </span>
                      ) : (
                        <span
                          className="span-unclicked"
                          onClick={() => {
                            setDevice("Android");
                          }}
                        >
                          <div className="span-inner"></div>
                        </span>
                      )}

                      <div className="label">Android</div>
                    </div>
                    <div className="radio-input">
                      <input
                        type="radio"
                        id="iOS"
                        name="gender"
                        checked={device === "iOS"}
                        style={{ width: "1px", height: "1px" }}
                      ></input>
                      {device === "iOS" ? (
                        <span className="span-clicked">
                          <div className="span-inner"></div>
                        </span>
                      ) : (
                        <span
                          className="span-unclicked"
                          onClick={() => {
                            setDevice("iOS");
                          }}
                        >
                          <div className="span-inner"></div>
                        </span>
                      )}
                      <div className="label">iOS</div>
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
                      value={mainAddress}
                      disabled
                    ></input>
                  </div>
                  <div className="inner">
                    <input
                      type="text"
                      className="input"
                      placeholder="상세주소를 입력하세요"
                      onChange={detailedAddressHandler}
                    ></input>
                  </div>
                </div>
              </div>
              <div className="b">
                <div className="button-container">
                  <div className="inner">
                    <button
                      className="button2 search"
                      onClick={onChangeOpenPost}
                    >
                      찾아보기
                    </button>
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
          <button className="button1 register-button" onClick={submit}>
            가입하기
          </button>
        </div>
      </div>
      {isOpenPost ? (
        <DaumPost
          onRevise={(data) => {
            setMainAddress(data);
          }}
          onShow={() => {
            setIsOpenPost(false);
          }}
        ></DaumPost>
      ) : (
        <></>
      )}
    </>
  );
}
export default RegisterPage;
