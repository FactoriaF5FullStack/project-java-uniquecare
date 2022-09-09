package org.factoriaf5.uniquecare.facility;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.factoriaf5.uniquecare.user.User;

import javax.persistence.*;

@Entity
@Table(name = "services")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double basePrice;

    @ManyToOne
    @JsonIgnoreProperties("facilities")
    private User owner;


}
