package devs2blu.hackweek.eventmanager.controllers;

import devs2blu.hackweek.eventmanager.dtos.activity.ActivityResponse;
import devs2blu.hackweek.eventmanager.dtos.event.EventResponse;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureRequest;
import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureResponse;
import devs2blu.hackweek.eventmanager.services.TreasureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treasures")
@RequiredArgsConstructor
@Tag(name = "Treasures Management", description = "Endpoints for managing Treasures")
public class TreasureController {

    final TreasureService treasureService;

    @Operation(summary = "Get all Treasures",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @GetMapping
    public ResponseEntity<List<TreasureResponse>> getAllTreasures() {
        return ResponseEntity.ok(this.treasureService.findAllTreasures());
    }

    @Operation(summary = "Get Treasure by ID", description = "Returns a single Treasure",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = EventResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Treasure not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TreasureResponse> getTreasureById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(this.treasureService.findTreasureById(id));       
    }

    @Operation(summary = "Get Event By Treasure Id", description = "Returns a single Event",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = EventResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Treasure not found")
    })
    @GetMapping("/{treasureId}/event")
    public ResponseEntity<EventResponse> getTreasureEvent(@PathVariable Long treasureId) throws Exception {
        return ResponseEntity.ok(this.treasureService.findTreasureEvent(treasureId));         
    }

    @Operation(summary = "Find Activity by Treasure ID", description = "Returns a single Activity",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = EventResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Treasure not found")
    })
    @GetMapping("/{treasureId}/activity")
    public ResponseEntity<ActivityResponse> getActivityEvent(@PathVariable Long treasureId) throws Exception {
        return ResponseEntity.ok(this.treasureService.findTreasureActivity(treasureId));         
    }

    @Operation(summary = "Create New Treasure",
        responses = {
            @ApiResponse(responseCode = "201", description = "Treasure Successfully created"),
            @ApiResponse(responseCode = "400", description = "Treasure could'nt be created")
    })
    @PostMapping
    public ResponseEntity<TreasureResponse> createTreasure(@RequestBody TreasureRequest tRequest) throws Exception {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.treasureService.createTreasure(tRequest));
    }

    @Operation(summary = "Delete Treasure",
        responses = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Treasure not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreasure(Long id) {
        treasureService.deleteTreasure(id);
        return ResponseEntity.noContent().build();
    }
}
