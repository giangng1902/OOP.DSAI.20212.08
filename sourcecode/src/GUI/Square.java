package GUI;


import java.io.IOException;

import board.Board;
import controller.SquareController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import player.Player;

public class Square extends Application {
	private static Board board;
	private static int point;
	private static int position;
	private static Player playingPlayer;

	@Override
	public void start(Stage primaryStage) {
		try {
			final String SQUARE_FILE_PATH = "/view/Square.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(SQUARE_FILE_PATH));
			SquareController squareController = new SquareController(board, position, playingPlayer);
			fxmlLoader.setController(squareController);
			Parent root;
			root = fxmlLoader.load();
			primaryStage.setTitle(null);
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public static void main(String[] args) {
		board = new Board();
		position = 0;
		
		launch(args);
	}
}
