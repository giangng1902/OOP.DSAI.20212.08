package controller;

import java.io.IOException;

import board.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import player.Player;
import player.Player1;
import player.Player2;

public class EndScreenController {
	private Board board;
	private Player1 player1;
	private Player2 player2;
	private Player playingPlayer;
	
	public EndScreenController(Board board, Player1 player1, Player2 player2, Player playingPlayer) {
		this.player1 = player1;
		this.player2 = player2;
		this.board = board;
		this.playingPlayer = playingPlayer;
	}
	
	@FXML
    private Label wonPlayer;
    
    @FXML
    void btnQuit(ActionEvent event) {
    	System.exit(0);
    }

    @FXML
    void btnRestart(ActionEvent event) {
		try {
			final String FILE_PATH = "/view/Game.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FILE_PATH));
			player1 = new Player1(player1.getName());
			player2 = new Player2(player2.getName());
			playingPlayer = player1;
			GameController gameController = new GameController(new Board(), player1, player2, playingPlayer);
			fxmlLoader.setController(gameController);
			Parent root;
			root = fxmlLoader.load();
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    		
    		stage.setScene(new Scene(root));
    		stage.setTitle(null);
    		stage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void initialize() {
    	Player wonplayer;
    	if (player1.getTotalPoint() > player2.getTotalPoint()) {
    		wonplayer = player1;
    	} else {
    		wonplayer = player2;
    	}
    	
    	wonPlayer.setText(wonplayer.infor());
    }
}
