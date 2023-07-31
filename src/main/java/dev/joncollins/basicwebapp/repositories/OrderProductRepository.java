package dev.joncollins.basicwebapp.repositories;

import dev.joncollins.basicwebapp.entity.CustomerOrder;
import dev.joncollins.basicwebapp.entity.OrderProduct;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {
    @Query(
            value = "SELECT op FROM OrderProduct op INNER JOIN op.product opd ON opd.id = op.product.id WHERE op" +
                    ".order_id = :orderId"
    )
    List<OrderProduct> findAllOrderProductDetailsByOrderId(@Param("orderId") String orderId);
}
