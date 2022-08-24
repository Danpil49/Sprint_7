package courier_api.courier_login_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import json_model.courier.Courier;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static json_model.courier.CourierClient.*;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class LoginRequiredFieldsTest {
    private final String tempLogin;
    private final String tempPassword;


    public LoginRequiredFieldsTest(String tempLogin, String tempPassword) {
        this.tempLogin = tempLogin;
        this.tempPassword = tempPassword;
    }

    @Parameterized.Parameters(name = "{index}: login = '{0}',  password = '{1}', firstName = 'Sasha'")
    public static Object[][] getTextData() {
        return new Object[][] {
                {"", "1111"},
                {"regular_courier", ""}
        };
    }
    @Before
    public void setUp() {
        setUpAPI();
        createCourier(courierSasha);
    }

    @Test
    @DisplayName("Параметризованный тест. Проверка обязательных полей логина у существующего курьера (/api/v1/courier/login)")
    @Description("Попытка логина существующего курьера без одного из обязательных полей login = 'regular_courier' или password = '1111'. Должно выдать сообщение об ошибке 'Недостаточно данных для входа' и статус код = 400")
    public void loginRequiredField() {
        login(new Courier(tempLogin, tempPassword))
                .then()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

    @After
    public void courierCleanUp() {
        deleteCourier(courierSasha);
    }
}
