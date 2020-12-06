package ch.zhaw.mas.sharingAppServer.firstTestProjectSetup.controller;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

    @RestController
    public class GreetingController {

        private static final String template = "Hello, %s!";
        private final AtomicLong counter = new AtomicLong();
        private long testNumber = 393939393;

        @GetMapping("/greeting")
        public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
            return new Greeting(counter.incrementAndGet(), String.format(template, name));
        }

        @GetMapping("/hallo")
        public Greeting hallo(@RequestParam(value = "name", defaultValue = "HALLO") String name) {
            return new Greeting(/*counter.incrementAndGet()*/testNumber, String.format(template, name));
        }

    }
