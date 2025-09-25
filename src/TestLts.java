
public class TestLts {
    public static void main(String[] args) {
        LTS unit1 = new LTS();
        LTS unit2;

        System.out.println("Testing unit1 (with no argument constructor) \n");

        //Testing manufacturer getter and setter
        System.out.printf("Manufacturer: %s \n", unit1.getManufacturer());
        System.out.println("Changing Manufacturer with empty string: ");
        unit1.setManufacturer("");
        System.out.println("Changing Manufacturer with valid string: 'Nasa' ");
        unit1.setManufacturer("Nasa \n");

        //Testing fuel mass getter and setter
        System.out.printf("Fuel Mass: %.2f \n",unit1.getFuelMass());
        System.out.println("Changing fuel mass with a negative value: -100");
        unit1.setFuelMass(-100);
        System.out.println("Changing fuel mass with a valid value: 100");
        unit1.setFuelMass(100);
        System.out.println();

        //Testing cargo mass getter and setter
        System.out.printf("Cargo Mass: %.2f \n", unit1.getCargoMass());
        System.out.println("Changing cargo mass with a negative value: -100");
        unit1.setCargoMass(-100);
        System.out.println("Changing cargo mass with a valid value: 100");
        unit1.setCargoMass(100);
        System.out.println();

        //Testing dry mass getter
        System.out.printf("Dry mass: %.2f \n\n", unit1.getDryMass());

        //Testing gross mass getter
        System.out.printf("Gross mass: %.2f \n\n", unit1.getGrossMass());

        //Testing ltsId getter
        System.out.printf("Unit 1 id: %d \n\n", unit1.getLtsID());

        //Testing DateManufactured getter
        System.out.printf("Date manufactured mm/dd/yy: %tD \n\n", unit1.getDateManufactured());

        //Testing mission time getter and increasing mission time until fuel is exhausted
        System.out.printf("Current mission time: %d \n", unit1.getMissionTime());
        for(int i = 0; i < 20; i++){
            unit1.increaseMissionTime();
        }
        System.out.printf("Current mission time: %d \n", unit1.getMissionTime() );
        unit1.deployCargo();
        for(int i = 0; i < 20; i++){
            unit1.increaseMissionTime();
        }
        System.out.printf("Current mission time: %d \n", unit1.getMissionTime());
        unit1.deployCargo();
        System.out.println();

        //Testing LTS creation with invalid values
        System.out.println("Creating LTS with 2 arguments constructor (invalid values)");
        unit2 = new LTS(-100,-100);
        System.out.printf("Unit 2 fuel mass: %.2f \n", unit2.getFuelMass());
        System.out.printf("Unit 2 cargo mass: %.2f \n", unit2.getCargoMass());

    }
}
