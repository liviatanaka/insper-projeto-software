package com.insper.partida.tabela;

import com.insper.partida.equipe.TeamService;
import com.insper.partida.game.Game;
import com.insper.partida.game.GameService;
import com.insper.partida.equipe.dto.TeamReturnDTO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TabelaService {

    @Autowired
    private GameService gameService;

    @Autowired
    private TeamService teamService;

    public List<TimeDTO> getTabela() {

        List<TimeDTO> response = new ArrayList<>();

        List<TeamReturnDTO> times = teamService.listTeams();

        for (TeamReturnDTO time: times){
            List<Game> games = gameService.getGameByTeam(time.getIdentifier());

            TimeDTO timeDTO = new TimeDTO();
            timeDTO.setNome(time.getName());

            for (Game game : games){
                Integer pontos = verificaResultado(time, game);
                timeDTO.setPontos(timeDTO.getPontos() + pontos);

                if (pontos == 0){
                    timeDTO.setDerrotas(timeDTO.getDerrotas() +1);
                } else if (pontos == 1){
                    timeDTO.setEmpates(timeDTO.getEmpates() +1);
                } else if (pontos == 3){
                    timeDTO.setVitorias(timeDTO.getVitorias() +1);
                }

                if (game.getHome().equals(time.getIdentifier()) ){
                    timeDTO.setGolsContra(timeDTO.getGolsContra() + game.getScoreAway());
                    timeDTO.setGolsPro(timeDTO.getGolsPro() + game.getScoreHome());
                } else {
                    timeDTO.setGolsContra(timeDTO.getGolsContra() + game.getScoreHome());
                    timeDTO.setGolsPro(timeDTO.getGolsPro() + game.getScoreAway());
                }


            }

            response.add(timeDTO);
        }

//        response.sort((x,y) -> Integer.compare(x.getPontos(), y.getPontos()));
        response.sort(Comparator.comparingInt(TimeDTO::getPontos).reversed());
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



}
