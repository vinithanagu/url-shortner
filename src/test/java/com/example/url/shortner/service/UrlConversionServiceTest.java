package com.example.url.shortner.service;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.url.shortner.entity.UrlEntity;
import com.example.url.shortner.exceptions.NotValidUrlException;
import com.example.url.shortner.exceptions.UrlNotFoundException;
import com.example.url.shortner.repository.UrlRepository;
import com.example.url.shortner.service.Impl.UrlConversionServiceImpl;

@DisplayName("UrlConversionService Unit Tests")
@ExtendWith(MockitoExtension.class)
public class UrlConversionServiceTest {

  private static final String LONG_URL = "https://www.real.com";

  private static final String SHORT_URL = "bc";

  private static final String INVALID_LONG_URL = "www.google.com";

  @Mock
  private UrlRepository urlRepository;

  @Mock
  private UniqueIdGeneratorService uniqueIdGeneratorService;

  @InjectMocks
  private UrlConversionServiceImpl urlConversionService;

  @Test
  public void shortenUrlTest() {
    var urlEntity = UrlEntity.builder().longUrl(LONG_URL).build();
    Mockito.when(urlRepository.save(urlEntity)).thenReturn(urlEntity);
    Mockito.when(uniqueIdGeneratorService.encode(urlEntity.getId())).thenReturn(SHORT_URL);

    String result = urlConversionService.shortenUrl(LONG_URL);

    Assertions.assertEquals(result, SHORT_URL);
    Mockito.verify(uniqueIdGeneratorService, Mockito.times(1)).encode(urlEntity.getId());
  }

  @Test
  public void shortenUrl_NotValidTest() {
    Assertions.assertThrows(NotValidUrlException.class, () -> urlConversionService.shortenUrl(INVALID_LONG_URL));
    Mockito.verifyNoMoreInteractions(urlRepository, uniqueIdGeneratorService);
  }

  @Test
  public void getLongUrlTest() {
    var urlEntity = UrlEntity.builder().longUrl(LONG_URL).build();
    Mockito.when(uniqueIdGeneratorService.decode(SHORT_URL)).thenReturn(1L);
    Mockito.when(urlRepository.findById(1L)).thenReturn(Optional.ofNullable(urlEntity));

    String result = urlConversionService.getLongUrl(SHORT_URL);

    Assertions.assertEquals(result, LONG_URL);
    Mockito.verify(uniqueIdGeneratorService, Mockito.times(1)).decode(SHORT_URL);
    Mockito.verify(urlRepository, Mockito.times(1)).findById(1L);

  }

  @Test
  public void getLongUrl_NotFoundTest() {
    Mockito.when(uniqueIdGeneratorService.decode(SHORT_URL)).thenReturn(1L);
    Mockito.when(urlRepository.findById(1L)).thenThrow(UrlNotFoundException.class);

    Assertions.assertThrows(UrlNotFoundException.class, () -> urlConversionService.getLongUrl(SHORT_URL));
    Mockito.verify(uniqueIdGeneratorService, Mockito.times(1)).decode(SHORT_URL);
    Mockito.verify(urlRepository, Mockito.times(1)).findById(1L);

  }
}
