package controller;

import java.io.IOException;

import board.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import player.Player;
import player.Player1;
import player.Player2;

public class MenuScreenController {
	private Board board;
	private Player1 player1;
	private Player2 player2;
	private Player playingPlayer;

    @FXML
    private Button buttob_start;

    @FXML
    private Button button_rule;
    
    private Stage primaryStage;
    private Scene scene;
    private Parent root;
    
    public MenuScreenController(Board board, Player1 player1, Player2 player2, Player playingPlayer) {
		this.player1 = player1;
		this.player2 = player2;
		this.board = board;
		this.playingPlayer = playingPlayer;
	}
	@FXML
	void switch_Start(ActionEvent event)throws IOException{  	   	  
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
	@FXML
	void switch_Rule(ActionEvent event)throws IOException{
		try {
			final String FILE_PATH = "/view/RuleScreen.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FILE_PATH));
			player1 = new Player1(player1.getName());
			player2 = new Player2(player2.getName());
			playingPlayer = player1;
			RuleController ruleController = new RuleController(board, player1, player2, playingPlayer);
			fxmlLoader.setController(ruleController);
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
	
	@FXML
	void quit_menu(ActionEvent event){

		System.exit(0);

  }}