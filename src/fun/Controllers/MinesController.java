package fun.Controllers;

import fun.Controller;
import fun.MinedBoard;
import fun.Tile;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MinesController implements Controller {

  private Stack<Tile> mineStack = new Stack<>();
  private Set<Tile> alreadySearched = new HashSet<>();

  private static MinesController instance;

  public static MinesController getInstance() {
    return instance;
  }

  MinedBoard board;

  @FXML private Pane squarePane;

  @FXML
  public void initialize() {
    instance = this;
  }

  public void createSquares(int height, int width, int mines) {

    board = new MinedBoard(height, width, mines);

    double xPosition = 20; // Starting X position
    double yPosition = 50; // Starting Y position
    double squareSize = 50; // Size of each square
    double squareOffset = 5;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        ImageView imageView = new ImageView();
        Image image =
            new Image(
                "C:\\Users\\Zach\\Desktop\\Minesweeper\\src\\fun\\r"
                    + "esources\\images\\default.png");
        imageView.setImage(image);
        imageView.setFitWidth(squareSize); // Set the width
        imageView.setFitHeight(squareSize); // Set the height
        imageView.setX(xPosition);
        imageView.setY(yPosition);

        xPosition += squareSize + squareOffset;

        // Add an event handler to detect clicks on the square
        final Tile tile = new Tile(i,j,imageView);
        board.setBoardTile(i, j, tile);
        imageView.setOnMouseClicked(
            new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                // Call a method to handle the click event for the clicked square
                handleSquareClick(tile);
              }
            });
        squarePane.getChildren().add(imageView);
      }
      yPosition += squareSize + squareOffset;
      xPosition = 20;
    }
    board.initialiseBoard();
  }

  // Method to handle the click event for the clicked square
  private void handleSquareClick(Tile tile) {
    int i = tile.getY();
    int j = tile.getX();
    int num = board.reveal(i,j);
    updateImage(num, tile.getImageView());
  }

  private void updateImage(int num, ImageView imgView) {
    String s =
        switch (num) {
          case -1 -> "bomb";
          case 0 -> "zero";
          case 1 -> "one";
          case 2 -> "two";
          case 3 -> "three";
          case 4 -> "four";
          case 5 -> "five";
          default -> null;
        };

    if (s == null) return;
    imgView.setImage(retrieveImage(s));
  }

  private Image retrieveImage(String s) {
    return new Image(
        "C:\\Users\\Zach\\Desktop\\Minesweeper\\src\\fun\\resources\\images\\" + s + ".png");
  }
}
