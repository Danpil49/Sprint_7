package json_model.Order;

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

    public static String getMetroStationID(String metroName) {
        setUpOrderAPI();
        return given()
                .header("Content-type", "application/json")
                .queryParam("s", metroName)
                .get(metroStationAPI)
                .body()
                .as(MetroStation[].class)[0].getNumber();
    }

    public static Response createOrder(Order order) {
        return given()
                .header("Content-type", "application/json")
                .body(order)
                .post(orderCreateAPI);
    }

    public static Response getOrdersList() {
        return given()
                .header("Content-type", "application/json")
                .get(orderCreateAPI);
    }
    public static void setUpOrderAPI() {
        RestAssured.baseURI = "http://qa-scooter.praktikum-services.ru";
    }

    public static Response orderConfirm(String orderId, String courierId) {
        return given()
                .header("Content-type", "application/json")
                .queryParam("courierId", courierId)
                .put(orderConfirmAPI + orderId);
    }
    public static Response putOrderConfirmWithoutOrderId(String courierId) {
        return given()
                .header("Content-type", "application/json")
                .queryParam("courierId", courierId)
                .put(orderConfirmAPI);
    }
    public static Response orderConfirmWithoutCourierId(String orderId) {
        return given()
                .header("Content-type", "application/json")
                .put(orderConfirmAPI + orderId);
    }
    public static String getOrderIdByTrackStr(Order order) {
        return   given()
                .header("Content-type", "application/json")
                .queryParam("t", order.getTrack())
                .get(orderByTrack).as(SingleOrderInObject.class).getOrder().getId();
    }
    public static Response getOrderIdByTrackWithOrderObj(Order order) {
        return   given()
                .header("Content-type", "application/json")
                .queryParam("t", order.getTrack())
                .get(orderByTrack);
    }
    public static Response getOrderIdByTrackWithTrackID(String trackId) {
        return   given()
                .header("Content-type", "application/json")
                .queryParam("t", trackId)
                .get(orderByTrack);
    }
}
