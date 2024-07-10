# Just Do It To-Do / Task Manager App

### 📖 Information

Just Do It is a to-do app and task manager. It is inspired by WunderList. You can start using it by creating a user.
You can categorize your tasks for your user. You can add tasks to the categories you have created. You can favorite your tasks. You can update your tasks to completed status when you complete them. You can edit all your tasks, categories and user information. You can see all of your tasks or categorized by listed categories.

### Technologies

---
- Java 21
- Spring Boot 3.3.1
- Spring Data Couchbase
- Spring Security
- jsonwebtoken
- Couchbase
- Restful API
- Lombok
- Maven
- Junit5
- Mockito
- Unit Tests
- Swagger
- Docker
- Docker Compose

### Docker Run
The application can be built and run by the `Docker` engine. The `Dockerfile` has multistage build, so you do not need to build and run separately.

Please follow directions shown below in order to build and run the application with Docker Compose file;

```sh
$ cd just-do-it
$ docker-compose up -d
```

If you change anything in the project and run it on Docker, you can also use this command shown below

```sh
$ cd just-do-it
$ docker-compose up --build
```

---
### Maven Run
To build and run the application with `Maven`, please follow the directions shown below;

```sh
$ cd just-do-it
$ mvn clean install
$ mvn spring-boot:run
```
---
### Prerequisites

#### If you are running the application for the first time, follow these steps:

1. **Visit** `127.0.0.1:8091` **on your browser for Couchbase UI**

2. **Create an account in Couchbase**
    - **Cluster Name:** `localhost`
    - **Username:** `user`
    - **Password:** `asdf1234`

3. **Create a bucket named `justdoit` in Couchbase**

4. **Create a primary index in Couchbase with the following query:**
    ```sql
    CREATE PRIMARY INDEX ON justdoit;
    ```

