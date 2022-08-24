package courier_api.courier_creation_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import json_model.courier.Courier;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static json_model.courier.CourierClient.*;
import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class CourierRequiredFieldsTest {
    private final String tempLogin;
    private final String tempPassword;
    private final String tempFirstName;


    public CourierRequiredFieldsTest(String tempLogin, String tempPassword, String tempFirstName) {
        this.tempLogin = tempLogin;
        this.tempPassword = tempPassword;
        this.tempFirstName = tempFirstName;
    }

    @Parameterized.Parameters(name = "{index}: login = {0},  password = {1}, firstName = {2}")
    public static Object[][] getTextData() {
        return new Object[][] {
                {"", "1111", "Sasha"},
                {"regular_courier", "", "Sasha"}
        };
    }

    @Before
    public void setUp() {
        setUpAPI();
    }

    @Test
    @DisplayName("Параметризованный тест. Проверка создания курьера без заполненных обязательных полей (/api/v1/courier)")
    @Description("Должно выдать сообщение об ошибке 'Недостаточно данных для создания учетной записи' и статус код = 400")
    public void createCourierWithoutRequiredFields() {
        createCourier(new Courier(tempLogin, tempPassword, tempFirstName))
                .then()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для создания учетной записи"))
                .and()
                .statusCode(SC_BAD_REQUEST);
    }

}
