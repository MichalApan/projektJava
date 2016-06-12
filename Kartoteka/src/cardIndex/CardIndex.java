package cardIndex;

import model.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.Callable;
import oracle.jdbc.driver.OracleDriver;

/**
 *
 * @author Michał
 */
public class CardIndex implements Callable {

    public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:ORA2015";
    private Connection conn;
    private Statement stat;

    /**
     * Construktor of CardIndes class
     *
     * @param login
     * @param passwd
     */
    public CardIndex(String login, String passwd) {
        try {
            DriverManager.registerDriver(new OracleDriver());
        } catch (Exception e) {
            System.err.println("Brak sterownika JDBC");
        }

        try {
            conn = DriverManager.getConnection(DB_URL, login, passwd);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
        }
    }

    /**
     * Updates value in DataBase
     *
     * @param pesel
     * @param table
     * @param column
     * @param id
     * @param data
     * @return
     */
    public boolean update(int pesel, String table, String column, String id, String data) {
        try {
            String query = "UPDATE " + table + " SET " + column + " = ? WHERE " + id + " =  ?";
            PreparedStatement prepStmt = conn.prepareStatement(query);
            prepStmt.setString(1, data);
            prepStmt.setInt(2, pesel);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Bład przy edytowaniu harcerza");
            return false;
        }
        return true;
    }

    /**
     * Inserts values into Scouts table
     *
     * @param pesel
     * @param name
     * @param surname
     * @param scoutRankId
     * @return true or false
     */
    public boolean insertScout(int pesel, String name, String surname, int scoutRankId) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO Scouts VALUES (?, ?, ?, ?)");
            prepStmt.setInt(1, pesel);
            prepStmt.setString(2, name);
            prepStmt.setString(3, surname);
            prepStmt.setInt(4, scoutRankId);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy dodawaniu harcerza");

