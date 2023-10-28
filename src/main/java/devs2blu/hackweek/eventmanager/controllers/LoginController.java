package devs2blu.hackweek.eventmanager.controllers;

import devs2blu.hackweek.eventmanager.dtos.login.LoginRequest;
import devs2blu.hackweek.eventmanager.dtos.user.UserResponse;
import devs2blu.hackweek.eventmanager.services.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@Tag(name = "Login Management", description = "Login")
public class LoginController {
    final LoginService loginService;

    @Operation(summary = "Login",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation")
            })
    @PostMapping
    public UserResponse login(@RequestBody LoginRequest loginRequest){
        return loginService.login(loginRequest);
    }
};