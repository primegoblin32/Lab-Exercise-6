package LE6_1_Drawable_Shape;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 25;

    private Square square = new Square(25, 12, "BLUE", 7);

    private TextArea drawingArea;
    private TextField sideField;
    private TextField characterField;
    private TextField dxField;
    private TextField dyField;

    private ComboBox<String> colorBox;

    private Label areaLabel;
    private Label perimeterLabel;
    private Label centerLabel;

    @Override
    public void start(Stage stage) {

        drawingArea = new TextArea();
        drawingArea.setEditable(false);
        drawingArea.setWrapText(false);
        drawingArea.setFont(Font.font("Monospaced", 14));
        drawingArea.setPrefRowCount(HEIGHT);
        drawingArea.setPrefColumnCount(WIDTH);

        sideField = new TextField("7");
        characterField = new TextField("*");
        dxField = new TextField();
        dyField = new TextField();

        colorBox = new ComboBox<>();
        colorBox.getItems().addAll(
                "BLACK", "RED", "BLUE", "GREEN", "ORANGE", "PURPLE"
        );
        colorBox.setValue("BLUE");

        Button drawButton = new Button("Draw Square");
        drawButton.setMaxWidth(Double.MAX_VALUE);
        drawButton.setOnAction(e -> drawSquare());

        Button moveButton = new Button("Move Square");
        moveButton.setMaxWidth(Double.MAX_VALUE);
        moveButton.setOnAction(e -> moveSquare());

        Button resetButton = new Button("Reset");
        resetButton.setMaxWidth(Double.MAX_VALUE);
        resetButton.setOnAction(e -> resetSquare());

        areaLabel = new Label();
        perimeterLabel = new Label();
        centerLabel = new Label();

        VBox controls = new VBox(10,
                new Label("SQUARE PROPERTIES"),
                new Label("Side Length:"),
                sideField,
                new Label("Drawing Character:"),
                characterField,
                new Label("Color:"),
                colorBox,
                drawButton,

                new Separator(),

                new Label("MOVE SQUARE"),
                new Label("Horizontal (DX):"),
                dxField,
                new Label("Vertical (DY):"),
                dyField,
                moveButton,
                resetButton,

                new Separator(),

                new Label("SQUARE INFORMATION"),
                areaLabel,
                perimeterLabel,
                centerLabel
        );

        controls.setPadding(new Insets(15));
        controls.setPrefWidth(200);

        BorderPane root = new BorderPane();
        root.setCenter(drawingArea);
        root.setRight(controls);
        root.setPadding(new Insets(10));

        drawSquare();

        Scene scene = new Scene(root, 950, 500);

        stage.setTitle("Drawable Shape - Square");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void drawSquare() {
        try {
            int sideLength = Integer.parseInt(sideField.getText());

            if (characterField.getText().length() != 1) {
                throw new IllegalArgumentException(
                        "Enter exactly one drawing character."
                );
            }

            square.setSideLength(sideLength);
            square.setCharacter(characterField.getText().charAt(0));

            // Update color by creating a new square.
            Square updatedSquare = new Square(
                    square.getCenterX(),
                    square.getCenterY(),
                    colorBox.getValue(),
                    square.getSideLength(),
                    square.getCharacter()
            );

            square = updatedSquare;

            updateDisplay();

        } catch (NumberFormatException e) {
            showError("Please enter a valid side length.");
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }

    private void moveSquare() {
        try {
            int dx = Integer.parseInt(dxField.getText());
            int dy = Integer.parseInt(dyField.getText());

            square.move(dx, dy);

            updateDisplay();

            dxField.clear();
            dyField.clear();

        } catch (NumberFormatException e) {
            showError("Please enter valid DX and DY values.");
        }
    }

    private void resetSquare() {
        square = new Square(25, 12, "BLUE", 7);

        sideField.setText("7");
        characterField.setText("*");
        colorBox.setValue("BLUE");

        dxField.clear();
        dyField.clear();

        updateDisplay();
    }

    private void updateDisplay() {
        char[][] canvas = new char[HEIGHT][WIDTH];

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                canvas[row][col] = ' ';
            }
        }

        int startX = square.getCenterX() - square.getSideLength() / 2;
        int startY = square.getCenterY() - square.getSideLength() / 2;

        int endX = startX + square.getSideLength();
        int endY = startY + square.getSideLength();

        for (int row = startY; row < endY; row++) {
            for (int col = startX; col < endX; col++) {

                if (row == startY || row == endY - 1 ||
                        col == startX || col == endX - 1) {

                    if (row >= 0 && row < HEIGHT &&
                            col >= 0 && col < WIDTH) {

                        canvas[row][col] = square.getCharacter();
                    }
                }
            }
        }

        StringBuilder output = new StringBuilder();

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                output.append(canvas[row][col]).append(' ');
            }

            output.append('\n');
        }

        drawingArea.setText(output.toString());

        String color = square.getColor().toLowerCase();

        drawingArea.setStyle(
                "-fx-control-inner-background: white;" +
                        "-fx-text-fill: " + color + ";" +
                        "-fx-font-family: 'Monospaced';" +
                        "-fx-font-size: 14px;"
        );

        areaLabel.setText("Area: " + square.computeArea());
        perimeterLabel.setText(
                "Perimeter: " + square.computePerimeter()
        );

        centerLabel.setText(
                "Center: (" + square.getCenterX()
                        + ", " + square.getCenterY() + ")"
        );
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
