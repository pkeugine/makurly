import React from "react";
import DaumPostcode from "react-daum-postcode";
import { AiOutlineClose } from "react-icons/ai";

export default function DaumAddress({ onRevise, onShow }) {
  const handleComplete = async (data) => {
    let fullAddress = data.address;

    onRevise(fullAddress);
    onShow();
  };
  const postCodeStyle = {
    display: "block",
    width: "600px",
    padding: "7px",
    backgroundColor: "white",
  };
  return (
    <div className="modal-background">
      <div className="modal" style={postCodeStyle}>
        <div
          style={{ display: "flex", justifyContent: "right", padding: "5px" }}
        >
          <AiOutlineClose
            size="24"
            color="rgb(150,150,150)"
            onClick={() => {
              onShow();
            }}
            className="button"
          ></AiOutlineClose>
        </div>

        <DaumPostcode onComplete={handleComplete} />
      </div>
    </div>
  );
}
