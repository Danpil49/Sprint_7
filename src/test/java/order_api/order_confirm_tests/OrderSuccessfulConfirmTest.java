package order_api.order_confirm_tests;

import io.qameta.allure.junit4.DisplayName;
import json_model.Courier.Courier;
import json_model.Order.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static json_model.Courier.CourierClient.*;
import static json_model.Order.OrderClient.*;
import static org.hamcrest.Matchers.equalTo;

public class OrderSuccessfulConfirmTest {
    private String orderId;
    private String courierId;
    private final Courier courier = courierSasha;


    @Before
    public void setUp() {
        setUpOrderAPI();
        Order order = createOrder(orderForNarutoWithoutColor).as(Order.class);
        orderId = getOrderIdByTrackStr(order);
        create(courier);
        courierId = String.valueOf(getCourierID(courier));
    }

    @Test
    @DisplayName("Проверка работы ручки принятия курьером заказа ('/api/v1/orders/track')")
    public void orderSuccessfulConfirm() {
        orderConfirm(orderId, courierId)
                .then()
                .assertThat()
                .body("ok", equalTo(Boolean.TRUE))
                .and()
                .statusCode(200);
    }

    @After
    public void courierCleanUp() {
        delete(courierSasha);
    }
}
