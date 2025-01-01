package com.idme.server.controller;

import com.idme.common.result.PageResult;
import com.idme.common.result.Result;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.Product;
import com.idme.server.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/insert")
    Result insert(@RequestBody Product product) {
        productService.insert(product);
        return Result.success();
    }

    @PostMapping("/update")
    Result update(@RequestBody Product product) {
        productService.update(product);
        return Result.success();
    }

    @PostMapping("/delete")
    Result delete(@RequestBody Long id) {
        productService.delete(id);
        return Result.success();
    }

    @PostMapping("/page")
    Result<PageResult> pageProducts(@RequestBody SearchQueryDTO query) {
        PageResult pageResult = productService.page(query);
        return Result.success(pageResult);
    }

    @GetMapping("/getById/{id}")
    Result<Product> getById(@PathVariable Long id) {
        Product product = productService.getById(id);
        return Result.success(product);
    }
}
