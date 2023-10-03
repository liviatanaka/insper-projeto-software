package com.insper.partida.equipe.exception;

public class TeamDoNotExistsException extends RuntimeException {

    public  TeamDoNotExistsException(String identifier) {
        super("Time %s não existe! :/".formatted(identifier));
    }

}
