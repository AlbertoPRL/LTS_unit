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

    public LTS() {  // No-argument constructor
        burn = fuelMass * 0.01;
        randomID();  // Initializes LTS unit with default values
        updateGross(); // Recalculating Gross Mass
    }

    public LTS(double fuelMass, double cargoMass){ //Creates LTS unit with fuel and cargo mass parameter.
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
        this.ltsId = 100000 + rand.nextInt(900000); // For 6 digit Random ltsID
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
        if(manufacturer != null && !manufacturer.isEmpty()){ // Checks input
            this.manufacturer = manufacturer;
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
    }

    public void setCargoMass(double cargoMass){
        if (cargoMass < 0) {
            System.out.println("Error: Cargo mass is negative");
            return;
        }
        this.cargoMass = cargoMass;
        updateGross();
    }

    //  Method for increasedMissionTime. Will increase mission time up to 5 seconds if possible
    public void increaseMissionTime() {
        int secondsFlown = 0;
        for(int i = 0; i < 5; i++){
            if(fuelMass < burn){
                System.out.println("We ran out of fuel in the outer space"); // checker for fuel going empty if fuel mass-burn is negative
                break;
            }
            else {
                secondsFlown++;
                fuelMass -= burn;
            }
        }
        missionTime += secondsFlown; // Increase mission time by the actual number of seconds flown (up to 5)
        updateGross();
    }

    //  Method for deployCargo
    public void deployCargo(){
        if (missionTime < 200){ // checks if mission time is less than 200
            System.out.println("Error: Wait "+ (200 - missionTime) + " seconds."); //Invalid Check (Mission time under 200)
        } else {
            cargoMass = 0;  //  cargo mass changes to 0 as launch was deployed.
            updateGross();
            System.out.println("Cargo was deployed into space!");
        }
    }
}
