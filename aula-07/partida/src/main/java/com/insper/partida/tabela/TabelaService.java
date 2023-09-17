package com.insper.partida.tabela;

import com.insper.partida.equipe.Team;
import com.insper.partida.equipe.TeamService;
import com.insper.partida.game.Game;
import com.insper.partida.game.GameService;
import com.insper.partida.equipe.dto.TeamReturnDTO;
import com.insper.partida.game.dto.EditGameDTO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TabelaService {



    @Autowired
    private TabelaRepository tabelaRepository;

    public List<Tabela> getTabela() {

        List<Tabela> response = tabelaRepository.findAll();

//        response.sort((x,y) -> Integer.compare(x.getPontos(), y.getPontos()));
        response.sort(Comparator.comparingInt(Tabela::getPontos).reversed());
        return response;
    }

    private Integer verificaResultado(TeamReturnDTO time, Game game) {
        if (game.getScoreHome() == game.getScoreAway()){
            return 1;
        } else if (game.getScoreHome() > game.getScoreAway() && game.getHome().equals(time.getIdentifier())){
            return 3;
        }else if (game.getScoreHome() < game.getScoreAway() && game.getAway().equals(time.getIdentifier())){
            return 3;
        }

        return 0;
    }

    public void createTabela(String nome, String identifier){
        Tabela verifica = tabelaRepository.findByTimeIdentifier(identifier);
        if (verifica == null){
            Tabela tabela = new Tabela();
            tabela.setTimeIdentifier(identifier);
            tabela.setNome(nome);
            tabelaRepository.save(tabela);
        }
    }

    public void editTabela(Game game){

        Tabela home = tabelaRepository.findByTimeIdentifier(game.getHome());
        Tabela away = tabelaRepository.findByTimeIdentifier(game.getAway());

        int scoreHome = game.getScoreHome();
        int scoreAway = game.getScoreAway();

        // vitória, derrota e empate + pontos
        if (scoreAway > scoreHome){
            home.setDerrotas(home.getDerrotas() + 1);
            away.setVitorias(away.getVitorias() + 1);
            away.setPontos(away.getPontos() + 3);
        } else if (scoreAway < scoreHome){
            home.setVitorias(home.getVitorias() + 1);
            away.setDerrotas(away.getDerrotas() + 1);
            home.setPontos(home.getPontos() + 3);
        } else {
            home.setEmpates(home.getEmpates() + 1);
            away.setEmpates(away.getEmpates() + 1);
            home.setPontos(home.getPontos() + 1);
            away.setPontos(away.getPontos() + 1);
        }

        // numero de gols
        home.setGolsPro(home.getGolsPro() + scoreHome);
        home.setGolsContra(home.getGolsContra() + scoreAway);

        away.setGolsPro(away.getGolsPro() + scoreAway);
        away.setGolsContra(away.getGolsContra() + scoreHome);

        Tabela t1 = tabelaRepository.save(home);
        Tabela t2 = tabelaRepository.save(away);
    }


}
