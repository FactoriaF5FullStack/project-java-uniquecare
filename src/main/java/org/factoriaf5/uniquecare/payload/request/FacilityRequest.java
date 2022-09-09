package org.factoriaf5.uniquecare.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FacilityRequest {
    private String name;
    private String description;
    private double basePrice;
}
