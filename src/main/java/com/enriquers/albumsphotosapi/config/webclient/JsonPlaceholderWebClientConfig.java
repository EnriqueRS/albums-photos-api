package com.enriquers.albumsphotosapi.config.webclient;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@RequiredArgsConstructor
public class JsonPlaceholderWebClientConfig implements WebClientConfig {

  @Value("${JSONPLACEHOLDER_BASE_URL:https://jsonplaceholder.typicode.com/}")
  private String jsonplaceholderBaseUrl;

  @Bean
  public WebClient.Builder webClientBuilder() {
    return WebClient.builder();
  }

  @Bean
  @Override
  public WebClient.Builder getWebClientBuilder() {
    return webClientBuilder();
  }

  @Bean
  @Override
  public WebClient getWebClient() {
    return getWebClientBuilder().baseUrl(jsonplaceholderBaseUrl).build();
  }
}
