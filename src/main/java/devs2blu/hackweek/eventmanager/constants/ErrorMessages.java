package devs2blu.hackweek.eventmanager.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessages {
    // Validation Error Messages
    public final String VALIDATION_ERROR = "Erro de validação";

    // Generic Id not Found Message
    public final String ID_NOT_FOUND = "Id não encontrado no banco de dados";

    // User Error Messages
    public final String USER_NOT_FOUND = "O usuário não foi encontrado";
    public final String USERNAME_NOT_FOUND = "O nome de usuário não foi encontrado";
    public final String LOGIN_ALREADY_EXISTS = "O login já existe";

    // Person Error Messages
    public final String EMAIL_ALREADY_EXISTS = "Email já cadastrado!";

    // Event Error Messages
    public final String EVENT_NOT_FOUND = "Event não encontrado!";

    // Activity Error Messages
    public final String ACTIVITY_NOT_FOUND = "Activity não encontrado!";

    // Treasure Error Messages
    public final String TREASURE_NOT_FOUND = "Treasure não encontrado!";

    // Speaker Error Messages
    public final String SPEAKER_NOT_FOUND = "Palestrante não foi encontrado";

    // Speaker Error Messages
    public final String QUESTION_NOT_FOUND = "Pergunta não foi encontrada";
}