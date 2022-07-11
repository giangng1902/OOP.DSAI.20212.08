package controller;

import board.Board;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import player.Player;

public class SquareController {
	private Board board;
	private int point;
	private int position;
    static Player playingPlayer;
    public SquareController(Board board, int position, Player playingPlayer) {
    	this.board = board;
    	this.position = position;
    	SquareController.playingPlayer = playingPlayer;
    }
    
    public int getPoint() {
    	return board.getSquare(position).getPoint();
    }
    
    @FXML
    private Label pnt;


    @FXML
    private ImageView imgGem;
    
    public static Label static_pnt; 

	public void initialize() {
		
		
		if (getPoint() == 0) {
			imgGem.setVisible(false);
		} else {
			imgGem.setVisible(true);
		}
		pnt.setText(Integer.toString(getPoint()));
		pnt.setVisible(true);
		
	}
}
