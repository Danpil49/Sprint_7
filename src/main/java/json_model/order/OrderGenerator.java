package json_model.order;

import java.util.List;
import static json_model.order.Colors.*;

public class OrderGenerator {
    public static Order getDefaultScooterOrder(String metroStationID) {
        return new Order("Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                metroStationID,
                5,
                "2020-06-06",
                "Saske, come back to Konoha",
                List.of(BLACK.toString(), GREY.toString()));
    }
    public static Order getBlackScooterOrder(String metroStationID) {
        return new Order("Sasha",
                "Petrov",
                "Lenina 228",
                metroStationID,
                5,
                "2021-02-05",
                "Random comment",
                List.of(BLACK.toString()));
    }
    public static Order getGreyScooterOrder(String metroStationID) {
        return new Order("Misha",
                "Ivanov",
                "Lavochkina 54",
                metroStationID,
                5,
                "2022-01-01",
                "Winter scooter ride",
                List.of(GREY.toString()));
    }
    public static Order getDefaultScooterOrderWithoutColor(String metroStationID) {
        return new Order("Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                metroStationID,
                5,
                "2020-06-06",
                "Saske, come back to Konoha",
                null);
    }
}
