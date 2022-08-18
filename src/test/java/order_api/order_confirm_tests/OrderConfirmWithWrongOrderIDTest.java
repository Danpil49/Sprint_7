package order_api.order_confirm_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import json_model.Courier.Courier;
import json_model.Order.Order;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static json_model.Courier.CourierClient.*;
import static json_model.Order.OrderClient.*;
import static org.hamcrest.Matchers.equalTo;

public class OrderConfirmWithWrongOrderIDTest {
    private final String orderId = String.valueOf(((new Random()).nextInt(100_000_000)+10_000_000));
    private String courierId;
    private final Courier courier = courierSasha;


    @Before
    public void setUp() {
        setUpOrderAPI();
        create(courier);
        courierId = String.valueOf(getCourierID(courier));
    }

    @Test
    @DisplayName("Проверка работы ручки принятия курьером заказа ('/api/v1/orders/track')")
    @Description("Должно выдать сообщение об ошибке 'Заказа с таким id не существует' и статус код = 404")
    public void orderConfirmWithWrongOrderID() {
        orderConfirm(orderId, courierId)
                .then()
                .assertThat()
                .body("message", equalTo("Заказа с таким id не существует"))
                .and()
                .statusCode(404);
    }

    @After
    public void courierCleanUp() {
        delete(courierSasha);
    }
}
