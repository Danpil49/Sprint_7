package courier_api.courier_creation_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static json_model.Courier.CourierClient.*;
import static org.hamcrest.Matchers.equalTo;

public class DuplicateCourierCreationTest {
    @Before
    public void setUp() {
        setUpAPI();
        create(courierSasha);
    }

    @Test
    @DisplayName("Проверка создания дубликата уже существующего курьера (/api/v1/courier)")
    @Description("Проверка создания дубликата курьера с полями: login = 'regular_courier', password = '1111', firstName = 'Sasha'. Должно выдать сообщение об ошибке 'Этот логин уже используется. Попробуйте другой.' и статус код = 409")
    public void createDuplicateCourier() {
        create(courierSasha)
                .then()
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(409);
    }

    @After
    public void courierCleanUp() {
        delete(courierSasha);
    }
}