            return false;
        }
        return true;
    }

    /**
     * Deleste values from Scouts table
     *
     * @param pesel
     * @return true or false
     */
    public boolean deleteScout(int pesel) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "DELETE FROM Scouts WHERE pesel = ?");
            prepStmt.setInt(1, pesel);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Blad przy usuwaniu harcerza");
            return false;
        }
        return true;
    }

    /**
     * Sends query to DataBase
     *
     * @return List of Scouta
     */
    public List<Scout> selectScouts() {
        List<Scout> l = new LinkedList<>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM Scouts ORDER BY surname");
            int pesel, scoutRankId;
            String name, surname;
            while (result.next()) {
                pesel = result.getInt("pesel");
                name = result.getString("name");
                surname = result.getString("surname");
                scoutRankId = result.getInt("scoutRankId");

                l.add(new Scout(pesel, name, surname, scoutRankId));
            }
        } catch (SQLException e) {
            return null;
        }
        return l;
    }

    /**
     * Inserts values into Badges table
     *
     * @param id
     * @param title
     * @param tasks
     * @return true or false
     */
    public boolean insertBadge(int id, String title, String tasks) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO Badges VALUES (?, ?, ?)");
            prepStmt.setInt(1, id);
            prepStmt.setString(2, title);
            prepStmt.setString(3, tasks);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy dodawaniu sprawności");
            return false;
        }
        return true;
    }

    /**
     * Deletes values from Badges table
     *
     * @param id
     * @return true or false
     */
    public boolean deleteBadge(int id) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "DELETE FROM Badges WHERE badgeId = ?");
            prepStmt.setInt(1, id);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Blad przy usuwaniu sprawności");
            return false;
        }
        return true;
    }

    /**
     *
     * @return List of Badges
     */
    public List<Badge> selectBadges() {
        List<Badge> l = new LinkedList<>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM Badges ORDER BY badgeId");
            int id;
            String title, tasks;
            while (result.next()) {
                id = result.getInt("badgeId");
                title = result.getString("title");
                tasks = result.getString("tasks");

                l.add(new Badge(id, title, tasks));
            }
        } catch (SQLException e) {
            return null;
        }
        return l;
    }

    /**
     * Inserts values into ScoutRanks table
     *
     * @param id
     * @param title
     * @return true or false
     */
    public boolean insertScoutRank(int id, String title) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO ScoutRanks VALUES (?, ?)");
            prepStmt.setInt(1, id);
            prepStmt.setString(2, title);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy dodawaniu stopnia");
            return false;
        }
        return true;
    }

    /**
     * Deletes value from ScoutRanks table
     *
     * @param id
     * @return true or false
     */
    public boolean deleteScoutRank(int id) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "DELETE FROM ScoutRanks WHERE scoutRankId = ?");
            prepStmt.setInt(1, id);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Blad przy usuwaniu stopnia");
            return false;
        }
        return true;
    }

    /**
     * Selecting values from ScoutRanks table
     *
     * @return List of ScoutRank
     */
    public List<ScoutRank> selectScoutRank() {
        List<ScoutRank> l = new LinkedList<>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM ScoutRanks ORDER BY scoutRankId");
            int id;
            String title;
            while (result.next()) {
                id = result.getInt("scoutRankId");
                title = result.getString("title");
                l.add(new ScoutRank(id, title));
            }
        } catch (SQLException e) {
            return null;
        }
        return l;
    }

    /**
     * Inserts value into GainedRanks table
     *
     * @param pesel
     * @param scoutRankId
     * @param status
     * @param supervisor
     * @param commandDate
     * @param commandNumber
     * @return true or false
     */
    public boolean insertGainedRank(int pesel, int scoutRankId, int status,
            String supervisor, String commandDate, String commandNumber) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO GainedRank VALUES (?, ?, ?, ?, TO_DATE(?,'dd/mm/yyyy'), ?)");
            prepStmt.setInt(1, pesel);
            prepStmt.setInt(2, scoutRankId);
            prepStmt.setInt(3, status);
            prepStmt.setString(4, supervisor);
            prepStmt.setString(5, commandDate);
            prepStmt.setString(6, commandNumber);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy dodawaniu zdobytego stopnia");
            return false;
        }
        return true;
    }

    /**
     * Deletes value from GainedRanks table
     *
     * @param pesel
     * @param scoutRankId
     * @return
     */
    public boolean deleteGainedRank(int pesel, int scoutRankId) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "DELETE FROM GainedRank WHERE pesel = ? AND scoutRankId = ?");
            prepStmt.setInt(1, pesel);
            prepStmt.setInt(2, scoutRankId);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Blad przy usuwaniu zdobytego stopnia");
            return false;
        }
        return true;
    }

    /**
     * Selects values from GainedRanks table
     *
     * @return List of GainedRank
     */
    public List<GainedRank> selectGainedRank() {
        List<GainedRank> l = new LinkedList<>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM GainedRank");
            int pesel, scoutRankId, status;
            String supervisor, commandDate, commandNumber;
            while (result.next()) {
                pesel = result.getInt("pesel");
                scoutRankId = result.getInt("scoutRankId");
                status = result.getInt("status");
                supervisor = result.getString("supervisor");
                commandDate = result.getString("commandDate");
                commandNumber = result.getString("commandNumber");
                l.add(new GainedRank(pesel, scoutRankId, supervisor, commandDate,
                        commandNumber, status));
            }
        } catch (SQLException e) {
            return null;
        }
        return l;
    }

    /**
     * Inserts value int GainedBadge table
     *
     * @param pesel
     * @param badgeId
     * @param commandNumber
     * @param commandDate
     * @return true or false
     */
    public boolean insertGainedBadge(int pesel, int badgeId, String commandNumber,
            String commandDate) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "INSERT INTO GainedBadge VALUES (?, ?, ?, TO_DATE(?,'dd/mm/yyyy'))");
            prepStmt.setInt(1, pesel);
            prepStmt.setInt(2, badgeId);
            prepStmt.setString(3, commandNumber);
            prepStmt.setString(4, commandDate);
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy dodawaniu zdobytej sprawności");
            return false;
        }
        return true;
    }

    /**
     * Deletes value from GainedBadge table
     *
     * @param pesel
     * @param badgeId
     * @return true or false
     */
    public boolean deleteGainedBadge(int pesel, int badgeId) {
        try {
            PreparedStatement prepStmt = conn.prepareStatement(
                    "DELETE FROM GainedBadge WHERE pesel = ? AND badgeId = ?");
            prepStmt.setInt(1, pesel);
            prepStmt.setInt(2, badgeId);
            prepStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Blad przy usuwaniu zdobytej sprawności");
            return false;
        }
        return true;
    }

    /**
     * Selects values from GainedBadge table
     *
     * @return List of GainedBadge
     */
    public List<GainedBadge> selectGainedBadge() {
        List<GainedBadge> l = new LinkedList<>();
        try {
            ResultSet result = stat.executeQuery("SELECT * FROM GainedBadge");
            int pesel, badgeId;
            String commandNumber, commandDate;
            while (result.next()) {
                pesel = result.getInt("pesel");
                badgeId = result.getInt("badgeId");
                commandDate = result.getString("commandDate");
                commandNumber = result.getString("commandNumber");
                l.add(new GainedBadge(pesel, badgeId, commandNumber, commandDate));
            }
        } catch (SQLException e) {
            return null;
        }
        return l;
    }

    /**
     * Closing connection with DataBase
     */
    public void closeConnection() {
        try {
            conn.close();
            System.out.println("Zamknięto poprawnie");
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
        }
    }

    /**
     * Selects scouts in new Thread
     *
     * @return List of Scout
     * @throws Exception
     */
    @Override
    public List<Scout> call() throws Exception {
        return selectScouts();
    }

}
