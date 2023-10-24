package devs2blu.hackweek.eventmanager.dtos.event;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;


@Getter @Builder
public class EventResponse {
    private Long id;
    private String name;

    private String website;

    private String city;

    private String state;

    // private Set<User> users;

    private LocalDateTime endDate;

    private LocalDateTime startDate;

    // private List<Activity> activities;

    // private List<Message> messages;
}
