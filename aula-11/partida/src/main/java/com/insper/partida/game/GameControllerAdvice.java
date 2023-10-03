package com.insper.partida.game;


import com.insper.partida.common.ErrorDTO;
import com.insper.partida.game.exception.GameDoNotExistsException;
import com.insper.partida.game.exception.GameTeamAgainstItselfException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class GameControllerAdvice {

    @ExceptionHandler({
            GameTeamAgainstItselfException.class,
            GameDoNotExistsException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO gameException(RuntimeException ex){
        ErrorDTO error = new ErrorDTO();
        error.setMessage(ex.getMessage());
        error.setCode(400);
        error.setTime(LocalDateTime.now());
        return error;
    }
}
