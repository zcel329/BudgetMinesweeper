package fun.Controllers;


import fun.Controller;
import fun.MinedBoard;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class MinesController implements Controller {

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
    double squareSize = 25; // Size of each square
    double squareOffset = 5;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Rectangle square = new Rectangle(xPosition, yPosition, squareSize, squareSize);

        xPosition += squareSize + squareOffset;

        // Add an event handler to detect clicks on the square
        final int iPos = i;
        final int jPos = j;
        square.setOnMouseClicked(
            new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                // Call a method to handle the click event for the clicked square
                handleSquareClick(iPos,jPos);
              }
            });
        squarePane.getChildren().add(square);

      }
      yPosition += squareSize + squareOffset;
      xPosition = 20;
    }
  }

  // Method to handle the click event for the clicked square
  private void handleSquareClick(int i, int j) {
    System.out.println(board.reaveal(i, j));
  }
}
