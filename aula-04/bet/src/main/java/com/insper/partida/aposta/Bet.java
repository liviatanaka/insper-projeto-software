package com.insper.partida.aposta;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("bet")
public class Bet {

    @Id
    private String id;

    private String identifier;

    private BetStatus status;

    private BetResult result;

    private String gameIdentifier;

}
