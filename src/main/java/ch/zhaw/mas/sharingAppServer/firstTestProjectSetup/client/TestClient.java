package ch.zhaw.mas.sharingAppServer.firstTestProjectSetup.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TestClient {


    public static void main(String[] args) {
        test();

    }

    public static void test(){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/books/test";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
    }

}
