package server.privat.tomove;

import org.springframework.scheduling.annotation.Scheduled;
import server.privat.ConnectedUsernames;
import ticking.JoeyClock;
import tomove.cqrs.commandstack.GoToTickCommand;
import tomove.cqrs.commandstack.PlayerAggregator;

public class Ticker {


    private ConnectedUsernames connectedUsernames;
    private PlayerAggregator playerAggregator;

    public Ticker(ConnectedUsernames connectedUsernames, PlayerAggregator playerAggregator) {
        this.connectedUsernames = connectedUsernames;
        this.playerAggregator = playerAggregator;
    }

    @Scheduled(fixedDelay = 2000)
    public void tick() {
        System.out.println("ticking");
        JoeyClock.tick();

        connectedUsernames.getAllConnectedUsernames()
                .forEach(
                        playerName -> playerAggregator.handleGoToTickCommand(
                                new GoToTickCommand(playerName, JoeyClock.currentTick())
                        )
                );
    }

}
