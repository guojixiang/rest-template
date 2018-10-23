package com.gjx.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("get")
public class GetController {

    /*
    get 请求：content-type:application/x-www-form-urlencoded
     */
    @RequestMapping(value = "/testUrlencoded", method = RequestMethod.GET)
    public String testGet(@RequestParam String k, String v) {
        log.info("restTemplate get request arrive:k={},v={}", k, v);
        return "SUCCESS";
    }


}
