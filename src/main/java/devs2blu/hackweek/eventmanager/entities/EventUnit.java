package devs2blu.hackweek.eventmanager.entities;

import java.time.LocalDateTime;
import java.util.List;

import devs2blu.hackweek.eventmanager.enums.EventUnitType;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class EventUnit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Enumerated
    private EventUnitType type;

    @ManyToOne
    private EventCore eventCore;

    private Long totalTickets;

    @ManyToOne
    private LocalUser host;

    @OneToMany(mappedBy = "eventUnit")
    private List<Ticket> tickets;
}
