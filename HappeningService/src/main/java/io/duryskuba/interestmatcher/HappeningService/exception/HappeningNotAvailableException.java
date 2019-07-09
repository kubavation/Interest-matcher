package io.duryskuba.interestmatcher.HappeningService.exception;

public class HappeningNotAvailableException extends RuntimeException {

    public HappeningNotAvailableException(Object id) {
        super(String.format("HAPPENING WITH ID:%s NOT AVAILABLE", id));
    }
}
