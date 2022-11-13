package org.sid.orderservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sid.orderservice.enums.OrderStatus;
import org.sid.orderservice.model.Customer;
import org.sid.orderservice.model.Product;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity

@Table(name = "orders")
@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdAt ;
    private OrderStatus status;
    private Long customerId;
    @Transient
    private Customer customer;
    @OneToMany(mappedBy = "order" )
    private List<ProductItem> productItemList ;

    public double getTotal(){
        double somme = 0;
        for (ProductItem pi : productItemList){
            somme+= pi.getAmount();
        }
        return somme;
    }
}
