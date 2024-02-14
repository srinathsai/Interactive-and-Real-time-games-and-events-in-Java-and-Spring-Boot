package com.example.service;

import com.example.entity.UrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.UrlMappingRepository;

import java.util.Random;

@Service
public class UrlMappingService {
    @Autowired
    private UrlMappingRepository urlMappingRepository;


    public String generateShortUrl(String longUrl) {
        // Logic to generate a short URL
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder shortUrl = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(characters.length());
            shortUrl.append(characters.charAt(index));
        }

        // Saving to the database (assuming you have a UrlMapping entity)
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setLongUrl(longUrl);
        urlMapping.setShortUrl(shortUrl.toString());
        urlMappingRepository.save(urlMapping);

        return shortUrl.toString();
    }


    public String getLongUrl(String shortUrl) {
        UrlMapping urlMapping = urlMappingRepository.findByShortUrl(shortUrl);
        if (urlMapping != null) {
            return urlMapping.getLongUrl();
        } else {
            return null; // Short URL not found
        }
    }


}
