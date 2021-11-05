# Customer-Phones-API
Backend MicroService for Customer Phones by using Spring Boot.

### Server Requirements

### Installation steps:

Steps to run the app:
```sh
git clone https://github.com/mohamedelnaggar/customer-phones-api.git
cd customer-phones-api
mvn clean package
docker build -t customer-phones-api:latest  .
docker run -p 8080:8080 customer-phones-api:latest
```

Now you should be able to access our UI app [http://localhost:8080/swagger-ui/index.html] from your browser


