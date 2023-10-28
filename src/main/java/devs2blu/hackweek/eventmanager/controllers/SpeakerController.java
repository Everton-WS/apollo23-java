package devs2blu.hackweek.eventmanager.controllers;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
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

    @Operation(summary = "Get all Speakers", description = "Retrieve a list of all speakers")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping
    public ResponseEntity<List<SpeakerResponse>> findAll() {
        List<SpeakerResponse> speakers = speakerService.findAll();
        return ResponseEntity.ok().body(speakers);
    }

    @Operation(summary = "Find Speaker by ID", description = "Retrieve a single speaker by ID")
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

    @Operation(summary = "Create a new Speaker", description = "Create a new speaker")
    @ApiResponse(responseCode = "201", description = "Speaker successfully created")
    @ApiResponse(responseCode = "400", description = "Speaker couldn't be created")
    @PostMapping
    public ResponseEntity<SpeakerResponse> save(@RequestBody @Valid SpeakerRequest request) {
        SpeakerResponse createdSpeaker = speakerService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSpeaker);
    }

    @Operation(summary = "Update Speaker", description = "Update an existing speaker by ID")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = SpeakerResponse.class)))
    @ApiResponse(responseCode = "404", description = "Speaker not found")
    @PutMapping("/{id}")
    public ResponseEntity<SpeakerResponse> update(@PathVariable Long id, @RequestBody @Valid SpeakerRequest request) {
        SpeakerResponse updatedSpeaker = speakerService.update(id, request);
        if (updatedSpeaker == null) {
            throw new EntityNotFoundException(ErrorMessages.SPEAKER_NOT_FOUND);
        }
        return ResponseEntity.ok(updatedSpeaker);
    }

    @Operation(summary = "Delete Speaker", description = "Delete a speaker by ID")
    @ApiResponse(responseCode = "204", description = "Successful operation")
    @ApiResponse(responseCode = "404", description = "Speaker not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        speakerService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
