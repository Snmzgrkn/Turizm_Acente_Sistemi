package dao;

import core.Db;
import entity.Feature;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {
    private final Connection connection;

    public SeasonDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Season> findAll() {
        ArrayList<Season> seasonList = new ArrayList<>();
        String query = "SELECT * FROM season ORDER BY season_id ASC";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                seasonList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return seasonList;
    }

    public Season findById(int id) {
        Season season = null;
        String query = "SELECT * FROM season WHERE season_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                season = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return season;
    }

    public boolean save(Season season){
        String query = "INSERT INTO public.season (season_name,season_strt_Date,season_fnsh_date,season_rate_multiplier) VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1,season.getName());
            pr.setDate(2, Date.valueOf(season.getStartDate()));
            pr.setDate(3,Date.valueOf(season.getEndDate()));
            pr.setDouble(4,season.getRateMultiplier());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public Season match(ResultSet rs) throws SQLException {
        Season season = new Season();
        season.setId(rs.getInt("season_id"));
        season.setName(rs.getString("season_name"));
        season.setStartDate(LocalDate.parse(rs.getString("season_strt_date")));
        season.setEndDate(LocalDate.parse(rs.getString("season_fnsh_Date")));
        season.setRateMultiplier(rs.getDouble("season_rate_multiplier"));
        return season;
    }
}
