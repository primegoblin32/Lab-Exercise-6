package LE6_2_Patient_Billing;

public class Doctor extends Person{
    private String specialty;
    private double officeVisitFee;

    public Doctor() {
        super();
        specialty = "No specialty";
        officeVisitFee = 0.0;
    }

    public Doctor(String name, String specialty, double officeVisitFee) {
        super(name);
        this.specialty = specialty;
        this.officeVisitFee = officeVisitFee;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setOfficeVisitFee(double officeVisitFee) {
        this.officeVisitFee = officeVisitFee;
    }

    public String getSpecialty() {
        return specialty;
    }

    public double getOfficeVisitFee() {
        return officeVisitFee;
    }

    public void writeOutput() {
        super.writeOutput();
        System.out.println("Specialty: " + specialty);
        System.out.println("Office Visit Fee: " + officeVisitFee);
    }

    public boolean equals(Doctor otherDoctor) {
        return super.hasSameName(otherDoctor)
                && this.specialty.equalsIgnoreCase(otherDoctor.specialty)
                && this.officeVisitFee == otherDoctor.officeVisitFee;
    }
}

