package order_api.order_confirm_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import json_model.order.Order;
import org.junit.Before;
import org.junit.Test;

import static json_model.order.OrderClient.*;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.equalTo;

public class OrderConfirmWithoutCourierIDTest {
    private String orderId;


    @Before
    public void setUp() {
        setUpOrderAPI();
        Order order = createOrder(orderForNarutoWithoutColor).as(Order.class);
        orderId = getOrderIdByTrackStr(order);
    }

    @Test
    @DisplayName("Проверка работы ручки принятия заказа буз указания id курьера ('/api/v1/orders/track')")
    @Description("Должно выдать сообщение об ошибке 'Недостаточно данных для поиска' и статус код = 400")
    public void orderConfirmWithoutCourierID() {
        orderConfirmWithoutCourierId(orderId)
                .then()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для поиска"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }
}
