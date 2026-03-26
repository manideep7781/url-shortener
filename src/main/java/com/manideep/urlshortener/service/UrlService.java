package com.manideep.urlshortener.service;

import com.manideep.urlshortener.model.UrlMapping;
import com.manideep.urlshortener.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String originalUrl) {

        String shortUrl = UUID.randomUUID().toString().substring(0,6);

        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping.setShortUrl(shortUrl);

        urlRepository.save(mapping);

        return "http://localhost:8080/" + shortUrl;
    }

    public String getOriginalUrl(String shortUrl){

        Optional<UrlMapping> mapping = urlRepository.findByShortUrl(shortUrl);

        if(mapping.isPresent()){
            return mapping.get().getOriginalUrl();
        }

        return null;
    }
}