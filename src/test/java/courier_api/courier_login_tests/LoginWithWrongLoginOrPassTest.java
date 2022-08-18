package courier_api.courier_login_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import json_model.Courier.Courier;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static json_model.Courier.CourierClient.*;
import static org.hamcrest.Matchers.equalTo;

public class LoginWithWrongLoginOrPassTest {
    private final String login = "login" + ((new Random()).nextInt(100)+1);
    private final String password = "password" + ((new Random()).nextInt(100)+1);

    @Before
    public void setUp() {
        setUpAPI();
    }

    @Test
    @DisplayName("Параметризованный тест. Проверка логина и пароля несуществующего курьера (/api/v1/courier/login)")
    @Description("Должно выдать сообщение об ошибке 'Учетная запись не найдена' и статус код = 404")
    public void loginWithWrongLoginOrPass() {
        login(new Courier(login, password))
                .then()
                .assertThat().body("message", equalTo("Учетная запись не найдена"))
                .and()
                .statusCode(404);
    }
}
