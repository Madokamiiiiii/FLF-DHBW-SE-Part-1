package airportfiretruck.extinguisher.task01;

public enum Configuration {
    instance;

    public final String userDirectory = System.getProperty("user.dir");
    public final String fileSeparator = System.getProperty("file.separator");
    public final String pathToReportJavaArchive = userDirectory + fileSeparator + "Mixer" + fileSeparator + "jar" + fileSeparator + "mixer.jar";

}
