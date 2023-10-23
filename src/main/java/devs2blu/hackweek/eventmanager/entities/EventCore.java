package devs2blu.hackweek.eventmanager.entities;

import java.sql.Date;
import java.util.List;

import devs2blu.hackweek.eventmanager.enums.Category;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class EventCore {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Enumerated()
    private Category category;

    private Date startDate;
    private Date endDate;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "event_street")),
        @AttributeOverride(name = "city", column = @Column(name = "event_city")),
        @AttributeOverride(name = "state", column = @Column(name = "event_state")),
        @AttributeOverride(name = "country", column = @Column(name = "event_country")),
        @AttributeOverride(name = "cep", column = @Column(name = "event_cep"))
    })
    private Address address;

    @OneToMany
    private List<LocalUser> organizers;

    @OneToMany(mappedBy = "eventCore")
    private List<EventUnit> eventUnits;

    @OneToMany(mappedBy = "eventCore")
    private List<Achievement> achievements;

    @OneToMany(mappedBy = "eventCore")
    private List<Post> posts;

    @OneToMany(mappedBy = "eventCore")
    private List<Question> questions;

    @OneToMany(mappedBy = "eventCore")
    private List<Ticket> distributedTickets;

}
