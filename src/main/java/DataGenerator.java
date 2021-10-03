import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class DataGenerator {
    private final ArrayList<String> customerTypeList;
    private final ArrayList<String> regionList;
    private final ArrayList<Town> townList;
    private final ArrayList<Customer> customerList;

    public DataGenerator() {
        customerTypeList = new ArrayList<>();
        regionList = new ArrayList<>();
        townList = new ArrayList<>();
        customerList = new ArrayList<>();
    }

    /* PLEASE DO NOT MODIFY
    public static void main(String... args) {
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.initCustomerTypeList();
        dataGenerator.initRegionList();
        dataGenerator.generateTownList();
        dataGenerator.generateCustomerList();
        dataGenerator.generateToCSVFile();
    } */

    public void initCustomerTypeList() {
        customerTypeList.add("S");
        customerTypeList.add("K");
        customerTypeList.add("L");
        customerTypeList.add("M");
    }

    public void initRegionList() {
        regionList.add("A");
        regionList.add("B");
        regionList.add("C");
        regionList.add("D");
        regionList.add("E");
        regionList.add("F");
        regionList.add("G");
    }

    public void generateTownList() {
        for (int i = 0; i < Configuration.instance.maximumNumberOfTowns; i++) {
            int randomRegionIndex = Configuration.instance.randomNumberGenerator.nextInt(0, regionList.size() - 1);
            townList.add(new Town(i + 1, regionList.get(randomRegionIndex)));
        }
    }

    public void generateCustomerList() {
        for (int i = 0; i < Configuration.instance.maximumNumberOfCustomers; i++) {
            int randomTownIndex = Configuration.instance.randomNumberGenerator.nextInt(0, Configuration.instance.maximumNumberOfTowns - 1);
            int randomCustomerTypeIndex = Configuration.instance.randomNumberGenerator.nextInt(0, customerTypeList.size() - 1);

            int minimumEnergyConsumption;
            int maximumEnergyConsumption;

            if (randomCustomerTypeIndex == 1) {
                minimumEnergyConsumption = 5;
                maximumEnergyConsumption = 100;
            } else {
                minimumEnergyConsumption = 25;
                maximumEnergyConsumption = 250;
            }

            boolean hasSmartTechnology = Configuration.instance.randomNumberGenerator.nextBoolean(0.25);
            int randomBonusLevel = Configuration.instance.randomNumberGenerator.nextInt(1, 3);
            int randomEnergyConsumption0To6 = Configuration.instance.randomNumberGenerator.nextInt(minimumEnergyConsumption, maximumEnergyConsumption);
            int randomEnergyConsumption6To12 = Configuration.instance.randomNumberGenerator.nextInt(minimumEnergyConsumption, maximumEnergyConsumption);
            int randomEnergyConsumption12To18 = Configuration.instance.randomNumberGenerator.nextInt(minimumEnergyConsumption, maximumEnergyConsumption);
            int randomEnergyConsumption18To24 = Configuration.instance.randomNumberGenerator.nextInt(minimumEnergyConsumption, maximumEnergyConsumption);

            Customer customer = new Customer(i + 1,
                    townList.get(randomTownIndex),
                    randomBonusLevel,
                    hasSmartTechnology,
                    customerTypeList.get(randomCustomerTypeIndex),
                    randomEnergyConsumption0To6, randomEnergyConsumption6To12,
                    randomEnergyConsumption12To18, randomEnergyConsumption18To24);
            customerList.add(customer);
        }
    }

    public void generateToCSVFile() {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Configuration.instance.dataPath + "customerList.csv"));

            for (int i = 0; i < Configuration.instance.maximumNumberOfCustomers; i++)
                bufferedWriter.write(customerList.get(i).toString() + Configuration.instance.lineSeparator);

            bufferedWriter.close();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}