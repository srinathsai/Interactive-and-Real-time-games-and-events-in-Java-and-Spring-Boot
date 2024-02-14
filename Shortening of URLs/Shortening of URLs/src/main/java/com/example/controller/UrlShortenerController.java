package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.service.UrlMappingService;
import org.springframework.ui.Model;

@Controller
public class UrlShortenerController {
    @Autowired
    private UrlMappingService urlMappingService;


    @RequestMapping("/")
    public String showForm() {
        return "home";
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam("longUrl") String longUrl, Model model) {
        String shortUrl = urlMappingService.generateShortUrl(longUrl);
        model.addAttribute("shortUrl", shortUrl);
        return "result";

    }


    @RequestMapping("/{shortUrl}")
    public String redirectToLongUrl(@PathVariable String shortUrl) {
        String Longurl = urlMappingService.getLongUrl(shortUrl);
        if(Longurl==null){
            return "error";
        }
        return "redirect:" + Longurl;
    }
}
