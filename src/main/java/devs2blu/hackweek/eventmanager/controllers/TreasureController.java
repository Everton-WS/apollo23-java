package devs2blu.hackweek.eventmanager.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import devs2blu.hackweek.eventmanager.dtos.treasure.TreasureResponse;
import devs2blu.hackweek.eventmanager.services.TreasureService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/treasures")
@RequiredArgsConstructor
@Tag(name = "Treasures Management", description = "Endpoints for managing Treasures")
public class TreasureController {

    final TreasureService treasureService;

    public ResponseEntity<List<TreasureResponse>> getAllTreasures() {

    }

    public ResponseEntity<TreasureResponse> getTreasureById() {

    }

    public 
}
