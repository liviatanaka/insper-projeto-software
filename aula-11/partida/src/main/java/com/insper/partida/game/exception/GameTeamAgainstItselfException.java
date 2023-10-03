package com.insper.partida.game.exception;

public class GameTeamAgainstItselfException extends RuntimeException{
    public GameTeamAgainstItselfException() {super("Time não pode jogar contra si mesmo :O");}
}
