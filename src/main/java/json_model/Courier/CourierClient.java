package json_model.Courier;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {
     public  static Courier courierSasha = CourierGenerator.getCourierSasha();
     public  static Courier courierMisha = CourierGenerator.getCourierMisha();
     public  static String courierCreationAPI = "/api/v1/courier";
     public  static String courierLoginAPI = "/api/v1/courier/login";
     private static final String courierDeleteAPI = "/api/v1/courier/";

     public static Response create(Courier courier) {
         return given()
                 .header("Content-type", "application/json")
                 .body(courier)
                 .post(courierCreationAPI);
     }

     public static Response login(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .post(courierLoginAPI);
     }

     public static Integer getCourierID(Courier courier) {
         return login(courier)
                 .body()
                 .as(Courier.class).getId();
     }

    public static Response delete(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .delete(
                        courierDeleteAPIStringBuild(
                                getCourierID(courier)
                ));
    }

    public static Response wrongDelete(Courier courier) {
         return given()
                 .header("Content-type", "application/json")
                 .delete(courierDeleteAPI);
    }
    public static Response wrongDelete(Courier courier, String id) {
        return given()
                .header("Content-type", "application/json")
                .delete(courierDeleteAPI + id);
    }

     public static void setUpAPI() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

     public static String courierDeleteAPIStringBuild(Integer courierID) {
        return courierDeleteAPI + courierID;
    }
}

