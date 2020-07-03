package resources.data;

import com.sun.glass.ui.Window;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class dataCenter {
    private final String url = "jdbc:postgresql://localhost:5432/photos";
    private final String user = "postgres";
    private final String password = "NorthBrunswick1685!";          // Remember to delete this when uploading to github

    // Method to connect to database

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // creates the initial empty album in the database

    private void createAlbumTable(String libraryUser, String albumName) {
        String sql = String.format("create table %s.%s (id bigserial not null primary key, " +
                "pictures varchar(50) unique, caption varchar(50), tag varchar(50))", libraryUser, albumName);

        try (Connection conn = connect(); PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Creates a schema in the database for each user

    public void createNewUser(String libraryUser) {
        String sql = String.format("create schema %s", libraryUser);

        try (Connection conn = connect(); PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public ObservableList<String> listAlbums(String libraryUser) {
        String sql = String.format("select * from information_schema.tables where table_schema = '%s'", libraryUser);
        ObservableList<String> observableAlbums = FXCollections.observableArrayList();

        try (Connection conn = connect()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();


            while (rs.next()) {
                observableAlbums.add(rs.getString(3));
                //System.out.println(rs.getString(3));
            } return observableAlbums;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } return null;
    }
}
