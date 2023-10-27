package devs2blu.hackweek.eventmanager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;

    @Column(name = "text", columnDefinition = "text")
    private String text;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    // Lifecycle Callbacks
    @PrePersist
    public void prePersist() {
        dateTime = LocalDateTime.now();
    }
}

