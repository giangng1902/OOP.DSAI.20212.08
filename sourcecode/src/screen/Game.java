package screen;

import java.io.IOException;

import board.Board;
import controller.GameController;
import controller.SquareController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import player.Player1;
import player.Player2;

public class Game extends Application {
	private static Board board;
	private static Player1 player1;
	private static Player2 player2;
	

	@Override
	public void start(Stage primaryStage) {
		try {
			final String FILE_PATH = "/view/Game.fxml";
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FILE_PATH));
			GameController gameController = new GameController(board, player1, player2);
			fxmlLoader.setController(gameController);
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
		board  = new Board();
		player1 = new Player1("giang1");
		player2 = new Player2("giang2");
		launch(args);
	}
}
