package com.idme.server.controller;

import com.idme.common.result.PageResult;
import com.idme.common.result.Result;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.Order;
import com.idme.server.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/insert")
    Result insert(@RequestBody Order order) {
        orderService.insert(order);
        return Result.success();
    }

    @PostMapping("/update")
    Result update(@RequestBody Order order) {
        orderService.update(order);
        return Result.success();
    }

    @PostMapping("/delete")
    Result delete(@RequestBody Long id) {
        orderService.delete(id);
        return Result.success();
    }

    @GetMapping("/getById")
    Result<Order> getById(@RequestParam Long id) {
        Order order= orderService.getById(id);
        return Result.success(order);
    }

    @PostMapping("/page")
    Result<PageResult> page(@RequestBody SearchQueryDTO query) {
        PageResult pageResult = orderService.page(query);
        return Result.success(pageResult);
    }



}
