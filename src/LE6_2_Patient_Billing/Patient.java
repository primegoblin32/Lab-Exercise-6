package LE6_2_Patient_Billing;

public class Patient extends Person {
    private String patientID;

    public Patient() {
        super();
        patientID = "No ID yet";
    }

    public Patient(String name, String patientID) {
        super(name);
        this.patientID = patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void writeOutput() {
        super.writeOutput();
        System.out.println("Patient ID: " + patientID);
    }

    public boolean equals(Patient otherPatient) {
        return super.hasSameName(otherPatient)
                && this.patientID.equalsIgnoreCase(otherPatient.patientID);
    }
}

