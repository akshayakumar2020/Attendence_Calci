# 📊 Attendance Calculator

A web application built with **Java & Spring Boot** that helps students instantly check their attendance status and plan ahead — so they never fall below the required 75% threshold.

🔗 **Live Demo:** [attendence-calci.onrender.com](https://attendence-calci.onrender.com/)

---

## 🎯 What It Does

Students just enter their total classes held and classes attended — and the app instantly tells them:

- ✅ Their **current attendance percentage**
- 📈 How many more classes they need to attend to **reach 75%**
- 📉 How many classes they can **safely skip** without dropping below 75%

No more manually doing the math before every class!

---

## 🛠️ Tech Stack

| Layer      | Technology              |
|------------|-------------------------|
| Backend    | Java, Spring Boot, Maven |
| Frontend   | Thymeleaf, HTML, CSS    |
| Hosting    | Render                  |
| Version Control | GitHub             |

---

## 🚀 Getting Started (Run Locally)

### Prerequisites

Make sure you have the following installed:
- Java 17+
- Maven

### Steps

```bash
# 1. Clone the repository
git clone https://github.com/akshayakumar2020/Attendence_Calci.git

# 2. Navigate into the project
cd Attendence_Calci

# 3. Build the project
mvn clean install

# 4. Run the application
mvn spring-boot:run
```

Then open your browser and go to:
```
http://localhost:8080/calculate
```

---

## 📁 Project Structure

```
Attendence_Calci/
├── src/
│   ├── main/
│   │   ├── java/com/example/attendence_calci/
│   │   │   ├── AttendenceCalciApplication.java   # Main entry point
│   │   │   ├── controller/                        # Handles web requests
│   │   │   └── model/                             # Business logic
│   │   └── resources/
│   │       ├── templates/                         # Thymeleaf HTML views
│   │       └── application.properties             # App configuration
├── pom.xml                                        # Maven dependencies
└── README.md
```

---

## ☁️ Deployment

This project is deployed on **Render** using a pre-built `.jar` approach:

1. The project is built locally using `mvn clean package`
2. The resulting `.jar` file is pushed to GitHub
3. Render picks it up and runs it directly — bypassing cloud memory limits during the build phase

---

## 💡 Key Learnings

- Building and deploying a full Spring Boot web application end-to-end
- Troubleshooting cloud server memory limits and optimizing for Render's free tier
- Server-side rendering with Thymeleaf templates
- Managing environment variables and port bindings for cloud deployment

---

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you'd like to change.

---

## 📬 Connect

**Akshay Kumar**
- GitHub: [@akshayakumar2020](https://github.com/akshayakumar2020)

---

> Built with ❤️ as part of my backend development journey.
