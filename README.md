# Day-to-Day Expense Tracker

### Welcome to the Day-to-Day Expense Tracker, a web application designed to help you keep track of your daily expenses effortlessly. This application is built using React for the frontend and Spring Boot for the backend, with MySQL as the database.



___
___


#### Features

+ User Authentication: Register and log in to manage your expenses securely.
+ Expense Management: Add, update, and delete your daily expenses.
+ Expense Categories: Organize your expenses into categories for better tracking.

#### Technologies Used
+ Frontend: React.js
+ Backend: Spring Boot
+ Database: MySQL
+ Version Control: Git

#### Getting Started

+ Node.js and npm installed on your machine
+ Java Development Kit (JDK) 17 installe
+ MySQL database installed and running
+ Maven installed

#### Installation

1. Clone the repository:

       git clone https://github.com/pasanchamikara99/finance_tracker.git
       cd expense-tracker
2. Setup the Backend:
    
      a. Navigate to the backend directory:

       cd backend

     b. Update application.properties with your MySQL configuration:
   
       spring.datasource.url=jdbc:mysql://localhost:3306/expense_tracker
       spring.datasource.username=your-username
       spring.datasource.password=your-password
       spring.jpa.hibernate.ddl-auto=update

    c. Build and run the Spring Boot application:

       mvn clean install
       mvn spring-boot:run

3. Setup the Frontend:
    
     a. Navigate to the frontend directory:

       cd frontend

    b. Install the dependencies:

       npm install

    c. Start the React development server:

       npm run dev 

4. Access the Application:

   Open your browser and navigate to http://localhost:3000.


#### Technologies Used

1. Register: Create a new account to start tracking your expenses.
2. Login: Log in with your credentials.
3. Add Expense: Add new expenses with details such as amount, category, and description.
4. View Expenses: View all your expenses in a list and get a summary.


