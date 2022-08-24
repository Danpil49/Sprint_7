package order_api.get_order_tests.get_order_by_trackId;

import io.qameta.allure.junit4.DisplayName;
import json_model.order.Order;
import org.junit.Before;
import org.junit.Test;

import static json_model.order.OrderClient.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrderByTrackIDTest {
    private Order order;
    @Before
    public void setUp() {
        setUpOrderAPI();
        order = createOrder(orderForNarutoWithoutColor).as(Order.class);
    }

    @Test
    @DisplayName("Проверка ручки которая возвращает заказ по его треку ('/api/v1/orders/track')")
    public void getOrderByTrackID() {
        getOrderIdByTrackWithOrderObj(order)
                .then()
                .assertThat()
                .body("order.id", notNullValue())
                .and()
                .statusCode(SC_OK);
    }
}
