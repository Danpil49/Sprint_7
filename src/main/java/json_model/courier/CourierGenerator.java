package json_model.courier;

public class CourierGenerator {
    public static Courier getCourierSasha() {
        return new Courier("regular_courier", "1111", "Sasha");
    }

    public static Courier getCourierMisha() {
        return new Courier("regular_courier", "1111", "Misha");
    }
}
