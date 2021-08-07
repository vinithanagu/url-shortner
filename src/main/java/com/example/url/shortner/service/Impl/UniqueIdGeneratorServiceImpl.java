package com.example.url.shortner.service.Impl;

import static com.example.url.shortner.AppConstants.allowedString;

import org.springframework.stereotype.Service;

import com.example.url.shortner.service.UniqueIdGeneratorService;

@Service
public class UniqueIdGeneratorServiceImpl implements UniqueIdGeneratorService {

  private final int base = allowedString.length();

  @Override
  public String encode(long input) {
    var encodedString = new StringBuilder();

    if (input == 0) {
      return String.valueOf(allowedString.charAt(0));
    }

    while (input > 0) {
      encodedString.append(allowedString.charAt((int) (input % base)));
      input = input / base;
    }

    return encodedString.reverse().toString();
  }

  @Override
  public long decode(String input) {
    var characters = input.toCharArray();
    var length = characters.length;

    var decoded = 0;

    //counter is used to avoid reversing input string
    var counter = 1;
    for (int i = 0; i < length; i++) {
      decoded += allowedString.indexOf(characters[i]) * Math.pow(base, length - counter);
      counter++;
    }
    return decoded;
  }
}
