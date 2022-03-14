# ReadingIsGood

ReadingIsGood is an online books retail firm which operates only on the Internet. Main
target of ReadingIsGood is to deliver books from its one centralized warehouse to their
customers within the same day. 

### Design
All the APIs as per design broken down into 3 controllers:

APIs
* CustomerController - For adding customer and stats related APIs.
* OrderController - For creating new orders.
* BookController - Adding new book to record or updating there stock.

DB
* SQL DB used since data was `structured` already and there was no complexity in its structure.
* Other reason since the requirement includes `transactional` behaviour for db updates so SQL again become better choice.

Technologies/Libraries Used
* JDK 11 (Amazon OpenJDK used for dev)
* Spring boot Starter Web
* Spring Data JPA/ Data Rest
* Spring Security for Basic Auth
* H2 In Memory database used for development can be migrated to any db of choice.
* Lombok

Note: For security currently on basic auth use with username, password as `(user, password)` can easily be upgraded further if needed.

### How to set up

Via IDE
 * Import in any IDE of choice
 * Go to `ReadingIsGoodApplication.java` and run main method.
 * Visit 'localhost:8080' on browser and enter username: `user` and password: `password`.
 * Try exploring APIs via HAL Explorer.
  OR
 * Open Postman and Import collection and Explore APIs via that.

Via Maven
 * Import in any location of choice.
 * Run command to start application `mvn clean spring-boot:run`.
 * Visit 'localhost:8080' on browser and enter username: `user` and password: `password`.
 * Try exploring APIs via HAL Explorer.
  OR
 * Open Postman and Import collection and Explore APIs via that.
 
Or you can compile project to Jar via `mvn package` and run jar using java. 

### Endpoints/Usage / Snapshot

You can explore all the available APIs via Postman Collection included in the code or HAL Explorer as below:
![Hal Explorer](./src/main/resources/static/sample2.png)

### Test Coverage
All controller test covers more than 70% of the code, Snapshot attached.
![Coverage Snapshot](./src/main/resources/static/sample1.png)