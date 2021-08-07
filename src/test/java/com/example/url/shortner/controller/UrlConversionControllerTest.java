package com.example.url.shortner.controller;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.url.shortner.exceptions.NotValidUrlException;
import com.example.url.shortner.exceptions.UrlNotFoundException;
import com.example.url.shortner.service.Impl.UrlConversionServiceImpl;

@DisplayName("UrlConversionController Unit Tests")
@ExtendWith(MockitoExtension.class)
public class UrlConversionControllerTest {

  private static final String LONG_URL = "https://www.real.com";

  private static final String SHORT_URL = "bc";

  private static final String INVALID_LONG_URL = "www.google.com";

  @Mock
  private UrlConversionServiceImpl urlConversionService;

  @InjectMocks
  private UrlConversionController urlConversionController;

  @Test
  public void shortenUrl_happyPath() {
    //when
    Mockito.when(urlConversionService.shortenUrl(LONG_URL)).thenReturn(SHORT_URL);
    //then
    String result = urlConversionController.shortenUrl(LONG_URL);
    //assert
    Mockito.verify(urlConversionService, Mockito.times(1)).shortenUrl(LONG_URL);
    Assert.assertEquals(result, SHORT_URL);
  }

  @Test
  public void shortenUrl_nullValue() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> urlConversionController.shortenUrl(null));
  }

  @Test
  public void shortenUrl_notValidUrl() {
    Mockito.when(urlConversionService.shortenUrl(INVALID_LONG_URL)).thenThrow(NotValidUrlException.class);
    Assertions.assertThrows(NotValidUrlException.class, () -> urlConversionController.shortenUrl(INVALID_LONG_URL));
  }

  @Test
  public void getLongUrl_happyPath() {
    //when
    Mockito.when(urlConversionService.getLongUrl(SHORT_URL)).thenReturn(LONG_URL);
    //then
    String result = urlConversionController.getLongUrl(SHORT_URL);
    //assert
    Mockito.verify(urlConversionService, Mockito.times(1)).getLongUrl(SHORT_URL);
    Assert.assertEquals(result, LONG_URL);
  }

  @Test
  public void getLongUrl_urlNotFound() {
    Mockito.when(urlConversionService.getLongUrl(SHORT_URL)).thenThrow(UrlNotFoundException.class);
    Assertions.assertThrows(UrlNotFoundException.class, () -> urlConversionController.getLongUrl(SHORT_URL));
  }

  @Test
  public void getLongUrl_nullValue() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> urlConversionController.getLongUrl(null));
  }
}
