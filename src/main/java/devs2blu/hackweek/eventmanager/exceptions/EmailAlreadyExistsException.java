package devs2blu.hackweek.eventmanager.exceptions;

import devs2blu.hackweek.eventmanager.constants.ErrorMessages;

public class EmailAlreadyExistsException extends RuntimeException {
    public EmailAlreadyExistsException(String message) {
        super(message);
    }

    public EmailAlreadyExistsException() {
        super(ErrorMessages.EMAIL_ALREADY_EXISTS);
    }
}