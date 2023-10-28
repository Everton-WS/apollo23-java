package devs2blu.hackweek.eventmanager.dtos.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode estar em branco")
    private String name;

    @NotNull(message = "O email não pode ser nulo")
    @NotBlank(message = "O email não pode estar em branco")
    private String email;

    @NotNull(message = "A senha não pode ser nula")
    @NotBlank(message = "A senha não pode estar em branco")
    private String password;

    @NotNull(message = "O número de telefone não pode ser nulo")
    @NotBlank(message = "O número de telefone não pode estar em branco")
    @Size(min = 9, max = 20, message = "O número de telefone deve ter entre 9 e 20 caracteres")
    private String mobile;

}
