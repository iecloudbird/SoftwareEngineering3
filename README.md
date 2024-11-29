# Newspaper Delivery Automation System
## Software Engineering 3

This repository contains a Newspaper Delivery Automation System designed to streamline the process of managing newspaper deliveries. Follow the steps below to deploy the system locally.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Deployment Steps](#deployment-steps)
- [How to Run](#how-to-run)

## Prerequisites
- XAMPP installed on your machine.
- Java Development Kit (JDK) installed.
- An IDE (Eclipse or any preferred Java IDE).
- MySQL Workbench for executing SQL scripts.

## Deployment Steps

1. **Start MySQL Server**
   - Open XAMPP and start the MySQL server.
   - Ensure that the configuration matches the following settings:
     - **Port:** `3307`
     - **Database Name:** `newsagent`
     - **Database User:** `root`
     - **Password:** *(leave blank)*

2. **Setup Database**
   - Navigate to the `database-files` folder in this repository.
   - Copy and paste the SQL code from `database.sql` into MySQL Workbench.
   - Execute the script to set up the `newsagent` database.

3. **Run the Application**
   - Open your IDE (e.g., Eclipse).
   - Run the `CommandLine.java` file.
   - The application should connect to the MySQL server and display command lines as intended.

## How to Run

Make sure you follow the deployment steps carefully. After successfully running the application, you should be able to interact with the Newspaper Delivery Automation System through the command line interface.

---
