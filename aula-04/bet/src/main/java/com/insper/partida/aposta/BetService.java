package com.insper.partida.aposta;

import com.insper.partida.game.GameReturnDTO;
import com.insper.partida.game.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class BetService {

    @Autowired
    private BetRespository betRespository;

    @Autowired
    private GameService gameService;


    public Bet saveBet(BetSaveDTO bet) {
        GameReturnDTO gameReturnDTO = gameService.verifyGame(bet.getGameIdentifier());
        if (gameReturnDTO != null) {
            Bet bet_nova = new Bet();
            bet_nova.setResult(bet.getResult());
            bet_nova.setGameIdentifier(bet.getGameIdentifier());
            bet_nova.setStatus(BetStatus.WAIT);
            bet_nova.setIdentifier(UUID.randomUUID().toString());

            return betRespository.save(bet_nova);
        }
        throw new RuntimeException("Game not found");
    }

    public List<Bet> listBets() {

        return betRespository.findAll();
    }

    public Bet verifyBet(String betId) {
        Bet bet = betRespository.findByIdentifier(betId);
        if(bet == null){
            return null;
        }

        GameReturnDTO game = gameService.verifyGame(bet.getGameIdentifier());
        if (game != null) {
            if(game.getStatus().equals("FINISHED")){
                if(game.getScoreHome().equals(game.getScoreAway()) && bet.getResult().equals(BetResult.DRAW)){
                    bet.setStatus(BetStatus.WON);
                } else if(game.getScoreHome() < game.getScoreAway() && bet.getResult().equals(BetResult.AWAY)){
                    bet.setStatus(BetStatus.WON);
                } else if(game.getScoreHome() > game.getScoreAway() && bet.getResult().equals(BetResult.HOME)){
                    bet.setStatus(BetStatus.WON);
                } else {
                    bet.setStatus(BetStatus.LOST);
                }
            }
        }

        return bet;
    }


    public List<Bet> getBetsByGame(String gameId) {

        GameReturnDTO game = gameService.verifyGame(gameId);
        if (game != null) {
            return betRespository.findByGameIdentifier(gameId);
        }
        throw new RuntimeException("Game not found");

    }
}
