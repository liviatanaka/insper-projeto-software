package com.insper.partida.equipe;

import com.insper.partida.common.ErrorDTO;
import com.insper.partida.equipe.exception.TeamAlreadyExistsException;
import com.insper.partida.equipe.exception.TeamDoNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class TeamControllerAdvice {

    @ExceptionHandler({TeamAlreadyExistsException.class,TeamDoNotExistsException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO teamException(RuntimeException ex) {
        ErrorDTO error = new ErrorDTO();
        error.setMessage(ex.getMessage());
        error.setCode(400);
        error.setTime(LocalDateTime.now());
        return error;
    }


}
