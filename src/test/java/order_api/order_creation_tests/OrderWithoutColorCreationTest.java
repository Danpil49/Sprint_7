package order_api.order_creation_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static json_model.Order.OrderClient.*;
import static org.hamcrest.Matchers.notNullValue;

public class OrderWithoutColorCreationTest {

    @Before
    public void setUp() {
        setUpOrderAPI();
    }

    @Test
    @DisplayName("Проверка создания заказа без указания цвета ('/api/v1/orders')")
    public void orderWithoutColorCreation() {
        createOrder(orderForNarutoWithoutColor)
                .then()
                .assertThat().body("track", notNullValue())
                .and()
                .statusCode(201);
    }
}
