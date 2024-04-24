package dao;

import core.Db;
import entity.Feature;
import entity.Pension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeatureDao {
    private final Connection connection;

    public FeatureDao() {
        this.connection = Db.getInstance();
    }

    public ArrayList<Feature> findAll() {
        ArrayList<Feature> featureList = new ArrayList<>();
        String query = "SELECT * FROM public.feature ORDER BY feature_id ASC";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                featureList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return featureList;
    }

    public Feature findById(int id) {
        Feature feature = null;
        String query = "SELECT * FROM public.feature WHERE feature_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                feature = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return feature;
    }
    public boolean save(Feature feature){
        String query = "INSERT INTO public.feature (feature_name) VALUES (?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1,feature.getName());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Feature feature){
        String query = "UPDATE public.feature SET feature_name = ? WHERE feature_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1,feature.getName());
            pr.setInt(2,feature.getId());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.feature WHERE feature_id = ?";
        try{
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public Feature match(ResultSet rs) throws SQLException {
        Feature feature = new Feature();
        feature.setId(rs.getInt("feature_id"));
        feature.setName(rs.getString("feature_name"));
        return feature;
    }
}
