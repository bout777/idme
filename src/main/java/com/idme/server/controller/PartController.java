package com.idme.server.controller;

import com.huawei.innovation.rdm.intelligentrobotengineering.delegator.PartDelegator;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.PartUpdateByAdminDTO;
import com.huawei.innovation.rdm.intelligentrobotengineering.dto.entity.PartUpdateDTO;
import com.idme.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/part")
@RestController
public class PartController {
    @Autowired
    PartDelegator partDelegator;

    @PostMapping("/update")
    public void update(@RequestBody PartUpdateByAdminDTO p) {
        partDelegator.updateByAdmin(p);
    }

}
