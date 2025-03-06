package com.example.ShipperService.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "WareHouse_Tb")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WareHouse {
    @Id
    String id;
    String name;
    String type;
    GeoPoint location;
}
