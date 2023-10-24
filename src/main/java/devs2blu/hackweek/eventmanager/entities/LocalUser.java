package devs2blu.hackweek.eventmanager.entities;

import java.util.List;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class LocalUser {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    

    private String username;
    private String password;

    private String name;
    private String lastName;

    private String picProfile;
    private String bio;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "street", column = @Column(name = "user_street")),
        @AttributeOverride(name = "city", column = @Column(name = "user_city")),
        @AttributeOverride(name = "state", column = @Column(name = "user_state")),
        @AttributeOverride(name = "country", column = @Column(name = "user_country")),
        @AttributeOverride(name = "cep", column = @Column(name = "user_cep"))
    })
    private Address address;

    @OneToOne
    private QRCode qrCode;

    @ManyToMany
    private List<Achievement> achievements;

    @OneToMany(mappedBy = "owner")
    private List<Ticket> tickets;
}
