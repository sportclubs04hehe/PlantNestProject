package vietnamese.com.PlantNest.entity;

public enum OrderStatus {
    PENDING, // chưa giải quyết
    PROCESSING, // xử lí
    SHIPPED, // đang vận chuyển
    DELIVERED, // đã giao hàng
    CANCELLED // đã hủy
}
