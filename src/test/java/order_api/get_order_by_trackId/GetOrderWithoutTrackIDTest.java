package order_api.get_order_by_trackId;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static json_model.Order.OrderClient.*;
import static org.hamcrest.Matchers.equalTo;

public class GetOrderWithoutTrackIDTest {
    @Before
    public void setUp() {
        setUpOrderAPI();
    }

    @Test
    @DisplayName("Проверка ручки возвращающей номер заказа по треку без указания трека ('/api/v1/orders/track')")
    @Description("Должно выдать сообщение об ошибке 'Недостаточно данных для поиска' и статус код = 400")
    public void getOrderWithoutTrackID() {
        String trackId = "";
        getOrderIdByTrackWithTrackID(trackId)
                .then()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для поиска"))
                .and()
                .statusCode(400);
    }
}
