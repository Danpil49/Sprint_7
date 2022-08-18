package courier_api.courier_delete_tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static json_model.Courier.CourierClient.*;
import static org.hamcrest.Matchers.equalTo;

public class CourierDeleteWithWrongIDTest {
    @Before
    public void setUp() {
        setUpAPI();
        create(courierSasha);
    }

    @Test
    @DisplayName("Проверка удаления курьера с несуществующим id ('/api/v1/courier/:id')")
    @Description("Должно выдать сообщение об ошибке 'Курьера с таким id нет.' и статус код = 404")
    public void courierDeleteWithWrongID() {
        String randomID = String.valueOf(((new Random()).nextInt(100_000_000)+10_000_000));
        System.out.println(randomID);
        wrongDelete(courierSasha, randomID)
                .then()
                .assertThat().body("message", equalTo("Курьера с таким id нет."))
                .and()
                .statusCode(404);
    }

    @After
    public void cleanUp() {
        delete(courierSasha);
    }
}
