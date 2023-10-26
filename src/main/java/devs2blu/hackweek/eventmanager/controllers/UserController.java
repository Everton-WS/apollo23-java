package devs2blu.hackweek.eventmanager.controllers;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;
import devs2blu.hackweek.eventmanager.dtos.user.UserResponse;
import devs2blu.hackweek.eventmanager.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "Endpoints for managing Users")
public class UserController {
    final UserService userService;

    @Operation(summary = "Get all Users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation")
            })
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllEvents() {
        List<UserResponse> allUsers = userService.getAllUsers();
        return ResponseEntity.ok().body(allUsers);
    }

    @Operation(summary = "Find User by ID", description = "Returns a single User",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) throws EntityNotFoundException {
        UserResponse userResponse = userService.getUserById(id);
        if (userResponse == null) {
            throw new EntityNotFoundException(ErrorMessages.USER_NOT_FOUND);
        }
        return ResponseEntity.ok(userResponse);
    }
}