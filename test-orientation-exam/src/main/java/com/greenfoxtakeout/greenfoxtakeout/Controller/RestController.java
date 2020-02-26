package com.greenfoxtakeout.greenfoxtakeout.Controller;

import com.greenfoxtakeout.greenfoxtakeout.Domain.StatusModifier;
import com.greenfoxtakeout.greenfoxtakeout.Domain.TakeoutOrder;
import com.greenfoxtakeout.greenfoxtakeout.Domain.TakeoutOrderDTO;
import com.greenfoxtakeout.greenfoxtakeout.Service.TakeoutOrderService;
import com.greenfoxtakeout.greenfoxtakeout.Service.TakeoutOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    TakeoutOrderService takeoutOrderService;

    @Autowired
    public RestController(TakeoutOrderServiceImpl takeoutOrderService) {
        this.takeoutOrderService = takeoutOrderService;
    }

    @PatchMapping("/api/orders/{orderId}")
    public ResponseEntity statusChange(@PathVariable Long orderId, @RequestBody StatusModifier modifiedStatus) {
        TakeoutOrder order = takeoutOrderService.findById(orderId);
        String status = modifiedStatus.getStatus();
        if(order == null) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(status.equals("ordered") || status.equals("inprogress") || status.equals("done")) {
            order.setStatus(status);
            takeoutOrderService.addTakeoutOrder(order);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //endpoint that expects a TakeoutOrderDTO
    @GetMapping("/api/orders")
    public ResponseEntity<List<TakeoutOrderDTO>> filterOrders(@RequestParam(required = false) String type,
                                                              @RequestParam(required = false) String status) {

        if(type.equals("all") || type.equals("vegetarian") && status.equals("ordered") ||
                status.equals("inprogress") || status.equals("done")) {

            List<TakeoutOrderDTO> listToSendBack = takeoutOrderService.filterByTypeAndStatus(type, status);

            return ResponseEntity.ok().body(listToSendBack);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        //querry test
        /*List<Object> orderDTOList = takeoutOrderService.filterKetto(type, status);
        return ResponseEntity.ok(orderDTOList);*/
    }

    //endpoint that expects a TakeoutOrder object with jsonignore on address
    @GetMapping("/api/not-dto-orders")
    public ResponseEntity<List<TakeoutOrder>> filterOrdersNotDTO(@RequestParam(required = false) String type,
                                                           @RequestParam(required = false) String status) {

        if(type.equals("all") || type.equals("vegetarian") && status.equals("ordered") ||
                status.equals("inprogress") || status.equals("done")) {

            List<TakeoutOrder> listToSendBack = takeoutOrderService.filterNotDTO(type, status);

            return ResponseEntity.ok().body(listToSendBack);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
