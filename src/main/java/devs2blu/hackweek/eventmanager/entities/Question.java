package devs2blu.hackweek.eventmanager.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  

    @ManyToOne 
    private LocalUser origin;

    @ManyToOne
    @JoinColumn(name = "event_core_id", nullable = false)
    private EventCore eventCore;

    // IF NULL QUESTION IS FOR THE WHOLE EVENT
    @ManyToOne
    @JoinColumn(name = "event_core_id", nullable = true)
    private EventUnit eventUnit;
}
