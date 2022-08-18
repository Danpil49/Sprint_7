package json_model.Order;

public class SingleOrderInObject {
    private Order order;

    public SingleOrderInObject(Order order) {
        this.order = order;
    }

    public SingleOrderInObject() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
