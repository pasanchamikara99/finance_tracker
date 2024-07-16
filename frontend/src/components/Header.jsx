import React, { useState } from "react";

const Header = () => {
  const [userId, setUserId] = useState(localStorage.getItem("username"));

  const styles = {
    header: {
      backgroundColor: "#444547",
      padding: "10px 20px",
      borderBottom: "1px solid #dee2e6",
    },
    greeting: {
      margin: 0,
      fontSize: "15px",
      color: "#f0f2f5",
    },
  };
  return (
    <div>
      <header style={styles.header}>
        <h1 style={styles.greeting}>Hello, {userId}!</h1>
      </header>
    </div>
  );
};

export default Header;
