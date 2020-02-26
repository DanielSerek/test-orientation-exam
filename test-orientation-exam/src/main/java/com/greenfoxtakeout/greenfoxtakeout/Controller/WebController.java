package com.greenfoxtakeout.greenfoxtakeout.Controller;

import com.greenfoxtakeout.greenfoxtakeout.Domain.TakeoutOrder;
import com.greenfoxtakeout.greenfoxtakeout.Service.TakeoutOrderService;
import com.greenfoxtakeout.greenfoxtakeout.Service.TakeoutOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    TakeoutOrderService takeoutOrderService;

    @Autowired
    public WebController(TakeoutOrderServiceImpl takeoutOrderService) {
        this.takeoutOrderService = takeoutOrderService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/order")
    public String order(@RequestParam String name, @RequestParam String address, @RequestParam String base, @RequestParam String topping ) {
        TakeoutOrder order = new TakeoutOrder(name, address, base, topping);
        takeoutOrderService.addTakeoutOrder(order);

        return "redirect:/order/" + order.getId();
    }

    @GetMapping("/order/{id}")
    public String orderSummary(@PathVariable Long id, Model model) {
        TakeoutOrder order = takeoutOrderService.findById(id);
        if(order == null) {
            return "error";
        }

        model.addAttribute("order", order);

        return "order";
    }
}
