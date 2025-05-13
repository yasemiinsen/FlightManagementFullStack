package com.Flight.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DailyFlightLimitExceededException extends RuntimeException {

    public DailyFlightLimitExceededException() {
        super("Daily flight limit exceeded for this route");
    }

    public DailyFlightLimitExceededException(String message) {
        super(message);
    }
}