package com.example.url.shortner.service.Impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;

import com.example.url.shortner.entity.UrlEntity;
import com.example.url.shortner.exceptions.NotValidUrlException;
import com.example.url.shortner.exceptions.UrlNotFoundException;
import com.example.url.shortner.repository.UrlRepository;
import com.example.url.shortner.service.UniqueIdGeneratorService;
import com.example.url.shortner.service.UrlService;

@Service
@AllArgsConstructor
@Log4j2
public class UrlConversionServiceImpl implements UrlService {

  private final UrlRepository urlRepository;

  private final UniqueIdGeneratorService uniqueIdGeneratorService;

  @Override
  public String shortenUrl(String fullUrl){
    UrlValidator urlValidator = new UrlValidator();
    if(urlValidator.isValid(fullUrl)){
      var entity = urlRepository.save(UrlEntity.builder().longUrl(fullUrl).build());
      return uniqueIdGeneratorService.encode(entity.getId());
    }else {
      throw new NotValidUrlException("Not valid Url");
    }
  }

  @Override
  public String getLongUrl(String shortUrl) {
    var id = uniqueIdGeneratorService.decode(shortUrl);
    var entity = urlRepository.findById(id)
        .orElseThrow(() -> new UrlNotFoundException("Long Url is not found for : " + shortUrl));
    return entity.getLongUrl();
  }

}
