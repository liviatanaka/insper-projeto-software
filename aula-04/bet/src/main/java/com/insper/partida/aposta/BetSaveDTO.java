package com.insper.partida.aposta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BetSaveDTO {

    private BetResult result;

    private String gameIdentifier;
}
