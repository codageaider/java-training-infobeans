package day20.spring;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/*
Ex:
(i) Make a POST request and fetch the response
from any POST API on reqres.in
documentation for RestTemplate :
https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/client/RestTemplate.html
 */

/*
It moves the validation logic from all the methods to a separate class.
Cons
(i) Each time you add a new API you have to add the validation code
(ii) Code duplicacy
(iii) Single Responsbility Principle is violated (one of SOLID principles)

Pros of using interceptor
(i) Write the validation logic once

 */
@RestController
public class TestController {

    @GetMapping("/")
    public JsonNode test(String id) throws TweetNotFoundException {
        System.out.println("inside test method");
        /*
        if(!credentialsValid(userName, password))
            throw new InvalidCredentialException();
         */
        /* Get request from here to https://reqres.in/api/users

        We need to make a get request to url
        We also need to specify the header of the GET request
        key=value
        exchange(String url, HttpMethod method, @Nullable HttpEntity<?> requestEntity,
			Class<T> responseType, Object... uriVariables)
         */
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        List<String> value = new ArrayList<>();
        value.add("application/json");
        httpHeaders.put("content-type", value);
        HttpEntity<JsonNode> entity = new HttpEntity<>(httpHeaders);
//        restTemplate.postForEntity()
        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange("https://reqres.in/api/users",
                HttpMethod.GET, entity,
                JsonNode.class);
        return responseEntity.getBody();
//        System.out.println("inside controller");
//        throw new TweetNotFoundException();
    }
}
