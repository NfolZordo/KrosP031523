package com.ars.manager.client;

import com.ars.manager.client.dto.ErrorResponseDto;
import com.ars.manager.exception.ClientUnexpectedException;
import com.ars.manager.exception.ClientValidationException;
import com.ars.manager.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static reactor.core.publisher.Mono.just;

@Component
@Slf4j
public class CompanyAdapter {

    private static final String NOT_FOUND_EXCEPTION = "Not found Company by id: %s ";
    private final WebClient webClient;

    public CompanyAdapter(@Value("${services.url.company-manager-service}") String companyManagerServiceUrl,
                          WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl(companyManagerServiceUrl)
                .build();
    }

    public void deleteById(String id) {
        webClient.delete()
                .uri("/v1/companies/{id}", id)
                .retrieve()
                .onStatus(status -> status.equals(NOT_FOUND), response -> just(new NotFoundException(NOT_FOUND_EXCEPTION.formatted(id))))
                .onStatus(status -> status.equals(BAD_REQUEST), response -> response.bodyToMono(ErrorResponseDto.class)
                        .flatMap(this::getValidationError))
                .onStatus(HttpStatusCode::isError, response -> just(new ClientUnexpectedException()))
                .toBodilessEntity()
                .block();
    }

    private Mono<Throwable> getValidationError(ErrorResponseDto responseDto) {
        return Mono.error(new ClientValidationException(responseDto.getErrorCode(), responseDto.getErrorMessages()));
    }
}
