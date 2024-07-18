import React, { useEffect, useState } from "react";
import { Doughnut } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";
import AddExpense from "../components/AddExpense";
import AddIncome from "../components/AddIncome";
import axios from "axios";

const HomePage = () => {
  ChartJS.register(ArcElement, Tooltip, Legend);

  const [btn, setBtn] = useState(false);
  const [username, setUserName] = useState(localStorage.getItem("username"));
  const [amount, setAmount] = useState([]);
  const [labels, setLabels] = useState([]);
  const [balance, setBalance] = useState(0);

  const data = {
    labels: labels,
    datasets: [
      {
        data: amount,
        backgroundColor: ["#FF6384", "#FFCE56", "#36A2EB"],
        hoverBackgroundColor: ["#FF6384", "#FFCE56", "#36A2EB"],
      },
    ],
  };

  useEffect(() => {
    getAllExpense();
    getBalance();
  }, [amount]);

  const getAllExpense = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/expense/getAllExpenses/` + username
      );
      if (response.status == 200) {
        const amounts = response.data.map((expense) => expense.amount);
        const labels = response.data.map((expense) => expense.typeDescription);
        setAmount(amounts);
        setLabels(labels);
      } else {
        console.log(response);
      }
    } catch (error) {
      console.error("There was an error registering!", error.response.data);
      setMessage(error.response.data);
    }
  };

  const getBalance = async () => {
    try {
      const response = await axios.get(
        `http://localhost:8080/expense/getBalance/` + username
      );
      if (response.status == 200) {
        setBalance(response.data.balance);
      } else {
        console.log(response);
      }
    } catch (error) {
      console.error("There was an error registering!", error.response.data);
      setMessage(error.response.data);
    }
  };

  const addIncome = () => {
    setBtn(false);

    getAllExpense();
  };

  const addExpense = () => {
    setBtn(true);

    getAllExpense();
  };

  const viewAllExpenses = () => {
    console.log("View All");
  };

  return (
    <div className="homeContainer">
      <div className="leftContainer">
        <div className="doughnut">
          <Doughnut data={data} />
        </div>

        <div className="buttons">
          <button className="addBtn" onClick={() => addIncome()}>
            +
          </button>
          <label
            className="balance"
            style={{ backgroundColor: balance > 0 ? "green" : "red" }}
            onClick={() => viewAllExpenses()}
          >
            Rs. {balance}.00
          </label>
          <button className="minBtn" onClick={() => addExpense()}>
            -
          </button>
        </div>
      </div>
      <div className="rightContainer">
        {btn ? <AddExpense /> : <AddIncome />}
      </div>
    </div>
  );
};

export default HomePage;
