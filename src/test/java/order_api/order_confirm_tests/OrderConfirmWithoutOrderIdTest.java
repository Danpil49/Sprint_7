package order_api.order_confirm_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import json_model.courier.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static json_model.courier.CourierClient.*;
import static json_model.order.OrderClient.*;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.hamcrest.Matchers.equalTo;

public class OrderConfirmWithoutOrderIdTest {
    private String courierId;
    private final Courier courier = courierSasha;


    @Before
    public void setUp() {
        setUpOrderAPI();
        createCourier(courier);
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
                .statusCode(SC_NOT_FOUND);
    }

    @After
    public void courierCleanUp() {
        deleteCourier(courierSasha);
    }
}
