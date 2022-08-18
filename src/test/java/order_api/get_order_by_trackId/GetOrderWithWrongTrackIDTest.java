package order_api.get_order_by_trackId;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static json_model.Order.OrderClient.getOrderIdByTrackWithTrackID;
import static json_model.Order.OrderClient.setUpOrderAPI;
import static org.hamcrest.Matchers.equalTo;

public class GetOrderWithWrongTrackIDTest {
    @Before
    public void setUp() {
        setUpOrderAPI();
    }

    @Test
    @DisplayName("Проверка ручки возвращающей номер заказа по треку с указанием неправильного трека ('/api/v1/orders/track')")
    @Description("Должно выдать сообщение об ошибке 'Заказ не найден' и статус код = 404")
    public void getOrderWithWrongTrackID() {
        String trackId = "0";
        getOrderIdByTrackWithTrackID(trackId)
                .then()
                .assertThat()
                .body("message", equalTo("Заказ не найден"))
                .and()
                .statusCode(404);
    }
}
