package org.example.deliveryfastbe.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.deliveryfastbe.Dto.Request.TokenCheck;
import org.example.deliveryfastbe.Dto.Response.ApiResponse;
import org.example.deliveryfastbe.Repository.IdentityClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {

    IdentityClient identityClient;
    public Mono<ApiResponse> introspect(String token){
        return identityClient.introspect(TokenCheck.builder().token(token).build());
    }

}
