package fun.Controllers;

import fun.Controller;
import fun.MinedBoard;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

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
    double squareSize = 50; // Size of each square
    double squareOffset = 5;

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        // Rectangle square = new Rectangle(xPosition, yPosition, squareSize, squareSize);

        ImageView imageView = new ImageView();
        Image image =
            new Image("C:\\Users\\Zach\\Desktop\\Minesweeper\\src\\fun\\resources\\images\\testzzz.png"); // Replace with the actual path to your image
        imageView.setImage(image);
        imageView.setFitWidth(squareSize); // Set the width
        imageView.setFitHeight(squareSize); // Set the height
        imageView.setX(xPosition);
        imageView.setY(yPosition);

        xPosition += squareSize + squareOffset;

        // Add an event handler to detect clicks on the square
        final int iPos = i;
        final int jPos = j;
        imageView.setOnMouseClicked(
            new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent event) {
                // Call a method to handle the click event for the clicked square
                int num = handleSquareClick(iPos, jPos);
                updateImage(num, imageView);
              }
            });
        squarePane.getChildren().add(imageView);
      }
      yPosition += squareSize + squareOffset;
      xPosition = 20;
    }
  }

  // Method to handle the click event for the clicked square
  private int handleSquareClick(int i, int j) {
    return board.reveal(i, j);
  }

  private void updateImage(int num, ImageView imgView) {
    switch (num) {
      case 1:
        imgView.setImage(retrieveImage("one"));
        break;
    
      default:
        break;
    }
  }

  private Image retrieveImage(String s) {
    return new Image("C:\\Users\\Zach\\Desktop\\Minesweeper\\src\\fun\\resources\\images\\"+s+".png");
  }
}
