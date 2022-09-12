package org.factoriaf5.uniquecare.service;

import org.factoriaf5.uniquecare.exceptions.ServiceNotFoundException;
import org.factoriaf5.uniquecare.exceptions.UserNotFoundException;
import org.factoriaf5.uniquecare.payload.request.ServiceRequest;
import org.factoriaf5.uniquecare.security.principal.UserDetailsImpl;
import org.factoriaf5.uniquecare.user.User;
import org.factoriaf5.uniquecare.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceDAO {
    private final ServiceRepository serviceRepository;
    private final UserRepository userRepository;

    @Autowired
    public ServiceDAO(ServiceRepository serviceRepository, UserRepository userRepository) {
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
    }

    public List<Service> findAll(){
        return serviceRepository.findAll();
    }

    public Service create(UserDetailsImpl userDetails, ServiceRequest serviceRequest) {
        User owner = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(UserNotFoundException::new);

        Service service = Service.builder()
                .name(serviceRequest.getName())
                .description(serviceRequest.getDescription())
                .basePrice(serviceRequest.getBasePrice())
                .owner(owner)
                .build();

        owner.addFacility(service);

        return serviceRepository.save(service);
    }

    public Service findById(Long id) {
        return serviceRepository.findById(id).orElseThrow(ServiceNotFoundException::new);
    }
}
