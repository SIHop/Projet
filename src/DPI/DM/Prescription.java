package DPI.DM;

public class Prescription {

    private String observation;
    private TypePrescription typePrescription;

    public Prescription(String observation, TypePrescription typePrescription) {
        this.observation = observation;
        this.typePrescription = typePrescription;
    }

}