package org.example.deliveryfastbe.Dto.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TokenCheck {
    String token;
}
