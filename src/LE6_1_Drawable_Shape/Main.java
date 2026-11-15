package LE6_1_Drawable_Shape;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    private Square square = new Square(300, 200, "DODGERBLUE", 100);

    private Rectangle rectangle;
    private Pane drawingArea;

    private Label areaLabel;
    private Label perimeterLabel;
    private Label centerLabel;

    private TextField sideField;
    private TextField dxField;
    private TextField dyField;

    @Override
    public void start(Stage stage) {

        drawingArea = new Pane();
        drawingArea.setPrefSize(600, 400);
        drawingArea.setStyle("-fx-background-color: white;");

        rectangle = new Rectangle();
        rectangle.setStroke(Color.BLACK);
        drawingArea.getChildren().add(rectangle);

        sideField = new TextField();
        sideField.setPromptText("Side length");

        Button updateButton = new Button("Update Side Length");
        updateButton.setOnAction(e -> updateSideLength());

        dxField = new TextField();
        dxField.setPromptText("DX");

        dyField = new TextField();
        dyField.setPromptText("DY");

        Button moveButton = new Button("Move Square");
        moveButton.setOnAction(e -> moveSquare());

        areaLabel = new Label();
        perimeterLabel = new Label();
        centerLabel = new Label();

        VBox controls = new VBox(10,
                new Label("Square Properties"),
                new Label("Side Length:"),
                sideField,
                updateButton,
                new Separator(),
                new Label("Move Square"),
                new Label("Horizontal (DX):"),
                dxField,
                new Label("Vertical (DY):"),
                dyField,
                moveButton,
                new Separator(),
                areaLabel,
                perimeterLabel,
                centerLabel
        );

        controls.setPadding(new Insets(15));
        controls.setPrefWidth(220);

        BorderPane root = new BorderPane();
        root.setCenter(drawingArea);
        root.setRight(controls);
        BorderPane.setAlignment(drawingArea, Pos.CENTER);

        updateDrawing();
        updateInformation();

        Scene scene = new Scene(root, 850, 450);

        stage.setTitle("Drawable Shape - Square");
        stage.setScene(scene);
        stage.show();
    }

    private void updateSideLength() {
        try {
            int length = Integer.parseInt(sideField.getText());

            square.setSideLength(length);

            updateDrawing();
            updateInformation();

        } catch (NumberFormatException e) {
            showError("Please enter a valid integer.");
        } catch (IllegalArgumentException e) {
            showError(e.getMessage());
        }
    }

    private void moveSquare() {
        try {
            int dx = Integer.parseInt(dxField.getText());
            int dy = Integer.parseInt(dyField.getText());

            square.move(dx, dy);

            updateDrawing();
            updateInformation();

        } catch (NumberFormatException e) {
            showError("Please enter valid integers for DX and DY.");
        }
    }

    private void updateDrawing() {
        int size = square.getSideLength();

        rectangle.setWidth(size);
        rectangle.setHeight(size);

        rectangle.setX(square.getCenterX() - size / 2.0);
        rectangle.setY(square.getCenterY() - size / 2.0);

        rectangle.setFill(Color.web(square.getColor()));
    }

    private void updateInformation() {
        areaLabel.setText("Area: " + square.computeArea());
        perimeterLabel.setText("Perimeter: " + square.computePerimeter());

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

