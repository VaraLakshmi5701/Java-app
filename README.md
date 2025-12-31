#  Java Spring Boot CI/CD with Jenkins & Docker

##  Project Overview

This project demonstrates a **complete end-to-end CI/CD pipeline** for a **Java Spring Boot application**, starting from **source code in GitHub** and ending with a **Dockerized application running on an EC2 instance**.

The goal of this project is to showcase **real-world DevOps practices** including:

* Source control with GitHub
* Build automation using Maven
* Static code analysis using SonarQube
* Continuous Integration with Jenkins
* Containerization using Docker
* Image storage using Docker Hub
* Deployment on a Linux server (AWS EC2)

---

##  Architecture Flow

```
Developer
   |
   |  (git push)
   ▼
GitHub Repository
   |
   |  Jenkins Pipeline Trigger
   ▼
Jenkins CI Server
   |
   |-- Maven Build (JAR)
   |-- SonarQube Code Quality Check
   |-- Docker Image Build
   |-- Push Image to Docker Hub
   ▼
Docker Hub
   |
   |  docker pull
   ▼
AWS EC2 Instance
   |
   |-- Docker Container Running
   ▼
Spring Boot Application (Port 8080)
```

---

##  Tech Stack Used

| Category         | Tool               |
| ---------------- | ------------------ |
| Language         | Java 17            |
| Framework        | Spring Boot        |
| Build Tool       | Maven              |
| CI/CD            | Jenkins            |
| Code Quality     | SonarQube          |
| Containerization | Docker             |
| Image Registry   | Docker Hub         |
| OS               | Linux (Amazon EC2) |

---

##  Project Structure

```
java-docker-app/
├── Dockerfile
├── Jenkinsfile
├── README.md
├── pom.xml
└── src
    └── main
        └── java
            └── com/example/demo
                ├── DemoApplication.java
                └── controller
                    └── HomeController.java
```

---

##  Application Details

This is a **simple Spring Boot REST application** with the following endpoints:

| Endpoint  | Description                         |
| --------- | ----------------------------------- |
| `/`       | Displays application status message |
| `/health` | Health check endpoint               |

### Sample Output

```text
Java Spring Boot App running in Docker 🚀
```

---

##  Build Process (Maven)

The application is packaged as an **executable JAR file** using Maven:

```bash
mvn clean package
```

Generated artifact:

```
target/java-docker-app-1.0.0.jar
```

---

##  Docker Containerization

### Dockerfile Explanation

* Uses **Eclipse Temurin OpenJDK 17** (lightweight & production-ready)
* Copies the built JAR into the container
* Exposes port `8080`
* Runs the application using `java -jar`

```dockerfile
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/java-docker-app-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Build Docker Image

```bash
docker build -t varalakshmi5701/java-app:v1 .
```

### Run Container

```bash
docker run -d --name java-app -p 1234:8080 varalakshmi5701/java-app:v1
```

---

##  Jenkins CI/CD Pipeline

### Jenkins Pipeline Stages

1. **Checkout Code** – Pulls source code from GitHub
2. **Build** – Builds JAR using Maven
3. **Code Quality Analysis** – Runs SonarQube scan
4. **Docker Build** – Creates Docker image
5. **Docker Push** – Pushes image to Docker Hub
6. **Container** – Creation of application container

<img width="1886" height="880" alt="image" src="https://github.com/user-attachments/assets/505a6cfc-ed6b-4812-b733-a7daceed8b29" />


##  Credentials Management

* Docker Hub credentials are stored securely in **Jenkins Credentials**
* Credentials Type: `Username with Password`
* Used inside pipeline using `withCredentials`

---

##  Deployment & Access

Once the container is running on EC2, the application is accessible at:

```
http://<EC2_PUBLIC_IP>:1234/
http://<EC2_PUBLIC_IP>:1234/health
```

---

##  Common Issues & Fixes

### 404 Whitelabel Error

✔ Happens when no controller is mapped
✔ Fixed by adding `@RestController` with `/` mapping

---

### Docker Login 404 Error

✔ Caused by wrong registry URL
✔ Fixed by using:

```bash
docker login
```

or

```bash
https://index.docker.io/v1/
```

---

### Port Not Accessible

✔ Ensure EC2 Security Group allows inbound traffic on port `1234`

---

##  Key DevOps Learnings

* Built real CI/CD pipeline using Jenkins
* Containerized Java applications using Docker
* Managed secrets securely
* Deployed applications on cloud infrastructure
* Debugged real-world production issues

---

##  Author

**Vara Lakshmi**
DevOps Engineer (3 years experience)
Skills: AWS, Jenkins, Docker, Kubernetes, Terraform, Ansible, CI/CD, Monitoring

---

##  Future Enhancements

* Kubernetes deployment
* ArgoCD GitOps
* HTTPS using Nginx
* Prometheus & Grafana monitoring
* Blue-Green deployment

---

