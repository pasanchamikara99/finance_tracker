import axios from "axios";
import React, { useEffect, useState } from "react";

const AddExpense = () => {
  const [expense, setExpense] = useState([]);
  const [selectedExpenseType, setSelectedExpenseType] = useState(""); // State to hold selected expense type
  const [amount, setAmount] = useState(""); // State to hold amount input
  const [message, setMessage] = useState("");
  const [userId, setUserId] = useState(localStorage.getItem("username"));

  useEffect(() => {
    const getExpenseType = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/category/getCategoryByType/Expence`
        );
        if (response.status === 200) {
          setExpense(response.data);
        } else {
          setMessage("Registration failed. Please try again.");
        }
      } catch (error) {
        console.error("There was an error registering!", error.response.data);
        setMessage(error.response.data);
      }
    };

    getExpenseType();
  }, []);

  const styles = {
    container: {
      maxWidth: "400px",
      margin: "auto",
      padding: "50px",
      border: "1px solid #ccc",
      borderRadius: "5px",
      boxShadow: "0 0 10px rgba(0, 0, 0, 0.1)",
      backgroundColor: "#f9f9f9",
    },
    form: {
      display: "flex",
      flexDirection: "column",
    },
    formGroup: {
      marginBottom: "15px",
    },
    label: {
      marginBottom: "5px",

      display: "block",
    },
    input: {
      padding: "10px",
      fontSize: "1rem",
      border: "1px solid #ccc",
      borderRadius: "4px",
      width: "90%",
    },
    select: {
      padding: "10px",
      fontSize: "1rem",
      border: "1px solid #ccc",
      borderRadius: "4px",
      width: "100%",
    },
    button: {
      backgroundColor: "#007bff",
      color: "#fff",
      border: "none",
      padding: "10px 20px",
      fontSize: "1rem",
      cursor: "pointer",
      borderRadius: "4px",
      transition: "background-color 0.3s",
    },
    buttonHover: {
      backgroundColor: "#0056b3",
    },
    message: {
      marginTop: "15px",
      padding: "10px",
      borderRadius: "4px",
      backgroundColor: "#f8d7da",
      color: "#721c24",
      border: "1px solid #f5c6cb",
    },
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await axios.post(
        "http://localhost:8080/expense/addExpense",
        { userId, type: Number(selectedExpenseType), amount: Number(amount) }
      );

      if (response.status === 201) {
        setMessage("Expense added successfully!");
        updateExpenses();
        setSelectedExpenseType("");
        setAmount("");
      } else {
        setMessage("Failed to add expense. Please try again.");
      }
    } catch (error) {
      console.error("Error adding expense:", error.response.data);
      setMessage("Error adding expense. Please try again.");
    }
  };

  return (
    <div style={styles.container}>
      <h2>Add Expense</h2>
      <form onSubmit={handleSubmit} style={styles.form}>
        {message && <p style={styles.message}>{message}</p>}
        <div style={styles.formGroup}>
          <label htmlFor="expenseType" style={styles.label}>
            Expense Type:
          </label>
          <select
            id="expenseType"
            value={selectedExpenseType}
            onChange={(e) => setSelectedExpenseType(e.target.value)}
            style={styles.select}
            required
          >
            <option value="">Select Expense Type</option>
            {expense.map((type) => (
              <option key={type.id} value={type.id}>
                {type.description}
              </option>
            ))}
          </select>
        </div>
        <div style={styles.formGroup}>
          <label htmlFor="amount" style={styles.label}>
            Amount:
          </label>
          <input
            type="number"
            id="amount"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
            style={styles.input}
            required
            placeholder="Rs."
          />
        </div>
        <button
          type="submit"
          style={styles.button}
          onMouseOver={(e) =>
            (e.currentTarget.style.backgroundColor = "#0056b3")
          }
          onMouseOut={(e) =>
            (e.currentTarget.style.backgroundColor = "#007bff")
          }
        >
          Add Expense
        </button>
      </form>
    </div>
  );
};

export default AddExpense;
