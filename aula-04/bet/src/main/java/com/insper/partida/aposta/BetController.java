package com.insper.partida.aposta;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bet")
public class BetController {

    @Autowired
    private BetService betService;

    @PostMapping
    public Bet createBet(@RequestBody BetSaveDTO bet) {
        return betService.saveBet(bet);
    }

    @GetMapping
    public List<Bet> getBets() {
        return betService.listBets();
    }

    @PutMapping("/{betId}/verify")
    public Bet verifyBet(@PathVariable String betId) {
        return betService.verifyBet(betId);
    }

    @GetMapping("/{gameId}/game")
    public List<Bet> getBetsByGame(@PathVariable String gameId) {
        return betService.getBetsByGame(gameId);
    }

}
