package devs2blu.hackweek.eventmanager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    
    @ManyToOne //(fetch = FetchType.LAZY)
    private LocalUser owner;

    @ManyToOne
    private EventCore eventCore;

    @ManyToOne
    private EventUnit eventUnit;

    private String qrCode;

    private Boolean isUsed;
    private Boolean isCancelled;

}
