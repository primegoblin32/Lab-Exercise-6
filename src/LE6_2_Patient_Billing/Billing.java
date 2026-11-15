package LE6_2_Patient_Billing;

public class Billing {
    private Patient patient;
    private Doctor doctor;

    public Billing() {
        patient = new Patient();
        doctor = new Doctor();
    }

    public Billing(Patient patient, Doctor doctor) {
        this.patient = patient;
        this.doctor = doctor;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public double getBillingAmount() {
        return doctor.getOfficeVisitFee();
    }

    public void writeOutput() {
        System.out.println("----- BILLING INFORMATION -----");

        patient.writeOutput();

        System.out.println();

        doctor.writeOutput();

        System.out.println("Billing Amount: "
                + getBillingAmount());
    }

    public boolean equals(Billing otherBilling) {
        return this.patient.equals(otherBilling.patient)
                && this.doctor.equals(otherBilling.doctor);
    }
}