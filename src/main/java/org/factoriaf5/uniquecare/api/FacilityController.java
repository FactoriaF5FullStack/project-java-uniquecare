package org.factoriaf5.uniquecare.api;

import org.factoriaf5.uniquecare.facility.Facility;
import org.factoriaf5.uniquecare.facility.FacilityService;
import org.factoriaf5.uniquecare.payload.request.FacilityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/facility")
public class FacilityController {
    private final FacilityService facilityService;

    @Autowired
    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @GetMapping("")
    public ResponseEntity<List<Facility>> index() {
        return ResponseEntity.ok().body(facilityService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody FacilityRequest facilityRequest, UriComponentsBuilder uriComponentsBuilder) {
        Facility facility = facilityService.create(facilityRequest);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/v1/facility/{id}").build(1))
                .body("Coder with id " + facility.getId() + " Created successfully");
    }
}
