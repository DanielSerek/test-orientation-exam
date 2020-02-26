package com.greenfoxtakeout.greenfoxtakeout.Service;

import com.greenfoxtakeout.greenfoxtakeout.Domain.TakeoutOrder;
import com.greenfoxtakeout.greenfoxtakeout.Domain.TakeoutOrderDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TakeoutOrderService {

    void addTakeoutOrder(TakeoutOrder order);
    TakeoutOrder findById(Long id);
    List<TakeoutOrderDTO> filterByTypeAndStatus(String type, String status);
    List<Object> filterKetto(String type, String status);
    List<TakeoutOrder> filterNotDTO(String type, String status);
}
