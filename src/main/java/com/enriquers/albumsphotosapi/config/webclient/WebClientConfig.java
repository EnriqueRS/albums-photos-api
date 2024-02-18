package com.enriquers.albumsphotosapi.config.webclient;

import org.springframework.web.reactive.function.client.WebClient;

public interface WebClientConfig {

  WebClient.Builder getWebClientBuilder();

  WebClient getWebClient();
}
