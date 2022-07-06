package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import board.Board;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import player.Player1;
import player.Player2;

public class GameController {
	private Board board;
	private Player1 player1;
	private Player2 player2;
	
	public GameController(Board board, Player1 player1, Player2 player2) {
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
	}
	
    @FXML
    private Label name2;

    @FXML
    private Label name1;
    
    @FXML
    private Pane sq0;

    @FXML
    private Pane sq1;

    @FXML
    private Pane sq2;

    @FXML
    private Pane sq3;

    @FXML
    private Pane sq4;

    @FXML
    private Pane sq5;

    @FXML
    private Pane sq6;

    @FXML
    private Pane sq7;

    @FXML
    private Pane sq8;

    @FXML
    private Pane sq9;

    @FXML
    private Pane sq10;

    @FXML
    private Pane sq11;
    
    @FXML
    private Label score2;

    @FXML
    private Label score1;
    
    public void updateScore() {
    	score1.setText(Integer.toString(player1.getTotalPoint()));
    	score2.setText(Integer.toString(player2.getTotalPoint()));
    }
    
    public void initialize() {
    	updateScore();
    	ArrayList<Pane> paneList = new ArrayList<Pane>();
    	paneList.add(sq0);
    	paneList.add(sq1);
    	paneList.add(sq2);
    	paneList.add(sq3);
    	paneList.add(sq4);
    	paneList.add(sq5);
    	paneList.add(sq6);
    	paneList.add(sq7);
    	paneList.add(sq8);
    	paneList.add(sq9);
    	paneList.add(sq10);
    	paneList.add(sq11);
    	final String SQUARE_FILE_PATH = "/view/Square.fxml";
    	for (int i = 0; i < 12 ;i++) {
    		try {
    			FXMLLoader fxmlLoader = new FXMLLoader();
    			fxmlLoader.setLocation(getClass().getResource(SQUARE_FILE_PATH));
    			SquareController squareController = new SquareController(board, i);
    			fxmlLoader.setController(squareController);
    			AnchorPane anchorPane = new AnchorPane();
    			anchorPane  = fxmlLoader.load();
    			
    			
    			paneList.get(i).getChildren().add(anchorPane);

    	
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	
    }
}
