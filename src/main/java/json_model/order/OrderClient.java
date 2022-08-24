package json_model.order;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class OrderClient {
    public static Order orderForNarutoBothColors = OrderGenerator.getDefaultScooterOrder(getMetroStationID("Сокольники"));
    public static Order orderForNarutoWithoutColor = OrderGenerator.getDefaultScooterOrderWithoutColor(getMetroStationID("Сокольники"));
    public static Order orderForSashaBlackColor = OrderGenerator.getBlackScooterOrder(getMetroStationID("Тверская"));
    public static Order orderForMishaGreyColor = OrderGenerator.getGreyScooterOrder(getMetroStationID("Тверская"));
    private static final String orderCreateAPI = "/api/v1/orders";
    private static final String metroStationAPI = "/api/v1/stations/search";
    private static final String orderConfirmAPI = "/api/v1/orders/accept/";
    private static final String orderByTrack = "/api/v1/orders/track";

    @Step("Получаем ID станции метро")
    public static String getMetroStationID(String metroName) {
        setUpOrderAPI();
        return given()
                .header("Content-type", "application/json")
                .queryParam("s", metroName)
                .get(metroStationAPI)
                .body()
                .as(MetroStation[].class)[0].getNumber();
    }

    @Step("Создаем заказ")
    public static Response createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .post(orderCreateAPI);
    }

    @Step("Получаем список заказов")
    public static Response getOrdersList() {
        return given()
                .header("Content-type", "application/json")
                .get(orderCreateAPI);
    }
    @Step("Подготавливаем переменную URI для RestAssured")
    public static void setUpOrderAPI() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    @Step("Подтверждаем заказ")
    public static Response orderConfirm(String orderId, String courierId) {
        return given()
                .header("Content-type", "application/json")
                .queryParam("courierId", courierId)
                .put(orderConfirmAPI + orderId);
    }
    @Step("Подтверждаем заказ неправильно, не указывая ID заказа")
    public static Response putOrderConfirmWithoutOrderId(String courierId) {
        return given()
                .header("Content-type", "application/json")
                .queryParam("courierId", courierId)
                .put(orderConfirmAPI);
    }
    @Step("Подтверждаем заказ неправильно, не указывая ID курьера")
    public static Response orderConfirmWithoutCourierId(String orderId) {
        return given()
                .header("Content-type", "application/json")
                .put(orderConfirmAPI + orderId);
    }
    @Step("Получаем ID заказа по его TrackID из объекта Order")
    public static String getOrderIdByTrackStr(Order order) {
        return   given()
                .header("Content-type", "application/json")
                .queryParam("t", order.getTrack())
                .get(orderByTrack).as(SingleOrderInObject.class).getOrder().getId();
    }
    @Step("Получаем полную информацию о заказе по его TrackID")
    public static Response getOrderIdByTrackWithOrderObj(Order order) {
        return   given()
                .header("Content-type", "application/json")
                .queryParam("t", order.getTrack())
                .get(orderByTrack);
    }
    @Step("Получаем ID заказа по его TrackID")
    public static Response getOrderIdByTrackWithTrackID(String trackId) {
        return   given()
                .header("Content-type", "application/json")
                .queryParam("t", trackId)
                .get(orderByTrack);
    }
}
