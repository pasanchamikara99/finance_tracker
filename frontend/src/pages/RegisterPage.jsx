import React, { useState } from "react";
import "./RegisterPage.css";
import axios from "axios";

const RegisterPage = () => {
  const [firstName, setfName] = useState("");
  const [lastName, setlName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [cpassword, setcPassword] = useState("");
  const [message, setMessage] = useState("");

  const handleSubmit = async (event) => {
    event.preventDefault();

    try {
      const response = await axios.post("http://localhost:8080/user/register", {
        firstName,
        lastName,
        email,
        password,
        cpassword,
      });
      if (response.status === 201) {
        setMessage("Registration successful!");
        navigate("/");
      } else {
        setMessage("Registration failed. Please try again.");
      }
    } catch (error) {
      console.error("There was an error registering!", error.response.data);
      setMessage(error.response.data);
    }
  };

  return (
    <div className="app-container">
      <div className="register-container">
        <h2>Register</h2>
        <form onSubmit={handleSubmit}>
          {message && <p>{message}</p>}
          <label>
            First Name:
            <input
              type="text"
              value={firstName}
              onChange={(e) => setfName(e.target.value)}
              required
            />
          </label>
          <label>
            Last Name:
            <input
              type="text"
              value={lastName}
              onChange={(e) => setlName(e.target.value)}
              required
            />
          </label>
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
          <label>
            Confirm Password:
            <input
              type="password"
              value={cpassword}
              onChange={(e) => setcPassword(e.target.value)}
              required
            />
          </label>
          <button type="submit">Register</button>

          <label className="reglbl">
            Already Registred ? <a href="/">Sign In</a>
          </label>
        </form>
      </div>
    </div>
  );
};

export default RegisterPage;
