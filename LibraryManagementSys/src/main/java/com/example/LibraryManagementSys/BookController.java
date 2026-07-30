package com.example.LibraryManagementSys;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @RequestMapping(value = "/custom", method = RequestMethod.GET)
    public String custom() {
        return "custom";
    }
}
