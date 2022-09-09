package org.factoriaf5.uniquecare.facility;

import org.factoriaf5.uniquecare.payload.request.FacilityRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityService {
    private final FacilityRepository facilityRepository;

    @Autowired
    public FacilityService(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    public List<Facility> findAll(){
        return facilityRepository.findAll();
    }

    public Facility create(FacilityRequest facilityRequest) {
        Facility facility = Facility.builder()
                .name(facilityRequest.getName())
                .description(facilityRequest.getDescription())
                .basePrice(facilityRequest.getBasePrice())
                .build();

        return facilityRepository.save(facility);
    }
}
