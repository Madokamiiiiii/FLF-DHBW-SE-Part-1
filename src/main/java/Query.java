import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Query implements IQuery {
    private Connection connection;
    private BufferedWriter bufferedWriter;

    public static void main(String... args) {
        Query query = new Query();
        query.startup();

        query.executeSQL01();
        query.executeSQL02();
        query.executeSQL03();
        query.executeSQL04();
        query.executeSQL05();
        query.executeSQL06();
        query.executeSQL07();
        query.executeSQL08();
        query.executeSQL09();
        query.executeSQL10();
        query.executeSQL11();
        query.executeSQL12();
        query.executeSQL13();
        query.executeSQL14();

        query.shutdown();
    }

    public void startup() {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            String driverName = "jdbc:hsqldb:";
            String databaseURL = driverName + Configuration.instance.dataPath + "customer.db";
            String username = "sa";
            String password = "";
            connection = DriverManager.getConnection(databaseURL, username, password);

            bufferedWriter = new BufferedWriter(new FileWriter(Configuration.instance.logPath + "query.log"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void writeLogfile(String message) {
        try {
            bufferedWriter.append(message).append("\n");
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public String dump(ResultSet resultSet) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int maximumNumberColumns = resultSetMetaData.getColumnCount();
            int i;
            Object object;

            while (resultSet.next()) {
                for (i = 0; i < maximumNumberColumns; ++i) {
                    object = resultSet.getObject(i + 1);
                    stringBuilder.append(object.toString()).append(" ");
                }
                stringBuilder.append(" \n");
            }

            return stringBuilder.toString();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }

        return "-1";
    }

    // count
    // count, where
    // count, where, in
    // count, where, not in
    // sum, where, in
    // avg, where, not in
    // id, where, in, order by desc limit
    // id, where, in, order by desc, order by asc
    // count, group by
    // count, where, group by
    // count, where, in, group by
    // count, where, not in, group by
    // sum, where, not in, in, group by
    // avg, where, in, in, group by

    public synchronized void queryDump(String sqlStatement) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            writeLogfile(sqlStatement);
            writeLogfile(dump(resultSet));
            statement.close();
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
        }
    }

    // count
    public void executeSQL01() {
        writeLogfile("--- query 01 (count)");
        String sqlStatement = "SELECT COUNT(*) FROM data";
        queryDump(sqlStatement);
    }

    // count, where
    public void executeSQL02() {
        writeLogfile("--- query 02 (count, where)");
        String sqlStatement = "SELECT COUNT(*) FROM data " +
                "WHERE region = 'A' AND type = 'S'";
        queryDump(sqlStatement);
    }

    // count, where, in
    public void executeSQL03() {
        writeLogfile("--- query 03 (count, where, in)");
        String sqlStatement = "SELECT COUNT(*) FROM data " +
                "WHERE region = 'A' " +
                "AND type IN ('S','L') " +
                "AND energyConsumption0To6 >= 25 AND energyConsumption0To6 <= 50";
        queryDump(sqlStatement);
    }

    // count, where, not in
    public void executeSQL04() {
        writeLogfile("--- query 04 (count, where, not in)");
        String sqlStatement = "SELECT COUNT(*) FROM data " +
                "WHERE type NOT IN ('L','M') AND region = 'B' AND bonusLevel >= 2 " +
                "AND hasSmartTechnology = 'true' AND energyConsumption12To18 <= 25";
        queryDump(sqlStatement);
    }

    // sum, where, in
    public void executeSQL05() {
        writeLogfile("--- query 05 (sum, where, in)");
        String sqlStatement = "SELECT SUM(energyConsumption6To12) FROM data " +
                "WHERE region = 'C' " +
                "AND type IN ('S','K') " +
                "AND bonusLevel <= 2 " +
                "AND hasSmartTechnology = 'true' ";
        queryDump(sqlStatement);
    }

    // avg, where, not in
    public void executeSQL06() {
        writeLogfile("--- query 06 (avg, where, not in)");
        String sqlStatement = "SELECT AVG(energyConsumption6To12) FROM data " +
                "WHERE region = 'A' " +
                "AND type NOT IN ('K','L') " +
                "AND bonusLevel >= 2 " +
                "AND hasSmartTechnology = 'false' ";
        queryDump(sqlStatement);
    }

    // id, where, in, order by desc limit
    public void executeSQL07() {
        writeLogfile("--- query 07 (id, where, in, order by desc limit)");
        String sqlStatement = "SELECT id FROM data " +
                "WHERE region = 'A' " +
                "AND type IN ('S','L') " +
                "AND energyConsumption0To6 >= 25 AND energyConsumption0To6 <= 30 " +
                "AND id <= 1000 " +
                "ORDER BY energyConsumption0To6 DESC LIMIT 3";
        queryDump(sqlStatement);
    }

    // id, where, in, order by desc, order by asc
    public void executeSQL08() {
        writeLogfile("--- query 08 (id, where, in, order by desc, order by asc)");
        String sqlStatement = "SELECT id FROM data " +
                "WHERE region = 'C' " +
                "AND type IN ('K','L') " +
                "AND bonusLevel <= 2 " +
                "AND hasSmartTechnology = 'true' " +
                "AND energyConsumption0To6 <= 5 " +
                "AND energyConsumption6To12 >= 10 AND energyConsumption6To12 <= 15 " +
                "ORDER BY bonusLevel DESC,energyConsumption6To12";
        queryDump(sqlStatement);
    }

    // count, group by
    public void executeSQL09() {
        writeLogfile("--- query 09 (count, group by)");
        String sqlStatement = "SELECT hasSmartTechnology,COUNT(*) FROM data " +
                "GROUP BY hasSmartTechnology";
        queryDump(sqlStatement);
    }

    // count, where, group by
    public void executeSQL10() {
        writeLogfile("--- query 10 (count, where, group by)");
        String sqlStatement = "SELECT region,COUNT(*) FROM data " +
                "WHERE energyConsumption0To6 <= 50 " +
                "GROUP BY region";
        queryDump(sqlStatement);
    }

    // count, where, in, group by
    public void executeSQL11() {
        writeLogfile("--- query 11 (count, where, in, group by)");
        String sqlStatement = "SELECT bonusLevel,COUNT(*) FROM data " +
                "WHERE TYPE IN ('L','M') " +
                "GROUP BY bonusLevel";
        queryDump(sqlStatement);
    }

    // count, where, not in, group by
    public void executeSQL12() {
        writeLogfile("--- query 12 (count, where, not in, group by)");
        String sqlStatement = "SELECT region,COUNT(*) FROM data " +
                "WHERE region NOT IN ('A','B') AND hasSmartTechnology = 'true' " +
                "GROUP BY region";
        queryDump(sqlStatement);
    }

    // sum, where, not in, in, group by
    public void executeSQL13() {
        writeLogfile("--- query 13 (sum, where, not in, in, group by)");
        String sqlStatement = "SELECT hasSmartTechnology,SUM(energyConsumption6To12) FROM data " +
                "WHERE region NOT IN ('B','C') AND bonusLevel IN (1,3) AND type = 'M' " +
                "GROUP BY hasSmartTechnology";
        queryDump(sqlStatement);
    }

    // avg, where, in, in, group by
    public void executeSQL14() {
        writeLogfile("--- query 14 (avg, where, in, in, group by)");
        String sqlStatement = "SELECT region,AVG(energyConsumption6To12) FROM data " +
                "WHERE region IN ('A','C') AND type IN ('L','M') AND hasSmartTechnology = 'false' " +
                "GROUP BY region";
        queryDump(sqlStatement);
    }

    public void shutdown() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SHUTDOWN");
            connection.close();
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}