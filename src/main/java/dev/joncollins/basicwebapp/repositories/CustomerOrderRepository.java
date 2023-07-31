package dev.joncollins.basicwebapp.repositories;

import dev.joncollins.basicwebapp.entity.CustomerOrder;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, String> {
    @Query(
            value = "SELECT co FROM CustomerOrder co WHERE co.user_token = :userId"
    )
    List<CustomerOrder> findAllOrdersByUserId(@Param("userId") String userId, Sort createdOn);
}
