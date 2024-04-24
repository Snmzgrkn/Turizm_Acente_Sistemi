package dao;

import core.Db;
import entity.Pension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {
    private final Connection connection;

    public PensionDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Pension> findAll() {
        ArrayList<Pension> pensionList = new ArrayList<>();
        String query = "SELECT * FROM pension ORDER BY pensiontype_id ASC";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                pensionList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionList;
    }

    public Pension findById(int id) {
        Pension pension = null;
        String query = "SELECT * FROM pension WHERE pensiontype_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                pension = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pension;
    }
    public boolean save(Pension pension){
        String query = "INSERT INTO public.pension (pensiontype_name) VALUES (?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1,pension.getName());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Pension pension){
        String query = "UPDATE public.pension SET pensiontype_name = ? WHERE pensiontype_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1,pension.getName());
            pr.setInt(2,pension.getId());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.pension WHERE pensiontype_id = ?";
        try{
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public Pension match(ResultSet rs) throws SQLException {
        Pension pension = new Pension();
        pension.setId(rs.getInt("pensiontype_id"));
        pension.setName(rs.getString("pensiontype_name"));
        return pension;
    }
}