package com.example.url.shortner.controller;

import static com.example.url.shortner.AppConstants.LONG_URL;
import static com.example.url.shortner.AppConstants.SHORT_URL;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.url.shortner.service.UrlService;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
@Log4j2
public class UrlConversionController {

  private final UrlService urlService;

  @PostMapping(path = LONG_URL)
  @ResponseStatus(value = HttpStatus.OK)
  @ApiResponse(responseCode = "200", description = "Success. Long url converted into short url.")
  @ApiResponse(responseCode = "400", description = "Url cannot be not valid or null.", content = @Content)
  @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
  public String shortenUrl(@Parameter(description = "To shorten the long url") @RequestBody final String fullUrl) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(fullUrl), "Url cannot be null");
    return urlService.shortenUrl(fullUrl);
  }

  @GetMapping(path = SHORT_URL)
  @ResponseStatus(value = HttpStatus.OK)
  @ApiResponse(responseCode = "200", description = "Success. Long url has been obtained.")
  @ApiResponse(responseCode = "400", description = "Url cannot be null.", content = @Content)
  @ApiResponse(responseCode = "404", description = "Long url cannot found for short url provided.", content = @Content)
  @ApiResponse(responseCode = "500", description = "Internal server error.", content = @Content)
  public String getLongUrl(
      @Parameter(description = "Short url name to be searched", example = "bc") @RequestParam(value = "shortUrl") final String shortUrl) {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(shortUrl), "Url cannot be null");
    return urlService.getLongUrl(shortUrl);
  }
}
