package courier_api.courier_creation_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CONFLICT;
import static json_model.courier.CourierClient.*;
import static org.hamcrest.Matchers.equalTo;

public class DuplicateLoginCourierTest {
    @Before
    public void setUp() {
        setUpAPI();
        createCourier(courierSasha);
    }

    @Test
    @DisplayName("Проверка создания курьера с уже занятым логином (/api/v1/courier)")
    @Description("Проверка создания курьера с уже занятым логином , но разными полями password и firstName: login = 'regular_courier'. Должно выдать сообщение об ошибке 'Этот логин уже используется. Попробуйте другой.' и статус код = 409")
    public void createLoginDuplicateCourier() {
        createCourier(courierMisha)
                .then()
                .assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."))
                .and()
                .statusCode(SC_CONFLICT);
    }

    @After
    public void courierCleanUp() {
        deleteCourier(courierSasha);
    }
}
