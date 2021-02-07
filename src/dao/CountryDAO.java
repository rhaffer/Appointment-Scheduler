package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Country;
import util.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDAO {
    private final ObservableList<Country> countries = FXCollections.observableArrayList();

    public ObservableList<Country> getAll(Connection conn) throws SQLException {
        String selectAllStatement = "SELECT * FROM countries";
        DBQuery.setPreparedStatement(conn, selectAllStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.execute();
        ResultSet rs = statement.getResultSet();
        while (rs.next()) {
            int countryID = rs.getInt("Country_ID");
            String country = rs.getString("Country");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getTimestamp("Last_Update").toString();
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            countries.add(new Country(countryID, country, createDate, createdBy, lastUpdate, lastUpdatedBy));
        }
        return countries;
    }
}
