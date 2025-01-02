package com.idme.server.controller;

import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.PartDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.PartUpdateByAdminDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.PartUpdateDTO;
import com.idme.common.result.PageResult;
import com.idme.common.result.Result;
import com.idme.pojo.dto.SearchQueryDTO;
import com.idme.pojo.entity.Part;
import com.idme.pojo.entity.PartClsDefinition;
import com.idme.server.mapper.PartMapper;
import com.idme.server.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/part")
@RestController
public class PartController {
    @Autowired
    PartDelegator partDelegator;
    @Autowired
    private PartService partService;
    @PostMapping("/update")
    public Result update(@RequestBody Part part) {
        partService.update(part);
        return Result.success();
    }

    @GetMapping("/listClsDefs")
    public Result<List<PartClsDefinition>> list(){
        List<PartClsDefinition> res = partService.listDefs();
        return Result.success(res);
    }

    @PostMapping("/insert")
    public Result insert(@RequestBody Part part) {
        partService.insert(part);
        return Result.success();
    }

    @PostMapping("/delete")
    public Result delete(Long id) {
        partService.delete(id);
        return Result.success();
    }

    @PostMapping("/page")
    public Result<PageResult> page(@RequestBody SearchQueryDTO query) {
        PageResult res = partService.page(query);
        return Result.success(res);
    }

    @GetMapping("/getByProductId")
    public Result<List<Part>> getByProductId(Long id) {
        List<Part> res = partService.getByProductId(id);
        return Result.success(res);
    }
}
