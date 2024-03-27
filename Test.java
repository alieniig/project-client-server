import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        String url = "jdbc:mysql://11f.h.filess.io:3307/databaseclientserver_spiderboy";
        String username = "databaseclientserver_spiderboy";
        String password = "8810f990559345eb9ee801ec5ead1caafd4c0c5e";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println("Verbindung erfolgreich hergestellt!");

            Nutzer neuerNutzer = new Nutzer("adrian", "muc");

            if (isUsernameTaken(conn, neuerNutzer.getUsername())) {
                System.out.println("Der Benutzername ist bereits vergeben.");
            } else {
                insertValues(conn, neuerNutzer.getUsername(), neuerNutzer.getPassword());
            }

        } catch (SQLException e) {
            System.err.println("Fehler beim Verbinden mit der Datenbank: " + e.getMessage());
        }
    }

    public static void insertValues(Connection connection, String username, String password) throws SQLException {
        String insertSql = "INSERT INTO User (username, password) VALUES (?, ?)";
        PreparedStatement insertStmt = connection.prepareStatement(insertSql);
        insertStmt.setString(1, username); // Modell setzen
        insertStmt.setString(2, password); // Hersteller setzen

        int rowsInserted = insertStmt.executeUpdate();
        System.out.println(rowsInserted + " Zeilen eingefügt.");

    }

    public static boolean isUsernameTaken(Connection connection, String username) throws SQLException {
        String checkSql = "SELECT * FROM User WHERE username = ?";
        PreparedStatement checkStmt = connection.prepareStatement(checkSql);
        checkStmt.setString(1, username);

        ResultSet resultSet = checkStmt.executeQuery();

        return resultSet.next();
    }

}