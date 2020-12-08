package ch.zhaw.mas.sharingAppServer.firstTestProjectSetup.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TestClient {


    public static void main(String[] args) {
        test();
        greeting();

    }

    public static void test(){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/books/test";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
    }

    public static void greeting(){
        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl
                = "http://localhost:8080/hallo?name=Adrian";
        ResponseEntity<String> response
                = restTemplate.getForEntity(fooResourceUrl, String.class);
        System.out.println(response);
    }

}
