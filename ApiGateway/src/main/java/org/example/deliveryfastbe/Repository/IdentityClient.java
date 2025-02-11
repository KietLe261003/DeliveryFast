package org.example.deliveryfastbe.Repository;

import org.example.deliveryfastbe.Dto.Request.TokenCheck;
import org.example.deliveryfastbe.Dto.Response.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface IdentityClient {
    @PostExchange(url = "/auth/checktoken", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse> introspect(@RequestBody TokenCheck tokenCheck);
}
