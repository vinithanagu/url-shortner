package com.example.url.shortner.service;

/**
 * The interface Unique id generator service.
 */
public interface UniqueIdGeneratorService {

  /**
   * Encode string.
   *
   * @param input the input
   * @return the string
   */
  String encode(long input);

  /**
   * Decode long.
   *
   * @param input the input
   * @return the long
   */
  long decode(String input);
}
