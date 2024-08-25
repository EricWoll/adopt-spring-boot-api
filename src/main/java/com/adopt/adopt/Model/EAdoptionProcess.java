package com.adopt.adopt.Model;

import lombok.Getter;

@Getter
public enum EAdoptionProcess {
    NONE("NONE"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETE("COMPLETE");

    private final String progress;

    EAdoptionProcess(String progress) {
        this.progress = progress;
    }
}
