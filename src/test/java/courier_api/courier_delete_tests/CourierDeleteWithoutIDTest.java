package courier_api.courier_delete_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static json_model.Courier.CourierClient.*;
import static org.hamcrest.Matchers.equalTo;

public class CourierDeleteWithoutIDTest {
    @Before
    public void setUp() {
        setUpAPI();
        create(courierSasha);
    }

    @Test
    @DisplayName("Проверка удаления курьера без указанного id ('/api/v1/courier/')")
    @Description("Должно выдать сообщение об ошибке 'Not Found.' и статус код = 404")
    public void courierDeleteWithoutID() {
        wrongDelete(courierSasha)
                .then()
                .assertThat().body("message", equalTo("Not Found."))
                .and()
                .statusCode(404);
    }

    @After
    public void cleanUp() {
        delete(courierSasha);
    }
}
