package courier_api.courier_creation_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static json_model.Courier.CourierClient.*;
import static org.hamcrest.Matchers.equalTo;

public class UniqueCourierCreationTest {

    @Before
    public void setUp() {
        setUpAPI();
    }

    @Test
    @DisplayName("Проверка создания курьера (/api/v1/courier)")
    @Description("Проверка создания курьера с полями: login = 'regular_courier', password = '1111', firstName = 'Sasha'")
    public void createUniqueCourier() {
        create(courierSasha)
                .then()
                .assertThat().body("ok", equalTo(true))
                .and()
                .statusCode(201);
    }

    @After
    public void courierCleanUp() {
        delete(courierSasha);
    }
}
