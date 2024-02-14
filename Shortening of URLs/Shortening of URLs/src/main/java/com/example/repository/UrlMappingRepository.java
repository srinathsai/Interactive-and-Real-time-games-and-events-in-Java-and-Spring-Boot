package com.example.repository;

import com.example.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {
    public UrlMapping findByShortUrl(String shortUrl);
}
