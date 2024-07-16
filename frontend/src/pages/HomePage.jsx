import React from "react";
import { Doughnut } from "react-chartjs-2";
import { Chart as ChartJS, ArcElement, Tooltip, Legend } from "chart.js";

const HomePage = () => {
  ChartJS.register(ArcElement, Tooltip, Legend);

  const data = {
    labels: ["Red", "Yellow", "Blue"],
    datasets: [
      {
        data: [10, 20, 30],
        backgroundColor: ["#FF6384", "#FFCE56", "#36A2EB"],
        hoverBackgroundColor: ["#FF6384", "#FFCE56", "#36A2EB"],
      },
    ],
  };
  return (
    <div>
      HomePage
      <Doughnut data={data} />
    </div>
  );
};

export default HomePage;
