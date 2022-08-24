package order_api.order_creation_tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static json_model.order.OrderClient.*;
import static org.apache.http.HttpStatus.SC_CREATED;
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
                .statusCode(SC_CREATED);
    }
}
