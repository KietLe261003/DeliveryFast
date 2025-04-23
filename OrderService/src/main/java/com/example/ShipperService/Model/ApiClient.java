package com.example.ShipperService.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("ApiClient_Tb")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiClient {
    @Id
    private String id;
    private String clientName;
    private String apiKey;
    private boolean active;
}
