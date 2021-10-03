public class Customer {
    private final int id;
    private final Town town;
    private final int bonusLevel;
    private final boolean hasSmartTechnology;
    private final String type;
    private final int energyConsumption0To6;
    private final int energyConsumption6To12;
    private final int energyConsumption12To18;
    private final int energyConsumption18To24;

    public Customer(int id, Town town, int bonusLevel, boolean hasSmartTechnology, String type,
                    int energyConsumption0To6, int energyConsumption6To12,
                    int energyConsumption12To18, int energyConsumption18To24) {
        this.id = id;
        this.town = town;
        this.bonusLevel = bonusLevel;
        this.hasSmartTechnology = hasSmartTechnology;
        this.type = type;
        this.energyConsumption0To6 = energyConsumption0To6;
        this.energyConsumption6To12 = energyConsumption6To12;
        this.energyConsumption12To18 = energyConsumption12To18;
        this.energyConsumption18To24 = energyConsumption18To24;
    }

    public int getId() {
        return id;
    }

    public Town getTown() {
        return town;
    }

    public int getBonusLevel() {
        return bonusLevel;
    }

    public boolean isHasSmartTechnology() {
        return hasSmartTechnology;
    }

    public String getType() {
        return type;
    }

    public int getEnergyConsumption0To6() {
        return energyConsumption0To6;
    }

    public int getEnergyConsumption6To12() {
        return energyConsumption6To12;
    }

    public int getEnergyConsumption12To18() {
        return energyConsumption12To18;
    }

    public int getEnergyConsumption18To24() {
        return energyConsumption18To24;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(id).append(";").append(town.getId()).append(";").append(town.getRegion()).append(";");
        stringBuilder.append(bonusLevel).append(";").append(hasSmartTechnology).append(";").append(type).append(";").append(";");
        stringBuilder.append(energyConsumption0To6).append(";").append(energyConsumption6To12).append(";");
        stringBuilder.append(energyConsumption12To18).append(";").append(energyConsumption18To24);
        return stringBuilder.toString();
    }
}