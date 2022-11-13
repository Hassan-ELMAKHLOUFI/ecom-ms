package org.sid.orderservice.web;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sid.orderservice.entities.Order;
import org.sid.orderservice.entities.ProductItem;
import org.sid.orderservice.model.Customer;
import org.sid.orderservice.model.Product;
import org.sid.orderservice.repository.OrderRepository;
import org.sid.orderservice.repository.ProductItemRepository;
import org.sid.orderservice.service.CustomerRestClientService;
import org.sid.orderservice.service.InventoryRestClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

@NoArgsConstructor
public class OrderRestController {

    @Autowired
    private OrderRepository orderRepository ;
    private ProductItemRepository productItemRepository ;
    @Autowired
    private CustomerRestClientService customerRestClientService;
    @Autowired
    private InventoryRestClientService inventoryRestClientService ;



    @GetMapping("fullOrder/{id}")
    public Order getOrder(@PathVariable Long id){
        Order order= orderRepository.findById(id).get() ;
       Customer customer = customerRestClientService.customerById(order.getCustomerId());

       order.setCustomer(customer);
        System.out.printf(order.getCustomerId()+"id");
        System.out.printf(customer.toString());
       order.getProductItemList().forEach(pi->{
           Product product =inventoryRestClientService.productById(pi.getProductId());
           pi.setProduct(product);
       });
    return order ;
   }
}
