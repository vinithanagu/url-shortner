package com.example.url.shortner.service;

/**
 * The interface Url service.
 */
public interface UrlService {

  /**
   * Shorten url string.
   *
   * @param fullUrl the full url
   * @return the string
   */
  String shortenUrl(String fullUrl);

  /**
   * Gets long url.
   *
   * @param shortUrl the short url
   * @return the long url
   */
  String getLongUrl(String shortUrl);
}
