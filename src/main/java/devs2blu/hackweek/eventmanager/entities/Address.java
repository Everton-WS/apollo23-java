package devs2blu.hackweek.eventmanager.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable @Getter @Setter
public class Address {
    
    private String street;
    private String city;
    private String state;
    private String country;
    private String number;
    private String cep;
}
