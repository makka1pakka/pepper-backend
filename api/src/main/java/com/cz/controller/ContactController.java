package com.cz.controller;

import com.cz.service.ContactService;
import com.cz.vo.ResultVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/connect")
@CrossOrigin
public class ContactController {

    @Resource
    ContactService connectService;

    @RequestMapping(value = "/sms", method = RequestMethod.GET)
    public ResultVo SendTestMessage() {
        return connectService.SMSTest();
    }


}
