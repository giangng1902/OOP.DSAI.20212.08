package controller;

import board.Board;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SquareController {
	private Board board;
	private int point;
	private int position;
	private int dir;
    
    public SquareController(Board board, int position) {
    	this.board = board;
    	this.position = position;
    }
    
    public int getPoint() {
    	return board.getSquare(position).getPoint();
    }
    
    @FXML
    private Label pnt;


    @FXML
    private ImageView dirLeft;

    @FXML
    private ImageView imgGem;

    @FXML
    private ImageView dirRight;

    @FXML
    void chooseSquClicked(MouseEvent event) {
    	if (position == 0 || position == 6) {
        	Alert alert = new Alert(AlertType.INFORMATION);
        	alert.setTitle("Notification");
        	alert.setHeaderText(null);
        	alert.setContentText("Cannot not choose this square!");
    	}
    	else {
    		dirLeft.setVisible(true);
    		dirRight.setVisible(true);
    	}
    }

    @FXML
    void leftClicked(MouseEvent event) {
    	dir = -1;
    	dirLeft.setVisible(false);
    	dirRight.setVisible(false);
    }

    @FXML
    void rightClicked(MouseEvent event) {
    	dir = 1;
    	dirLeft.setVisible(false);
    	dirRight.setVisible(false);	
    }
    
	public void initialize() {
		
		
		if (getPoint() == 0) {
			imgGem.setVisible(false);
		} else {
			imgGem.setVisible(true);
		}
		pnt.setText(Integer.toString(getPoint()));
		pnt.setVisible(true);
		
    	dirLeft.setVisible(false);
    	dirRight.setVisible(false);
	}
}
