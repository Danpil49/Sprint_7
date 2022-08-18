package courier_api.courier_login_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static json_model.Courier.CourierClient.*;
import static org.hamcrest.Matchers.notNullValue;

public class LoginExistingCourierTest {
    @Before
    public void setUp() {
        setUpAPI();
        create(courierSasha);
    }

    @Test
    @DisplayName("Проверка логина существующего курьера (/api/v1/courier/login)")
    @Description("Проверка логина существующего курьера с полями: login = 'regular_courier', password = '1111', firstName = 'Sasha'")
    public void loginExistingCourier() {
        login(courierSasha)
                .then()
                .assertThat().body("id", notNullValue())
                .and()
                .statusCode(200);
    }

    @After
    public void courierCleanUp() {
        delete(courierSasha);
    }
}
