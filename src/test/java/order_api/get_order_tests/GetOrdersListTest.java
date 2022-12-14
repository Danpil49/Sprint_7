package order_api.get_order_tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import json_model.order.Orders;
import org.junit.Before;
import org.junit.Test;

import static json_model.order.OrderClient.*;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

public class GetOrdersListTest {
    @Before
    public void setUp() {
        setUpOrderAPI();
    }

    @Test
    @DisplayName("Проверка что список заказов не пуст ('/api/v1/orders')")
    public void getOrdersListTest() {
        Response response = getOrdersList();
        response.then().statusCode(SC_OK);

        int responseLength = response.as(Orders.class).getOrders().size();
        assertThat(responseLength, greaterThan(0));
    }
}
