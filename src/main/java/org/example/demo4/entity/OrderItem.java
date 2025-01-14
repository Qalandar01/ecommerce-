package org.example.demo4.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    private Integer id = idGen++;
    private Integer orderId;
    private Integer productId;
    private Integer amount;
    private static Integer idGen = 1;

    public OrderItem(Integer orderId, Integer productId, Integer amount) {
        this.orderId = orderId;
        this.productId = productId;
        this.amount = amount;
    }
}
