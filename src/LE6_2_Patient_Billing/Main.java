package LE6_2_Patient_Billing;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private TextField patientNameField;
    private TextField patientIDField;

    private TextField doctorNameField;
    private TextField specialtyField;
    private TextField feeField;

    private TextArea outputArea;
    private Label totalIncomeLabel;

    private ArrayList<Billing> billingRecords = new ArrayList<>();

    private double totalIncome = 0.0;

    @Override
    public void start(Stage stage) {

        Label title = new Label("CLINIC BILLING SYSTEM");
        title.setStyle(
                "-fx-font-size: 25px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #1e3a8a;"
        );

        Label subtitle = new Label(
                "Patient and Doctor Management"
        );

        subtitle.setStyle("-fx-font-size: 13px;");

        Label patientLabel = new Label("PATIENT INFORMATION");
        patientLabel.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #2563eb;"
        );

        patientNameField = new TextField();
        patientNameField.setPromptText("Enter patient name");

        patientIDField = new TextField();
        patientIDField.setPromptText("Enter patient ID");

        GridPane patientGrid = new GridPane();
        patientGrid.setHgap(10);
        patientGrid.setVgap(10);

        patientGrid.add(new Label("Patient Name:"), 0, 0);
        patientGrid.add(patientNameField, 1, 0);

        patientGrid.add(new Label("Patient ID:"), 0, 1);
        patientGrid.add(patientIDField, 1, 1);

        Label doctorLabel = new Label("DOCTOR INFORMATION");
        doctorLabel.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #2563eb;"
        );

        doctorNameField = new TextField();
        doctorNameField.setPromptText("Enter doctor name");

        specialtyField = new TextField();
        specialtyField.setPromptText("Enter specialty");

        feeField = new TextField();
        feeField.setPromptText("Enter office visit fee");

        GridPane doctorGrid = new GridPane();
        doctorGrid.setHgap(10);
        doctorGrid.setVgap(10);

        doctorGrid.add(new Label("Doctor Name:"), 0, 0);
        doctorGrid.add(doctorNameField, 1, 0);

        doctorGrid.add(new Label("Specialty:"), 0, 1);
        doctorGrid.add(specialtyField, 1, 1);

        doctorGrid.add(new Label("Visit Fee:"), 0, 2);
        doctorGrid.add(feeField, 1, 2);

        Button addButton = new Button("Add Billing Record");
        Button clearButton = new Button("Clear Fields");
        Button resetButton = new Button("Reset Records");

        addButton.setStyle(
                "-fx-background-color: #2563eb;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 10 15;"
        );

        clearButton.setStyle(
                "-fx-background-color: #64748b;" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 10 15;"
        );

        resetButton.setStyle(
                "-fx-background-color: #dc2626;" +
                        "-fx-text-fill: white;" +
                        "-fx-padding: 10 15;"
        );

        addButton.setOnAction(e -> addBillingRecord());
        clearButton.setOnAction(e -> clearFields());
        resetButton.setOnAction(e -> resetRecords());

        HBox buttonBox = new HBox(
                10, addButton, clearButton, resetButton
        );

        buttonBox.setAlignment(Pos.CENTER);

        Label recordsLabel = new Label("BILLING RECORDS");
        recordsLabel.setStyle(
                "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #2563eb;"
        );

        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setWrapText(true);
        outputArea.setPrefRowCount(12);
        outputArea.setPromptText(
                "Billing records will appear here..."
        );

        totalIncomeLabel = new Label(
                "Total Clinic Income: PHP 0.00"
        );

        totalIncomeLabel.setStyle(
                "-fx-font-size: 20px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #15803d;"
        );

        VBox root = new VBox(15);

        root.setPadding(new Insets(25));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #f1f5f9;");

        VBox patientSection = new VBox(
                10, patientLabel, patientGrid
        );

        VBox doctorSection = new VBox(
                10, doctorLabel, doctorGrid
        );

        patientSection.setPadding(new Insets(15));
        doctorSection.setPadding(new Insets(15));

        patientSection.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 10;"
        );

        doctorSection.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 10;"
        );

        root.getChildren().addAll(
                title,
                subtitle,
                patientSection,
                doctorSection,
                buttonBox,
                recordsLabel,
                outputArea,
                totalIncomeLabel
        );

        Scene scene = new Scene(root, 650, 850);

        stage.setTitle("Clinic Billing System");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void addBillingRecord() {

        try {

            String patientName =
                    patientNameField.getText().trim();

            String patientID =
                    patientIDField.getText().trim();

            String doctorName =
                    doctorNameField.getText().trim();

            String specialty =
                    specialtyField.getText().trim();

            String feeText =
                    feeField.getText().trim();

            if (patientName.isEmpty() ||
                    patientID.isEmpty() ||
                    doctorName.isEmpty() ||
                    specialty.isEmpty() ||
                    feeText.isEmpty()) {

                showAlert(
                        "Input Error",
                        "Please fill in all fields."
                );

                return;
            }

            double fee = Double.parseDouble(feeText);

            if (!Double.isFinite(fee) || fee < 0) {
                showAlert(
                        "Invalid Fee",
                        "Please enter a valid non-negative fee."
                );
                return;
            }

            Patient patient = new Patient(
                    patientName,
                    patientID
            );

            Doctor doctor = new Doctor(
                    doctorName,
                    specialty,
                    fee
            );

            Billing billing = new Billing(
                    patient,
                    doctor
            );

            billingRecords.add(billing);

            totalIncome += billing.getBillingAmount();

            outputArea.appendText(
                    "\n================================\n" +
                            "BILLING RECORD #" +
                            billingRecords.size() + "\n" +
                            "================================\n" +

                            "PATIENT INFORMATION\n" +
                            "Name: " + patient.getName() + "\n" +
                            "Patient ID: " + patient.getPatientID() + "\n\n" +

                            "DOCTOR INFORMATION\n" +
                            "Name: " + doctor.getName() + "\n" +
                            "Specialty: " + doctor.getSpecialty() + "\n" +
                            "Office Visit Fee: PHP " +
                            String.format("%.2f", doctor.getOfficeVisitFee()) +
                            "\n\n" +

                            "Billing Amount: PHP " +
                            String.format("%.2f", billing.getBillingAmount()) +
                            "\n"
            );

            totalIncomeLabel.setText(
                    "Total Clinic Income: PHP " +
                            String.format("%.2f", totalIncome)
            );

            clearFields();

            showAlert(
                    "Success",
                    "Billing record added successfully!"
            );

        } catch (NumberFormatException ex) {

            showAlert(
                    "Invalid Input",
                    "Office visit fee must be a valid number."
            );
        }
    }

    private void clearFields() {

        patientNameField.clear();
        patientIDField.clear();
        doctorNameField.clear();
        specialtyField.clear();
        feeField.clear();
    }

    private void resetRecords() {

        Alert confirmation = new Alert(
                Alert.AlertType.CONFIRMATION
        );

        confirmation.setTitle("Reset Records");
        confirmation.setHeaderText(
                "Are you sure you want to reset all records?"
        );

        confirmation.setContentText(
                "This will delete all billing records and reset the income."
        );

        confirmation.showAndWait().ifPresent(response -> {

            if (response == ButtonType.OK) {

                billingRecords.clear();

                totalIncome = 0.0;

                outputArea.clear();

                totalIncomeLabel.setText(
                        "Total Clinic Income: PHP 0.00"
                );

                clearFields();
            }
        });
    }

    private void showAlert(String title, String message) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
