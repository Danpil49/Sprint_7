package order_api.order_confirm_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import json_model.order.Order;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static json_model.order.OrderClient.*;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.hamcrest.Matchers.equalTo;

public class OrderConfirmWithWrongCourierIDTest {
    private String orderId;
    private final String courierId = String.valueOf(((new Random()).nextInt(100_000_000)+10_000_000));


    @Before
    public void setUp() {
        setUpOrderAPI();
        Order order = createOrder(orderForNarutoWithoutColor).as(Order.class);
        orderId = getOrderIdByTrackStr(order);
    }

    @Test
    @DisplayName("Проверка работы ручки принятия заказа курьером с несуществующим id ('/api/v1/orders/track')")
    @Description("Должно выдать сообщение об ошибке 'Курьера с таким id не существует' и статус код = 404")
    public void orderConfirmWithWrongCourierID() {
        orderConfirm(orderId, courierId)
                .then()
                .assertThat()
                .body("message", equalTo("Курьера с таким id не существует"))
                .and()
                .statusCode(SC_NOT_FOUND);
    }
}
