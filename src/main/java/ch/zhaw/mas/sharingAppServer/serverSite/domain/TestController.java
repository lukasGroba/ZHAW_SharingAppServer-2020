package ch.zhaw.mas.sharingAppServer.serverSite.domain;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class TestController {

    @GetMapping("/test")
    public @ResponseBody
    void getBook() {
        System.out.println("bla");
    }

}
