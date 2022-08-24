package order_api.order_creation_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import json_model.order.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static json_model.order.OrderClient.*;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class BlackOrGreyOrderCreationTest {
    private final Order order;

    public BlackOrGreyOrderCreationTest(Order order) {
        this.order = order;
    }

    @Parameterized.Parameters()
    public static Object[][] getTextData() {
        return new Object[][] {
                {orderForSashaBlackColor},
                {orderForMishaGreyColor}
        };
    }

    @Test
    @DisplayName("Параметризованный тест. Проверка создания заказа с разными цветами ('/api/v1/orders')")
    @Description("Параметризованный тест для заказа самоката с цветом BLACK и заказа с цветом GREY")
    public void blackOrGreyOrderCreation() {
        createOrder(order)
                .then()
                .assertThat().body("track", notNullValue())
                .and()
                .statusCode(SC_CREATED);
    }
}
