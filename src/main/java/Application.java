import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Application implements IApplication {
    private final List<Customer> customerList;

    public Application() {
        customerList = new ArrayList<>();
    }

    public static void main(String... args) {
        Application application = new Application();
        application.loadRecords();

        application.executeQuery01();
        application.executeQuery02();
        application.executeQuery03();
        application.executeQuery04();
        application.executeQuery05();
        application.executeQuery06();
        application.executeQuery07();
        application.executeQuery08();
        application.executeQuery09();
        application.executeQuery10();
        application.executeQuery11();
        application.executeQuery12();
        application.executeQuery13();
        application.executeQuery14();
    }

    public void loadRecords() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(Configuration.instance.dataPath + "customers.csv"));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] entries = line.split(";");
                int id = Integer.parseInt(entries[0]);
                int townID = Integer.parseInt(entries[1]);
                String region = entries[5];
                Town town = new Town(townID, region);
                int bonusLevel = Integer.parseInt(entries[3]);
                boolean hasSmartTechnology = Boolean.parseBoolean(entries[4]);
                String type = entries[2];
                int energyConsumption0To6 = Integer.parseInt(entries[6]);
                int energyConsumption6To12 = Integer.parseInt(entries[7]);
                int energyConsumption12To18 = Integer.parseInt(entries[8]);
                int energyConsumption18To24 = Integer.parseInt(entries[9]);
                customerList.add(new Customer(id, town, bonusLevel, hasSmartTechnology, type, energyConsumption0To6, energyConsumption6To12, energyConsumption12To18, energyConsumption18To24));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void executeQuery01() {
        System.out.println("--- executeQuery01 ---");
        System.out.println(customerList.size());
        System.out.println();
    }

    public void executeQuery02() {
        System.out.println("--- executeQuery02 ---");
        System.out.println(customerList.stream()
                .filter(c -> c.getTown().getRegion().equals("A"))
                .filter(c -> c.getType().equals("S"))
                .count());
    }

    public void executeQuery03() {
        System.out.println("--- executeQuery03 ---");
        System.out.println(customerList.stream()
                .filter(c -> c.getTown().getRegion().equals("A"))
                .filter(c -> c.getType().equals("S")
                        || c.getType().equals("L"))
                .filter(c -> c.getEnergyConsumption0To6() >= 25
                        && c.getEnergyConsumption0To6() <= 50)
                .count());
    }

    public void executeQuery04() {
        System.out.println("--- executeQuery04 ---");
        System.out.println();
    }

    public void executeQuery05() {
        System.out.println("--- executeQuery05 ---");
        System.out.println();
    }

    public void executeQuery06() {
        System.out.println("--- executeQuery06 ---");
        System.out.println(customerList.stream()
                .filter(c -> c.getTown().getRegion().equals("A"))
                .filter(c -> !c.getType().equals("K") || !c.getType().equals("L"))
                .filter(c -> c.getBonusLevel() >= 2)
                .filter(c -> !c.isHasSmartTechnology())
                .collect(Collectors.averagingInt(Customer::getEnergyConsumption6To12))
        );
    }

    public void executeQuery07() {
        System.out.println("--- executeQuery07 ---");
        System.out.println();
    }

    public void executeQuery08() {
        System.out.println("--- executeQuery08 ---");
        System.out.println(customerList.stream()
                .filter(c -> c.getTown().getRegion().equals("C"))
                .filter(c -> c.getType().equals("K") || c.getType().equals("L"))
                .filter(c -> c.getBonusLevel() <= 2)
                .filter(Customer::isHasSmartTechnology)
                .filter(c -> c.getEnergyConsumption0To6() <= 5)
                .filter(c -> c.getEnergyConsumption6To12() >= 10
                        && c.getEnergyConsumption6To12() <= 15)
                .sorted(Comparator.comparing(Customer::getBonusLevel).reversed())
                .sorted(Comparator.comparingInt(Customer::getEnergyConsumption6To12))
                .map(Customer::getId)
        );
    }

    public void executeQuery09() {
        System.out.println("--- executeQuery09 ---");
        System.out.println();
    }

    public void executeQuery10() {
        System.out.println("--- executeQuery10 ---");
        System.out.println(customerList.stream()
                .filter(customer -> customer.getEnergyConsumption0To6() <= 50)
                .collect(Collectors.groupingBy(customer -> customer.getTown().getRegion(), Collectors.counting())));
    }

    public void executeQuery11() {
        System.out.println("--- executeQuery11 ---");
        System.out.println(customerList.stream()
                .filter(c -> c.getType().equals("L") || c.getType().equals("M"))
                .collect(Collectors.groupingBy(Customer::getBonusLevel, Collectors.counting())));
    }

    public void executeQuery12() {
        System.out.println("--- executeQuery12 ---");
        System.out.println();
    }

    public void executeQuery13() {
        System.out.println("--- executeQuery13 ---");
        System.out.println();
    }

    public void executeQuery14() {
        System.out.println("--- executeQuery14 ---");
        System.out.println(customerList.stream()
                .filter(c -> c.getTown().getRegion().equals("C") || c.getTown().getRegion().equals("A"))
                .filter(c -> c.getType().equals("L") || c.getType().equals("M"))
                .filter(c -> !c.isHasSmartTechnology())
                .collect(Collectors.groupingBy(customer -> customer.getTown().getRegion(), Collectors.averagingInt(Customer::getEnergyConsumption6To12))));
    }
}