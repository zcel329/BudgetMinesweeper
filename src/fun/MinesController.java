package fun;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MinesController implements Controller {

  private static MinesController instance;

  public static MinesController getInstance() {
    return instance;
  }

  private final int DEFAULT_NUM = -3;
  private final int FLAG_NUM = -2;
  private Queue<Tile> mineQueue = new LinkedList<Tile>();
  private Set<Tile> alreadySearched = new HashSet<>();
  private MinedBoard board;

  @FXML private Stage stage;
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
    final double squareSize = 50; // Size of each square
    final double squareOffset = 1;

    resizeStage(
        (int) (xPosition + width * (squareSize + squareOffset) + 2 * (xPosition - squareOffset)),
        (int) (yPosition + height * (squareSize + squareOffset) + 2 * (yPosition - squareOffset)));

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        ImageView imageView = new ImageView();
        Image image = new Image(getClass().getResourceAsStream("resources/images/default.png"));
        imageView.setImage(image);
        imageView.setFitWidth(squareSize);
        imageView.setFitHeight(squareSize);
        imageView.setX(xPosition);
        imageView.setY(yPosition);

        xPosition += squareSize + squareOffset;

        final Tile tile = new Tile(i, j, imageView);
        board.setBoardTile(i, j, tile);
        imageView.setOnMouseClicked(
            new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                MouseButton buttonClicked = event.getButton();

                if (buttonClicked == MouseButton.PRIMARY) {
                  if (tile.isFlagged()) return;
                  handleSquareClick(tile);
                } else if (buttonClicked == MouseButton.SECONDARY) {
                  if (!tile.isDefault()) return;
                  handleRightClick(tile);
                }
                if (board.checkWin() == true) gameWin();
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
    tile.setDefault(false);

    // hit bomb -> game over
    if (tileVal == -1) gameOver();

    if (tileVal != 0) {
      updateImage(tileVal, tile.getImageView());
      alreadySearched.add(tile);
      return;
    }

    mineQueue.add(tile);
    while (!mineQueue.isEmpty()) {
      Tile searchTile = mineQueue.remove();
      searchTile.setDefault(false);
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

  public void handleRightClick(Tile tile) {
    if (tile.isFlagged()) updateImage(DEFAULT_NUM, tile.getImageView());
    else updateImage(FLAG_NUM, tile.getImageView());
    tile.setFlag(!tile.isFlagged());
  }

  private void updateImage(int num, ImageView imgView) {
    String s =
        switch (num) {
          case DEFAULT_NUM -> "default";
          case FLAG_NUM -> "flag";
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

  private void gameOver() {
    revealNonFlaggedBombs();
    showDialog("Game Over", "You've lost! :(", "You clicked a bomb and blew up!");
    System.exit(0);
  }

  private void gameWin() {
    flagRemainingBombs();
    showDialog("Game Win", "You've won! :)", "words");
    System.exit(0);
  }

  private static void showDialog(String title, String headerText, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(message);
    alert.showAndWait();
  }

  private void revealNonFlaggedBombs() {
    for (int i = 0; i < board.getHeight(); i++) {
      for (int j = 0; j < board.getWidth(); j++) {
        Tile tile = board.getTile(i, j);
        if (!tile.isFlagged() && tile.getTileNum() == -1) updateImage(-1, tile.getImageView());
      }
    }
  }

  private void flagRemainingBombs() {
    for (int i = 0; i < board.getHeight(); i++) {
      for (int j = 0; j < board.getWidth(); j++) {
        Tile tile = board.getTile(i, j);
        if (!tile.isFlagged() && tile.getTileNum() == -1)
          updateImage(FLAG_NUM, tile.getImageView());
      }
    }
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }

  private void resizeStage(int a, int b) {
    stage.setWidth(a);
    stage.setHeight(b);
  }
}
