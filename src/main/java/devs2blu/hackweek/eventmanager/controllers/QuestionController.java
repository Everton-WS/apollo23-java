package devs2blu.hackweek.eventmanager.controllers;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.question.QuestionRequest;
import devs2blu.hackweek.eventmanager.dtos.question.QuestionResponse;
import devs2blu.hackweek.eventmanager.services.QuestionService;
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
@RequestMapping("/questions")
@RequiredArgsConstructor
@Tag(name = "Question Management", description = "Endpoints for managing Questions")
public class QuestionController {
    final QuestionService questionService;

    @Operation(summary = "Get all Questions", description = "Returns a list of all questions")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = QuestionResponse.class)))
    @GetMapping
    public ResponseEntity<List<QuestionResponse>> findAll() {
        List<QuestionResponse> questions = questionService.findAll();
        return ResponseEntity.ok().body(questions);
    }

    @Operation(summary = "Get Question by Question Id", description = "Returns a Single Question by Id")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = QuestionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Question not found")
    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> findById(@PathVariable Long id) {
        QuestionResponse question = questionService.findById(id);
        if (question == null) {
            throw new EntityNotFoundException(ErrorMessages.QUESTION_NOT_FOUND);
        }
        return ResponseEntity.ok(question);
    }

    @Operation(summary = "Create a new Question", description = "Create a new question")
    @ApiResponse(responseCode = "201", description = "Question successfully created")
    @ApiResponse(responseCode = "400", description = "Question couldn't be created")
    @PostMapping
    public ResponseEntity<QuestionResponse> save(@RequestBody @Valid QuestionRequest request) {
        QuestionResponse createdQuestion = questionService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuestion);
    }

    @Operation(summary = "Update Question", description = "Update an existing question by ID")
    @ApiResponse(responseCode = "200", description = "Successful operation",
            content = @Content(schema = @Schema(implementation = QuestionResponse.class)))
    @ApiResponse(responseCode = "404", description = "Question not found")
    @PatchMapping("/{id}")
    public ResponseEntity<QuestionResponse> update(@PathVariable Long id, @RequestBody @Valid QuestionRequest request) {
        return ResponseEntity.ok(this.questionService.update(id, request));
    }

    @Operation(summary = "Delete Question", description = "Delete a question by ID")
    @ApiResponse(responseCode = "204", description = "Successful operation")
    @ApiResponse(responseCode = "404", description = "Question not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        questionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
