public enum Configuration {
    instance;

    public MersenneTwister randomNumberGenerator = new MersenneTwister(System.currentTimeMillis());

    public String fileSeparator = System.getProperty("file.separator");
    public String lineSeparator = System.getProperty("line.separator");

    public String dataPath = "data" + fileSeparator;
    public String logPath = "log" + fileSeparator;

    public int maximumNumberOfTowns = 5000;
    public int maximumNumberOfCustomers = 1000000;
}