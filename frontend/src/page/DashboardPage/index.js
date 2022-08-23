import React from "react";
import "./style.css";
function DashboardPage() {
  return (
    <div className="dashboard-page">
      <div
        style={{
          fontWeight: "bold",
          fontSize: "30px",
          color: "rgb(95,0,128)",
          padding: "20px",
        }}
      >
        Dashboard
      </div>
      <iframe
        title="dashboard"
        style={{
          border: "none",
        }}
        src="https://public.tableau.com/views/makurly/1?:language=ko-KR&publish=yes&:display_count=n&:origin=viz_share_link?:embed=yes&:showVizHome=no"
        width="1000"
        height="900"
      ></iframe>
    </div>
  );
}
export default DashboardPage;
