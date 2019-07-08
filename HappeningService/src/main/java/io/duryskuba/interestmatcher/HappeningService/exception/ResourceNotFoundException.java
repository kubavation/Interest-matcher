package io.duryskuba.interestmatcher.HappeningService.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super(String.format("RESOURCE WITH ID:%s NOT FOUND", id));
    }

    public ResourceNotFoundException() {
        super(String.format("RESOURCE NOT FOUND"));
    }
}
