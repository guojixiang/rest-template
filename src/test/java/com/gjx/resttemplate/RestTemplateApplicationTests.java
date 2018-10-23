package com.gjx.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@SpringBootTest
public class RestTemplateApplicationTests {

    //    @Autowired
    private RestTemplate restTemplate = new RestTemplate();

    public final String URL = "http://localhost:8080/";

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetRest() throws URISyntaxException {
        String k = "this is a get test?";
        String v = "yes";
        // 第一种 getForEntity(String var1, Class<T> var2, Object... var3)
        StringBuilder stringBuilder = new StringBuilder(URL).append("/get/testUrlencoded")
                .append("?k={k}&v={v}");
        String testUrl = stringBuilder.toString();
        ResponseEntity<String> result = restTemplate.getForEntity(testUrl, String.class, k, v);
        log.info("first -result:{}", result.getBody());

        log.info("test get second====================================================");
        HashMap params = new HashMap(2);
        params.put("k", "this is second test?");
        params.put("v", "yes");
        ResponseEntity<String> result2 = restTemplate.getForEntity(testUrl, String.class, params);
        log.info("second -result:{}", result2.getBody());

        log.info("test get third====================================================");
        HashMap params2 = new HashMap(2);
        params2.put("k", "this is third test?");
        params2.put("v", "yes");
        UriComponents uriComponents = UriComponentsBuilder.fromUriString(testUrl).build().expand(params2);
        URI uri = uriComponents.encode().toUri();
        ResponseEntity<String> result3 = restTemplate.getForEntity(uri, String.class);
        log.info("third -result:{}", result3.getBody());

        log.info("test get forth====================================================");
        HashMap params3 = new HashMap(2);
        params3.put("k", "this is third test?");
        params3.put("v", "yes");
        String result4 = restTemplate.getForObject(testUrl, String.class, params3);
        log.info("third -result:{}", result4);

    }

    @Test
    public void testPostForJson() {
        StringBuilder stringBuilder = new StringBuilder(URL).append("/post/testForJson");
        String postUrl = stringBuilder.toString();
        HashMap params = new HashMap(2);
        params.put("name", "gjx");
        params.put("age", 10);
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params);
        ResponseEntity<String> result = restTemplate.postForEntity(postUrl, httpEntity, String.class);
        log.info("post test json result:{}", result.getBody());
    }

    @Test
    public void testPostForJsonWithUrlParam() {
        StringBuilder stringBuilder = new StringBuilder(URL).append("/post/testForJsonWithUrlParam?urlParam={urlParam}");
        String postUrl = stringBuilder.toString();
        HashMap params = new HashMap(2);
        params.put("name", "gjx");
        params.put("age", 10);
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(params);
        ResponseEntity<String> result = restTemplate.postForEntity(postUrl, httpEntity, String.class,"urlParam");
        log.info("post test json result:{}", result.getBody());
    }

    @Test
    public void testPostForUrlencoded() {
        StringBuilder stringBuilder = new StringBuilder(URL).append("/post/testForUrl?name={name}&age={age}");
        String postUrl = stringBuilder.toString();
        HashMap params = new HashMap(2);
        params.put("name", "gjx");
        params.put("age", 10);
        ResponseEntity<String> result = restTemplate.postForEntity(postUrl, null, String.class,params);
        log.info("post test json result:{}", result.getBody());
    }


}
