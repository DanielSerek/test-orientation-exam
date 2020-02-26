package com.greenfoxtakeout.greenfoxtakeout.Service;

import com.greenfoxtakeout.greenfoxtakeout.Domain.TakeoutOrder;
import com.greenfoxtakeout.greenfoxtakeout.Domain.TakeoutOrderDTO;
import com.greenfoxtakeout.greenfoxtakeout.Reposiroty.TakeoutOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TakeoutOrderServiceImpl implements TakeoutOrderService{

    TakeoutOrderRepo takeoutOrderRepo;

    @Autowired
    public TakeoutOrderServiceImpl(TakeoutOrderRepo takeoutOrderRepo) {
        this.takeoutOrderRepo = takeoutOrderRepo;
    }


    @Override
    public void addTakeoutOrder(TakeoutOrder order) {
        takeoutOrderRepo.save(order);
    }

    @Override
    public TakeoutOrder findById(Long id) {
        Optional<TakeoutOrder> orderOptional = takeoutOrderRepo.findById(id);

        return orderOptional.orElse(null);
    }

    @Override
    public List<TakeoutOrderDTO> filterByTypeAndStatus(String type, String status) {
        if(status.equals("vegetarian")){
            List<TakeoutOrderDTO> takeoutOrderDTOS = new ArrayList<>();
            List<TakeoutOrder> takeoutOrders = takeoutOrderRepo.findAllByToppingAndStatus("smoked-tofu", status);

            for (TakeoutOrder takeoutOrder : takeoutOrders) {
                TakeoutOrderDTO dto = new TakeoutOrderDTO(takeoutOrder);
                takeoutOrderDTOS.add(dto);
            }

            return takeoutOrderDTOS;
        }

        List<TakeoutOrderDTO> takeoutOrderDTOS = new ArrayList<>();
        List<TakeoutOrder> takeoutOrders = takeoutOrderRepo.findAllByStatus(status);

        for (TakeoutOrder takeoutOrder : takeoutOrders) {
            TakeoutOrderDTO dto = new TakeoutOrderDTO(takeoutOrder);
            takeoutOrderDTOS.add(dto);
        }

        return takeoutOrderDTOS;
    }

    @Override
    public List<Object> filterKetto(String type, String status) {
        return takeoutOrderRepo.findAllByToppingAndStatusketto(type, status);
    }

    @Override
    public List<TakeoutOrder> filterNotDTO(String type, String status) {
        if(type.equals("vegetarian")) {
            return takeoutOrderRepo.findAllByToppingAndStatus("smoked-tofu", status);
        }

        return takeoutOrderRepo.findAllByStatus(status);
    }


}
