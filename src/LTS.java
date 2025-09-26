import java.util.Date;
import java.util.Random;

public class LTS {
    private final Date dateManufactured = new Date(); // Stores date when LTS was built
    private final double dryMass = 620500;  // Represents mass of LTS before fueling (DEFAULT : 620,500)
    private int ltsId; // Unique LTS registration ID
    private int missionTime = 0; // Time in seconds
    private double fuelMass = 3800000;  // Represents mass of fuel required for launch and flight (DEFAULT : 3,800,000)
    private double cargoMass = 0;  // Represents the payload mass of the LTS ( DEFAULT : 0)
    private double grossMass; // Represents the total mass of the fully fueled and loaded LTS (Not calculated yet)
    private double burn; //Represents the constant amount of fuel consumed per second (1% of the initial fuel mass)
    private String manufacturer = "Celestial Transport Technologies"; // Storing the name of the company that built the LTS (DEFAULT : " Celestial Transport Technologies") 1 MANUFACTURER ONLY

    public LTS() {
        burn = fuelMass * 0.01;
        randomID();
        updateGross(); // Recalculating Gross Mass
    }

    public LTS(double fuelMass, double cargoMass){
        if(fuelMass < 0){
            System.out.println("Error: Fuel Mass input invalid. Using Default");
        } else {
            this.fuelMass = fuelMass;
        }
        if(cargoMass < 0){
            System.out.println("Error: Cargo Mass input invalid. Using Default");
        } else{
            this.cargoMass = cargoMass;
        }

        burn = fuelMass *0.01;
        randomID();
        updateGross();
    }

    private void randomID(){ // Helper Method for random ID
        Random rand = new Random();
        this.ltsId = 100000 + rand.nextInt(900000);
    }

    private void updateGross(){  //Helper Method for recalculating gross mass
        this.grossMass = dryMass + fuelMass + cargoMass;
    }


    public String getManufacturer(){
        return manufacturer;
    }

    public double getFuelMass(){
        return fuelMass;
    }

    public double getCargoMass(){
        return cargoMass;
    }

    public int getMissionTime(){
        return missionTime;
    }

    public double getDryMass(){
        return dryMass;
    }

    public double getGrossMass(){
        return grossMass;
    }

    public int getLtsID(){
        return ltsId;
    }

    public Date getDateManufactured(){
        return dateManufactured;
    }

    public void setManufacturer(String manufacturer){
        if(manufacturer != null && !manufacturer.isEmpty()){
            this.manufacturer = manufacturer;
            System.out.printf("Manufacturer changed to: %s \n", manufacturer);
        } else {
            System.out.println("Error: Manufacturer name empty");
        }
    }

    public void setFuelMass(double fuelMass){
        if (missionTime > 0){
            System.out.println("Error: Cannot change fuel after launch");
            return;
        }
        if (fuelMass < 0) {
            System.out.println("Error: Fuel mass negative. Unchanged");
            return;
        }
        this.fuelMass = fuelMass;
        burn = fuelMass * 0.01;
        updateGross();
        System.out.printf("Fuel mass changed to %.2f \n", fuelMass);
    }

    public void setCargoMass(double cargoMass){
        if (cargoMass < 0) {
            System.out.println("Error: Cargo mass is negative");
            return;
        }
        this.cargoMass = cargoMass;
        updateGross();
        System.out.printf("Cargo mass changed to: %.2f \n", cargoMass);
    }

    public void increaseMissionTime() {
        if(fuelMass >= burn){
            fuelMass -= burn;
        }
        if(missionTime < 200) {
            missionTime += 5;
        }
        updateGross();
    }

    public void deployCargo(){
        if (missionTime < 200){
            System.out.println("Error, mission time should be 200 to deploy cargo: Wait "+ (200 - missionTime) + " seconds. \n");
        }
        else {
            cargoMass = 0;  //  cargo mass changes to 0 as launch was deployed.
            updateGross();
            System.out.println("Cargo was deployed into space!");
        }
    }
}
