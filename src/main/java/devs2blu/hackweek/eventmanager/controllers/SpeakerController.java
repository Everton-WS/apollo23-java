package devs2blu.hackweek.eventmanager.controllers;

import devs2blu.hackweek.eventmanager.dtos.speaker.SpeakerRequest;
import devs2blu.hackweek.eventmanager.dtos.speaker.SpeakerResponse;
import devs2blu.hackweek.eventmanager.services.SpeakerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/speakers")
@RequiredArgsConstructor
@Tag(name = "Speaker Management", description = "Endpoints for managing Speakers")
public class SpeakerController {
    final SpeakerService speakerService;

    @Operation(summary = "Get all Speakers", description = "Returns a List of All Speakers from System")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping
    public ResponseEntity<List<SpeakerResponse>> findAll() {
        List<SpeakerResponse> speakers = speakerService.findAll();
        return ResponseEntity.ok().body(speakers);
    }

    @Operation(summary = "Get Speaker by ID", description = "Returns a Single Speaker by Id")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = SpeakerResponse.class)))
    @ApiResponse(responseCode = "404", description = "Speaker not found")
    @GetMapping("/{id}")
    public ResponseEntity<SpeakerResponse> findById(@PathVariable Long id) {
        SpeakerResponse speaker = speakerService.findById(id);
        if (speaker == null) {
            throw new EntityNotFoundException("Speaker not found");
        }
        return ResponseEntity.ok(speaker);
    }

    // @Operation(summary = "Get Activities By Speaker Id", description = "Returns a Single Activity from Speaker")
    // @ApiResponse(responseCode = "200", description = "Successful operation",
    //         content = @Content(schema = @Schema(implementation = SpeakerResponse.class)))
    // @ApiResponse(responseCode = "404", description = "Speaker not found")
    // @GetMapping("/{id}")
    // public ResponseEntity<SpeakerResponse> findActivitiesById(@PathVariable Long id) {
    //     SpeakerResponse speaker = speakerService.;
    //     if (speaker == null) {
    //         throw new EntityNotFoundException("Speaker not found");
    //     }
    //     return ResponseEntity.ok(speaker);
    // }

    @Operation(summary = "Create a new Speaker")
    @ApiResponse(responseCode = "201", description = "Speaker Successfully Created")
    @ApiResponse(responseCode = "400", description = "Speaker Couldn't Be Created")
    @PostMapping
    public ResponseEntity<SpeakerResponse> save(@RequestBody @Valid SpeakerRequest request) {
        SpeakerResponse createdSpeaker = speakerService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpeaker);
    }

    @Operation(summary = "Update Speaker", description = "Update an Existing Speaker By ID")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = SpeakerResponse.class)))
    @ApiResponse(responseCode = "404", description = "Speaker not found")
    @PatchMapping("/{id}")
    public ResponseEntity<SpeakerResponse> update(@PathVariable Long id, @RequestBody @Valid SpeakerRequest request) {

        return ResponseEntity.ok(this.speakerService.update(id, request));
    }

    @Operation(summary = "Delete Speaker", description = "Delete a Speaker By Id")
    @ApiResponse(responseCode = "204", description = "Successful operation")
    @ApiResponse(responseCode = "404", description = "Speaker not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        speakerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
