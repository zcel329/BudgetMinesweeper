package fun;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MinesController implements Controller {

  private static MinesController instance;

  public static MinesController getInstance() {
    return instance;
  }
  private Queue<Tile> mineQueue = new LinkedList<Tile>();
  private Set<Tile> alreadySearched = new HashSet<>();



  private MinedBoard board;

  @FXML private Pane squarePane;

  @FXML private Label timerLabel;

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
            new Image(getClass().getResourceAsStream("resources/images/default.png"));
        imageView.setImage(image);
        imageView.setFitWidth(squareSize); // Set the width
        imageView.setFitHeight(squareSize); // Set the height
        imageView.setX(xPosition);
        imageView.setY(yPosition);

        xPosition += squareSize + squareOffset;

        // Add an event handler to detect clicks on the square
        final Tile tile = new Tile(i, j, imageView);
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
    int tileVal = tile.getTileNum();

    if (tileVal != 0) {
      updateImage(tileVal, tile.getImageView());
      alreadySearched.add(tile);
      return;
    }

    mineQueue.add(tile);
    while (!mineQueue.isEmpty()) {
      Tile searchTile = mineQueue.remove();
      if (alreadySearched.contains(searchTile)) continue;
      if (searchTile.getTileNum() == 0) {
        for (int a = -1; a < 2; a++) {
          for (int b = -1; b < 2; b++) {
            int vertVal = a + searchTile.getY();
            int horzVal = b + searchTile.getX();
            if (vertVal > -1
                && vertVal < board.getHeight()
                && horzVal > -1
                && horzVal < board.getWidth()
                && !alreadySearched.contains(searchTile)) {
              Tile newTile = board.getTile(vertVal, horzVal);
              mineQueue.add(newTile);
            }
          }
        }
      }
      updateImage(searchTile.getTileNum(), searchTile.getImageView());
      alreadySearched.add(searchTile);
    }
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
    return new Image(getClass().getResourceAsStream("resources/images/" + s + ".png"));
  }

  public void updateTimerLabel(String s) {
    timerLabel.setText(s);
  }
}
