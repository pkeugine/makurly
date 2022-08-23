import React from "react";
import "./style.css";
function DashboardPage() {
  return (
    <div className="dashboard-page">
      <iframe
        title="dashboard"
        style={{ border: "none" }}
        src="https://public.tableau.com/views/test_16612332153140/1?:language=ko-KR&publish=yes&:display_count=n&:origin=viz_share_link?:embed=yes&:showVizHome=no"
        width="1050"
        height="2000"
      ></iframe>
    </div>
  );
}
export default DashboardPage;
