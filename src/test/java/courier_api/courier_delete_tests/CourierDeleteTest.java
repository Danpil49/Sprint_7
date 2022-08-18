package courier_api.courier_delete_tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static json_model.Courier.CourierClient.*;
import static org.hamcrest.Matchers.equalTo;

public class CourierDeleteTest {
    @Before
    public void setUp() {
        setUpAPI();
        create(courierSasha);
    }

    @Test
    @DisplayName("Проверка удаления курьера ('/api/v1/courier/:id')")
    public void courierDelete() {
        delete(courierSasha)
                .then()
                .assertThat().body("ok", equalTo(Boolean.TRUE))
                .and()
                .statusCode(200);
    }
}
