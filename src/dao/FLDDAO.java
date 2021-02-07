package dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivision;
import util.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FLDDAO {
    private final ObservableList<FirstLevelDivision> first_level_divisions = FXCollections.observableArrayList();

    public ObservableList<FirstLevelDivision> getAllByCountryID(Connection conn, int country_ID) throws SQLException {
        String selectAllStatement = "SELECT * FROM first_level_divisions WHERE COUNTRY_ID = ?";
        DBQuery.setPreparedStatement(conn, selectAllStatement);
        PreparedStatement statement = DBQuery.getPreparedStatement();
        statement.setInt(1, country_ID);
        statement.execute();
        ResultSet rs = statement.getResultSet();
        while (rs.next()) {
            int divisionID = rs.getInt("Division_ID");
            String division = rs.getString("Division");
            String createDate = rs.getString("Create_Date");
            String createdBy = rs.getString("Created_By");
            String lastUpdate = rs.getTimestamp("Last_Update").toString();
            String lastUpdatedBy = rs.getString("Last_Updated_By");
            int countryID = rs.getInt("Country_ID");
            first_level_divisions.add(new FirstLevelDivision(divisionID, division, createDate, createdBy, lastUpdate,
                    lastUpdatedBy, countryID));
        }
        return first_level_divisions;
    }
}
