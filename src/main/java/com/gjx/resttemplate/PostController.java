package com.gjx.resttemplate;


import com.alibaba.fastjson.JSON;
import com.gjx.resttemplate.model.JsonModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {

    @RequestMapping(value = "/testForJson", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String testForJson(@RequestBody JsonModel model) {
        log.info("success post json:{}", JSON.toJSONString(model));
        return "post json success";
    }

    @RequestMapping(value = "/testForJsonWithUrlParam", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String testForJsonWithUrlParam(@RequestBody JsonModel model, String urlParam) {
        log.info("success post json:{}==>url param:{}", JSON.toJSONString(model), urlParam);
        return "post json with url param success";
    }

    @RequestMapping(value = "/testForUrl", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String testForUrl(@RequestParam String name, @RequestParam Integer age) {
        log.info("success post Url:name={},age={}", name, age);
        return "post urlencoded success";
    }
}
