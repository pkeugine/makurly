import React, { useState } from "react";
import "./style.css";
import { AiFillCaretDown, AiFillCaretUp } from "react-icons/ai";

function Interaction({ interaction }) {
  //state

  const [isOpen, setIsOpen] = useState(false);
  const parseDate = (dateTime) => {
    let date = dateTime.split("T")[0];
    return date;
  };
  const parseTime = (dateTime) => {
    let remain = dateTime.split("T")[1];
    let time = remain.split(".")[0];
    return time;
  };
  return (
    <div className="interactions-container">
      {
        <div className="interaction-container">
          <div className="interaction-controller">
            <div className="date-container">
              <div>{parseDate(interaction.orderDate)}</div>
              <div style={{ fontSize: "15px", color: "rgb(150,150,150" }}>
                {parseTime(interaction.orderDate)}
              </div>
            </div>
            <div className="icon-container">
              {isOpen ? (
                <AiFillCaretUp
                  className="button"
                  size="30"
                  color="rgb(95,0,128)"
                  onClick={() => {
                    setIsOpen(!isOpen);
                  }}
                ></AiFillCaretUp>
              ) : (
                <AiFillCaretDown
                  className="button"
                  size="30"
                  color="rgb(95,0,128)"
                  onClick={() => {
                    setIsOpen(!isOpen);
                  }}
                ></AiFillCaretDown>
              )}
            </div>
          </div>
          {isOpen ? (
            <>
              {interaction.interactions.map((interactionItem) => (
                <div className="interaction-item-container">
                  <div className="img-container">
                    <img
                      className="image"
                      alt="img"
                      src={interactionItem.item.imageUrl}
                    ></img>
                  </div>
                  <div className="name-container">
                    {interactionItem.item.name}
                  </div>
                  <div className="quantity-container">
                    {interactionItem.quantity}개
                  </div>
                </div>
              ))}
            </>
          ) : (
            <></>
          )}
        </div>
      }
    </div>
  );
}
export default Interaction;
