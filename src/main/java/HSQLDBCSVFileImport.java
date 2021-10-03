import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class HSQLDBCSVFileImport {
    private Connection connection;

    /* PLEASE DO NOT MODIFY
    public static void main(String... args) {
        HSQLDBCSVFileImport hsqldbcsvFileImport = new HSQLDBCSVFileImport();
        hsqldbcsvFileImport.init();
        hsqldbcsvFileImport.importCSVFile(Configuration.instance.dataPath + "customers.csv");
        hsqldbcsvFileImport.shutdown();
    }*/

    public void startup() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String driverName = "jdbc:hsqldb:";
            String databaseURL = driverName + Configuration.instance.dataPath + "customer.db";
            String username = "sa";
            String password = "";
            connection = DriverManager.getConnection(databaseURL, username, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void update(String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            int result = statement.executeUpdate(sqlStatement);
            if (result == -1)
                System.out.println("error executing " + sqlStatement);
            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    public void dropTable() {
        System.out.println("--- dropTable");

        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("DROP TABLE data");
        System.out.println("sqlStringBuilder : " + sqlStringBuilder);

        update(sqlStringBuilder.toString());
    }

    public void createTable() {
        StringBuilder sqlStringBuilder = new StringBuilder();
        sqlStringBuilder.append("CREATE TABLE data ").append(" ( ");
        sqlStringBuilder.append("id BIGINT NOT NULL").append(",");
        sqlStringBuilder.append("town INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("type VARCHAR(1) NOT NULL").append(",");
        sqlStringBuilder.append("bonusLevel INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("hasSmartTechnology VARCHAR(5) NOT NULL").append(",");
        sqlStringBuilder.append("region VARCHAR(1) NOT NULL").append(",");
        sqlStringBuilder.append("energyConsumption0To6 INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("energyConsumption6To12 INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("energyConsumption12To18 INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("energyConsumption18To24 INTEGER NOT NULL").append(",");
        sqlStringBuilder.append("PRIMARY KEY (id)");
        sqlStringBuilder.append(" )");
        update(sqlStringBuilder.toString());
    }

    public void init() {
        startup();
        dropTable();
        createTable();
    }

    public String buildSQLStatement(long id, int town, String type, int bonusLevel, String hasSmartTechnology, String region,
                                    int energyConsumption0To6, int energyConsumption6To12,
                                    int energyConsumption12To18, int energyConsumption18To24) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO data (id,town,type,bonusLevel,hasSmartTechnology,region,");
        stringBuilder.append("energyConsumption0To6,energyConsumption6To12,energyConsumption12To18,energyConsumption18To24) VALUES (");
        stringBuilder.append(id).append(",");
        stringBuilder.append(town).append(",");
        stringBuilder.append("'").append(type).append("'").append(",");
        stringBuilder.append(bonusLevel).append(",");
        stringBuilder.append("'").append(hasSmartTechnology).append("'").append(",");
        stringBuilder.append("'").append(region).append("'").append(",");
        stringBuilder.append(energyConsumption0To6).append(",");
        stringBuilder.append(energyConsumption6To12).append(",");
        stringBuilder.append(energyConsumption12To18).append(",");
        stringBuilder.append(energyConsumption18To24);
        stringBuilder.append(")");
        //System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public void insert(int id, int town, String type, int bonusLevel, String hasSmartTechnology, String region,
                       int energyConsumption0To6, int energyConsumption6To12,
                       int energyConsumption12To18, int energyConsumption18To24) {
        update(buildSQLStatement(id, town, type, bonusLevel, hasSmartTechnology, region,
                energyConsumption0To6, energyConsumption6To12, energyConsumption12To18, energyConsumption18To24));
    }

    public void importCSVFile(String fileName) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split(";");
                insert(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]), strings[2], Integer.parseInt(strings[3]),
                        strings[4], strings[5], Integer.parseInt(strings[6]),
                        Integer.parseInt(strings[7]), Integer.parseInt(strings[8]), Integer.parseInt(strings[9]));
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void shutdown() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            connection.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }
}