package GUI;

import java.io.IOException;

import board.Board;
import controller.EndScreenController;
import controller.MenuScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import player.Player;
import player.Player1;
import player.Player2;

public class MenuScreen extends Application {
	private static Player1 player1;
	private static Player2 player2;
	private static Board board;
	private static Player playingPlayer;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			final String FILE_PATH = "/view/MenuScreen.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FILE_PATH));
			MenuScreenController menuController = new MenuScreenController(board, player1, player2, playingPlayer);
			fxmlLoader.setController(menuController);
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
		player1 = new Player1("player1");
		player2 = new Player2("player2");
		board = new Board();
		playingPlayer = player1;
		
		launch(args);
	}
}