import React, { useState } from "react";
import "./LoginPage.css";
import axios from "axios";

const LoginPage = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post("http://localhost:8080/user/login", {
        email,
        password,
      });
      if (response.status === 201) {
        setMessage("Login successful!");
      } else {
        setMessage("Login failed. Please try again.");
      }
    } catch (error) {
      console.error("There was an error registering!", error.response.data);
      setMessage(error.response.data);
    }
  };

  return (
    <div className="app-container">
      <div className="login-container">
        <h2>Login</h2>
        <form onSubmit={handleSubmit}>
          <label>
            Email:
            <input
              type="email"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </label>
          <label>
            Password:
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </label>
          <button type="submit">Login</button>

          <label className="reglbl">
            Dont have an account ? <a href="/register">Register Now</a>
          </label>
        </form>
        {message && <p>{message}</p>}
      </div>
    </div>
  );
};

export default LoginPage;
