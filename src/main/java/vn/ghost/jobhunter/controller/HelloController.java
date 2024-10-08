package vn.ghost.jobhunter.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.ghost.jobhunter.util.error.IdInvalidException;

@RestController
public class HelloController {

    @GetMapping("/")
    // @CrossOrigin
    public String getHelloWorld() throws IdInvalidException {
        return "Hello World ";
    }
}
