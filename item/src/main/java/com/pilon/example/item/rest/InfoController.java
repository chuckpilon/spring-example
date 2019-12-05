package com.pilon.example.item.rest;

import java.util.Locale;
import java.util.concurrent.Callable;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping(value = {"/info"})
public class InfoController {

    @RequestMapping(value = "/args", method=RequestMethod.GET)
    public Callable<ResponseEntity<String>> getArguments(Locale locale, @RequestHeader("User-Agent") String userAgent) {
        String originThread = Thread.currentThread().getName();
        return () -> ResponseEntity.ok(String.format("locale=%s, user agent=%s, request thread=%s, response thread=%s", 
            locale, userAgent, originThread, Thread.currentThread().getName()));
    }


}