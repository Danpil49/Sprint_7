package order_api.order_confirm_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import json_model.Courier.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static json_model.Courier.CourierClient.*;
import static json_model.Order.OrderClient.*;
import static org.hamcrest.Matchers.equalTo;

public class OrderConfirmWithoutOrderIdTest {
    private String courierId;
    private final Courier courier = courierSasha;


    @Before
    public void setUp() {
        setUpOrderAPI();
        create(courier);
        courierId = String.valueOf(getCourierID(courier));
    }

    @Test
    @DisplayName("Проверка работы ручки принятия курьером заказа без указания id заказа ('/api/v1/orders/track')")
    @Description("Должно выдать сообщение об ошибке 'Not Found.' и статус код = 404")
    public void orderConfirmWithoutOrderId() {
        putOrderConfirmWithoutOrderId(courierId)
                .then()
                .assertThat()
                .body("message", equalTo("Not Found."))
                .and()
                .statusCode(404);
    }

    @After
    public void courierCleanUp() {
        delete(courierSasha);
    }
}
