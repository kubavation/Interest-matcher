package io.duryskuba.interestmatcher.PostService.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("RESOURCE WITH ID: " + id + " DOES NOT EXISTS");
    }
}
