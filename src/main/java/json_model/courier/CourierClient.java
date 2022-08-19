package json_model.courier;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CourierClient {
     public  static final Courier courierSasha = CourierGenerator.getCourierSasha();
     public  static final Courier courierMisha = CourierGenerator.getCourierMisha();
     public  static final String courierCreationAPI = "/api/v1/courier";
     public  static final String courierLoginAPI = "/api/v1/courier/login";
     private static final String courierDeleteAPI = "/api/v1/courier/";

     @Step("Создаем курьера")
     public static Response createCourier(Courier courier) {
         return given()
                 .header("Content-type", "application/json")
                 .body(courier)
                 .post(courierCreationAPI);
     }

     @Step("Логинимся курьером")
     public static Response login(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .post(courierLoginAPI);
     }

     @Step("Получаем ID курьера")
     public static Integer getCourierID(Courier courier) {
         return login(courier)
                 .body()
                 .as(Courier.class).getId();
     }
    @Step("Удаляем курьера, автоматически находя его ID")
    public static Response deleteCourier(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .delete(
                        courierDeleteAPIStringBuild(
                                getCourierID(courier)
                ));
    }
    @Step("Метод неправильного удаления курьера (не указываем его ID)")
    public static Response wrongDelete() {
         return given()
                 .header("Content-type", "application/json")
                 .delete(courierDeleteAPI);
    }
    @Step("Удаляем курьера указывая его ID вручную")
    public static Response deleteCourierManually(String id) {
        return given()
                .header("Content-type", "application/json")
                .delete(courierDeleteAPI + id);
    }
    @Step("Указываем URI для RestAssured")
     public static void setUpAPI() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }
    @Step("Подготавливаем запрос на удаление к ручке")
     public static String courierDeleteAPIStringBuild(Integer courierID) {
        return courierDeleteAPI + courierID;
    }
}

