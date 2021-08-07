package com.example.url.shortner.service;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.url.shortner.service.Impl.UniqueIdGeneratorServiceImpl;

@DisplayName("UniqueIdGeneratorService Unit Tests")
@ExtendWith(MockitoExtension.class)
public class UniqueIdGeneratorServiceTest {

  @InjectMocks
  private UniqueIdGeneratorServiceImpl uniqueIdGeneratorService;

  @ParameterizedTest
  @MethodSource("provideLongValuesToEncode")
  public void encode(final long input, final String expectedValue) {
    Assertions.assertEquals(uniqueIdGeneratorService.encode(input), expectedValue);
  }

  private static Stream<Arguments> provideLongValuesToEncode() {
    return Stream.of(Arguments.of(0, "a"), Arguments.of(1, "b"), Arguments.of(12, "m"));
  }

  @ParameterizedTest
  @MethodSource("provideStringsToDecode")
  public void decode(final String input, final long expectedValue) {
    Assertions.assertEquals(uniqueIdGeneratorService.decode(input), expectedValue);
  }

  private static Stream<Arguments> provideStringsToDecode() {
    return Stream.of(Arguments.of("a", 0), Arguments.of("b", 1), Arguments.of("m", 12));
  }
}
