package resources.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;

public class dataCenter {
    private final String url = "jdbc:postgresql://localhost:5432/photos";
    private final String user = "postgres";
    private final String password = "NorthBrunswick1685!";          // Remember to delete this when uploading to github

    private File file = new File("src/resources/data/currentUser.txt");     // contains name of the user that is signed in

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

    public void createAlbumTable(String libraryUser, String albumName) {
        String sql = String.format("create table %s.%s (id bigserial not null primary key, " +
                "pictures varchar(50) unique, caption varchar(50), tag varchar(50))", libraryUser, albumName);

        try (Connection conn = connect(); PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Edits album name

    public void editAlbum(String libraryUser, String oldName, String newName) {
        String sql = String.format("alter table %s.%s rename to %s", libraryUser, oldName, newName);

        try (Connection conn = connect(); PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Deletes album

    public void deleteAlbum(String libraryUser, String albumName) {
        String sql = String.format("drop table %s.%s", libraryUser, albumName);

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

    // Deletes the user's schema in the database

    public void deleteUser(String libraryUser) {
        String sql = String.format("drop schema %s", libraryUser);

        try (Connection conn = connect(); PreparedStatement psmt = conn.prepareStatement(sql)) {
            psmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Generates list of albums for the user on the main dashboard

    public ObservableList<String> listAlbums(String libraryUser) {
        String sql = String.format("select * from information_schema.tables where table_schema = '%s'", libraryUser);
        ObservableList<String> observableAlbums = FXCollections.observableArrayList();

        try (Connection conn = connect()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                observableAlbums.add(rs.getString(3));
            } return observableAlbums;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Generates list of users in the admin dashboard

    public ObservableList<String> listUsers() {
        String sql = "select s.nspname as table_schema, s.oid as schema_id, u.usename as owner from pg_catalog.pg_namespace s join pg_catalog.pg_user u on " +
                "u.usesysid = s.nspowner where nspname not in ('information_schema', 'pg_catalog', 'public') and nspname not like 'pg_toast%' and nspname not " +
                "like 'pg_temp_%' order by table_schema";

        ObservableList<String> observableAlbums = FXCollections.observableArrayList();

        try (Connection conn = connect()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                observableAlbums.add(rs.getString(1));
            } return observableAlbums;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Returns the user that is signed in

    public String getUser() throws FileNotFoundException {
        Scanner scan = new Scanner(file);
        return scan.nextLine();
    }

    // Clears text file data

    public void clear() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        writer.close();
    }

    // Returns the user that the admin wants to remove

    public String getUserToDelete() throws FileNotFoundException {
        Scanner scan = new Scanner(new File("src/resources/data/userToDelete.txt"));
        return scan.nextLine();
    }
}
