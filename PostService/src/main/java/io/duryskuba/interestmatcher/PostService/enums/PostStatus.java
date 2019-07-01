package io.duryskuba.interestmatcher.PostService.enums;

public enum PostStatus {

    ACTIVE("A"),
    DELETED("D");

    private final String shortcut;

    PostStatus(String shortcut) {
        this.shortcut = shortcut;
    }
}
