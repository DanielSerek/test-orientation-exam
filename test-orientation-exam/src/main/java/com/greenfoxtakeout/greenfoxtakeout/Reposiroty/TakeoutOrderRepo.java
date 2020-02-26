package com.greenfoxtakeout.greenfoxtakeout.Reposiroty;

import com.greenfoxtakeout.greenfoxtakeout.Domain.TakeoutOrder;
import com.greenfoxtakeout.greenfoxtakeout.Domain.TakeoutOrderDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TakeoutOrderRepo extends CrudRepository<TakeoutOrder, Long> {

    @Query(value = "select id as id, name as name, base as base, topping as topping, status from takeout_order where topping like ?1 and status like ?2", nativeQuery = true)
    List<Object> findAllByToppingAndStatusketto(String topping, String status);

    List<TakeoutOrder> findAllByToppingAndStatus(String topping, String status);

    List<TakeoutOrder> findAllByStatus(String status);
}
