package com.insper.partida.tabela;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("tabela")
public class Tabela {

    private String nome;

    @Id
    private String timeIdentifier;
    private Integer pontos = 0;
    private Integer vitorias = 0;
    private Integer empates = 0;
    private Integer derrotas = 0;
    private Integer golsContra = 0;
    private Integer golsPro = 0;

}
