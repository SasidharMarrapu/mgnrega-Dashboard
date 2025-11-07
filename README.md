# 🌾 MGNREGA District Performance Dashboard

A full-stack web application that visualizes **district-wise performance data** of the **Mahatma Gandhi National Rural Employment Guarantee Act (MGNREGA)** for the state of **Andhra Pradesh** 🇮🇳.  
This project empowers citizens, researchers, and officials to access **transparent and easy-to-understand** information about MGNREGA performance.

---

## 🧠 Project Overview

MGNREGA is one of India’s largest rural employment programs.  
Although the Government provides data through open APIs, it is **difficult for non-technical users** to interpret it.

This project solves that problem by providing:
- A **user-friendly dashboard** that shows district-level data such as *jobs created, wages paid, and payment delays*.
- An option to **auto-detect the user’s district** using GPS location.
- Simple and clean data visualization designed for accessibility, even for low-literacy users.

---

## 🎯 Objectives

- Simplify access to government open data.
- Present MGNREGA data in an **interactive and visual** format.
- Detect the user’s location automatically and show relevant district data.
- Build a **production-ready**, scalable solution with MySQL and Spring Boot.

---

## 🧩 System Architecture

[Frontend: HTML, CSS, JavaScript]
↓
[Backend: Spring Boot REST API]
↓
[Database: MySQL (Districts + Monthly Performance)]

---

## 🖥️ Tech Stack

| Layer | Technology |
|-------|-------------|
| **Frontend** | HTML, CSS, JavaScript |
| **Backend** | Java (Spring Boot Framework) |
| **Database** | MySQL |
| **API Type** | RESTful APIs |
| **Geolocation API** | OpenStreetMap Nominatim |
| **Hosting (Optional)** | Railway / Render / GitHub Pages |

---

## ⚙️ Features

✅ Fetch all districts dynamically from the backend  
✅ View **monthly performance** for each district  
✅ Responsive, modern UI using HTML + CSS  
✅ **Auto-detect district** using device location (GPS)  
✅ Works seamlessly on desktop and mobile  
✅ Backend integrated with **MySQL database**

---

## 🗃️ Database Schema

### Table: `districts`
| Column | Type | Description |
|---------|------|-------------|
| id | INT (PK) | Auto-increment ID |
| name | VARCHAR | District name |
| state | VARCHAR | State name |

### Table: `monthly_performance`
| Column | Type | Description |
|---------|------|-------------|
| id | INT (PK) | Auto-increment ID |
| district_id | INT (FK) | Linked to `districts.id` |
| year | INT | Year of record |
| month | INT | Month number |
| jobs_created | INT | Number of jobs created |
| households_worked | INT | Households worked |
| wages_paid | DOUBLE | Total wages paid |
| payment_delay_days | DOUBLE | Avg. payment delay days |

---

## 📡 API Endpoints

| Method | Endpoint | Description |
|--------|-----------|-------------|
| `GET` | `/api/districts` | Fetch all districts |
| `GET` | `/api/performance/{districtId}` | Fetch performance data for a district |

---

## 📍 Auto District Detection (Geolocation)

The app uses your **browser’s GPS** and **OpenStreetMap Nominatim API**  
to automatically detect your current location and display the corresponding district’s MGNREGA data.

Example:
> 📍 “Detected District: Visakhapatnam”  
> → Automatically loads Visakhapatanam’s performance details.

---

## 🧰 Installation & Setup

### 1️⃣ Clone Repository

```bash
git clone https://github.com/yourusername/mgnrega-dashboard.git
cd mgnrega-dashboard
```

---
### 2. Backend Setup

Open in Eclipse or IntelliJ.

Configure your application.properties
```bash
spring.datasource.url=jdbc:mysql://localhost:3306/mgnrega
spring.datasource.username=root
spring.datasource.password=your_password
server.port=8082
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Run the backend:
```bash

mvn spring-boot:run

```

---


### 🧩 3. MySQL Setup
## 🏗️ Create Database

```
CREATE DATABASE mgnrega;
USE mgnrega;
```

- 🧱 Create Tables
  
- 1️⃣ districts Table

Stores basic information about all districts and their state.

```
CREATE TABLE districts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL
);
```

2️⃣ monthly_performance Table

Stores monthly data like jobs created, wages paid, and households worked —
linked to a district via district_id.

```
CREATE TABLE monthly_performance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    district_id BIGINT,
    year INT NOT NULL,
    month INT NOT NULL,
    jobs_created INT NOT NULL,
    households_worked INT NOT NULL,
    wages_paid DOUBLE NOT NULL,
    payment_delay_days DOUBLE NOT NULL,
    last_updated DATE DEFAULT (CURRENT_DATE),
    FOREIGN KEY (district_id) REFERENCES districts(id)
);
```

📥 Insert Initial Data
```
INSERT INTO districts (name, state) VALUES
('Visakhapatnam', 'Andhra Pradesh'),
('Guntur', 'Andhra Pradesh'),
('Srikakulam', 'Andhra Pradesh'),
('Kurnool', 'Andhra Pradesh'),
('Prakasam', 'Andhra Pradesh'),
('Vizianagaram', 'Andhra Pradesh');
```
```
INSERT INTO monthly_performance
(district_id, year, month, jobs_created, households_worked, wages_paid, payment_delay_days)
VALUES
(1, 2024, 10, 350, 120, 50000, 3),
(1, 2024, 11, 400, 135, 54000, 2),
(2, 2024, 10, 380, 140, 58000, 4),
(2, 2024, 11, 410, 150, 60000, 1),
(3, 2024, 10, 300, 110, 45000, 5),
(3, 2024, 11, 320, 115, 47000, 3),
(4, 2024, 10, 370, 125, 52000, 2),
(5, 2024, 10, 340, 118, 49000, 3),
(6, 2024, 11, 410, 132, 56000, 2);
```
---

### 4️.Frontend Setup

- Inside your repo, you should have:

index.html
style.css
script.js

- Open index.html in your browser.

Make sure the API URL in script.js matches your backend:
```
const BASE_URL = "http://localhost:8082/api";
```
---

### 🚀 Future Enhancements
- Add charts and graphs (Chart.js) for data visualization
- District comparison feature
- Year and month filters
- Support for multiple states
- Multi-language support for rural users

### 🏁 Conclusion

- This project showcases how open government data can be transformed into a citizen-friendly digital dashboard.
- With Spring Boot, MySQL, and interactive frontend technologies, it empowers users to explore MGNREGA data in a transparent, visual, and engaging way.

### 👨‍💻 Author

Sasidhar Marrapu
Java Full Stack Developer | AI & Data Enthusiast

📧 gmail: sasidharmarrapu674@gmail.com

🌐 LinkedIn: https://www.linkedin.com/in/sasidharmarrapu



## 🏠 Dashboard View
![Dashboard Screenshot](https://github.com/SasidharMarrapu/mgnrega-Dashboard/blob/master/mgnrega-dashboard/assets/dashboard.png?raw=true)

---

## 📊 District Performance Example
![Performance Table](https://github.com/SasidharMarrapu/mgnrega-Dashboard/blob/master/mgnrega-dashboard/assets/performance.png?raw=true)

---

## 🧩 System Architecture
![Architecture Diagram](https://github.com/SasidharMarrapu/mgnrega-Dashboard/blob/master/mgnrega-dashboard/assets/Architecture.png?raw=true)

