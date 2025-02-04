import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TaskApiTest {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/api";
    }

    @Test
    public void testCreateTask() {
        String requestBody = "{ \"title\": \"New Task\", \"description\": \"Task description\" }";

        given()
            .contentType(ContentType.JSON)
            .body(requestBody)
        .when()
            .post("/tasks")
        .then()
            .statusCode(201)
            .body("id", notNullValue())
            .body("title", equalTo("New Task"))
            .body("description", equalTo("Task description"));
    }

    @Test
    public void testGetTaskById() {
        int taskId = 1; // Assume this task ID exists

        given()
            .pathParam("id", taskId)
        .when()
            .get("/tasks/{id}")
        .then()
            .statusCode(200)
            .body("id", equalTo(taskId))
            .body("title", notNullValue())
            .body("description", notNullValue());
    }

    @Test
    public void testUpdateTaskById() {
        int taskId = 1; // Assume this task ID exists
        String requestBody = "{ \"title\": \"Updated Task\", \"description\": \"Updated description\" }";

        given()
            .contentType(ContentType.JSON)
            .pathParam("id", taskId)
            .body(requestBody)
        .when()
            .put("/tasks/{id}")
        .then()
            .statusCode(200)
            .body("id", equalTo(taskId))
            .body("title", equalTo("Updated Task"))
            .body("description", equalTo("Updated description"));
    }

    @Test
    public void testDeleteTaskById() {
        int taskId = 1; // Assume this task ID exists

        given()
            .pathParam("id", taskId)
        .when()
            .delete("/tasks/{id}")
        .then()
            .statusCode(204);
    }
}