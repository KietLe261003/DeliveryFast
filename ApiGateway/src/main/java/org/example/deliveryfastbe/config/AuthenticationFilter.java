package org.example.deliveryfastbe.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.HttpHeaders;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.example.deliveryfastbe.Dto.Response.ApiResponse;
import org.example.deliveryfastbe.Service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class AuthenticationFilter implements GlobalFilter, Ordered {
    @Autowired
    IdentityService identityService;
    @Autowired
    ObjectMapper objectMapper;

    @NonFinal
    String[] publicEnpoint= {
            "/api/userservice/auth",
            "/api/userservice/auth/checktoken"
    };



    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        if(isPublicEnpoint(exchange.getRequest()))
        {
            return chain.filter(exchange);
        }

        //Token
        List<String> authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (CollectionUtils.isEmpty(authHeader))
            return unauthenticated(exchange.getResponse());

        String token = authHeader.get(0).replace("Bearer ", "");
        log.info("Token: {}", token);
        return identityService.introspect(token)
                .doOnNext(introspectResponse -> log.info("introspectResponse: {}", introspectResponse)) // Log sẽ chạy
                .flatMap(introspectResponse -> {
                    if (introspectResponse.getData())
                        return chain.filter(exchange);
                    else
                        return unauthenticated(exchange.getResponse());
                })
                .onErrorResume(throwable -> failConnectUserService(exchange.getResponse()));

    }
    private boolean isPublicEnpoint(org.springframework.http.server.reactive.ServerHttpRequest request) {
        return Arrays.stream(publicEnpoint).anyMatch(s -> request.getURI().getPath().matches(s));
    }

    @Override
    public int getOrder() {
        return -1;
    }

    Mono<Void> unauthenticated(ServerHttpResponse response){
        ApiResponse apiResponse = ApiResponse.builder()
                .code(1401)
                .message("Unauthenticated")
                .build();

        String body = null;
        try {
            body = objectMapper.writeValueAsString(apiResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return response.writeWith(
                Mono.just(response.bufferFactory().wrap(body.getBytes())));
    }
    Mono<Void> failConnectUserService(ServerHttpResponse response){
        ApiResponse apiResponse = ApiResponse.builder()
                .code(1401)
                .message("Fail Connect User")
                .build();

        String body = null;
        try {
            body = objectMapper.writeValueAsString(apiResponse);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        return response.writeWith(
                Mono.just(response.bufferFactory().wrap(body.getBytes())));
    }
}
