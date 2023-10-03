package com.insper.partida.game.exception;

public class GameDoNotExistsException extends RuntimeException {

    public  GameDoNotExistsException(String identifier) {
        super("Jogo %s não existe! :/".formatted(identifier));
    }

}
