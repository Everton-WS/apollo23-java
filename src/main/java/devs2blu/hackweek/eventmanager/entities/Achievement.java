package devs2blu.hackweek.eventmanager.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String img;
    private Integer points;

    @ManyToOne
    private EventCore eventCore;

    @OneToOne
    private QRCode qrCode;

    @ManyToMany
    private List<LocalUser> owners;
}
