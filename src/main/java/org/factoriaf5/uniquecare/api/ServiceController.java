package org.factoriaf5.uniquecare.api;

import org.factoriaf5.uniquecare.exceptions.UserNotFoundException;
import org.factoriaf5.uniquecare.security.principal.UserDetailsImpl;
import org.factoriaf5.uniquecare.service.Service;
import org.factoriaf5.uniquecare.service.ServiceDAO;
import org.factoriaf5.uniquecare.payload.request.ServiceRequest;
import org.factoriaf5.uniquecare.user.User;
import org.factoriaf5.uniquecare.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/api/v1/facility")
@CrossOrigin(origins = "*")
public class ServiceController {
    private final ServiceDAO serviceDAO;


    @Autowired
    public ServiceController(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    @GetMapping("")
    public ResponseEntity<List<Service>> index() {
        return ResponseEntity.ok().body(serviceDAO.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> index(@PathVariable Long id) {
        return ResponseEntity.ok().body(serviceDAO.findById(id));
    }

    @PostMapping("")
    public ResponseEntity<String> create(@RequestBody ServiceRequest serviceRequest, UriComponentsBuilder uriComponentsBuilder, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Service service = serviceDAO.create(userDetails, serviceRequest);
        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/v1/facility/{id}").build(1))
                .body("Coder with id " + service.getId() + " Created successfully");
    }
}
