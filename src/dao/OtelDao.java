package dao;

import core.Db;
import entity.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OtelDao {
    private final Connection connection;
    private final PensionDao pensionDao; // Pension veritabanı işlemleri için DAO sınıfı
    private final FeatureDao featureDao; // Feature veritabanı işlemleri için DAO sınıfı
    private final RoomDao roomDao;

    public OtelDao() {
        this.connection = Db.getInstance();
        this.pensionDao = new PensionDao(); // PensionDao sınıfının örneği
        this.featureDao = new FeatureDao(); // FeatureDao sınıfının örneği
        this.roomDao = new RoomDao();
    }

    public ArrayList<Otel> findAll(){
        ArrayList<Otel> otelList = new ArrayList<>();
        String query = "SELECT * FROM public.otel";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()){
                otelList.add(this.match(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return otelList;
    }
    public Otel findById(int id) {
        Otel otel = null;
        String query = "SELECT * FROM otel WHERE otel_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                otel = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return otel;
    }

    public Otel match(ResultSet rs) throws SQLException {
        Otel obj = new Otel();
        obj.setId(rs.getInt("otel_id"));
        obj.setName(rs.getString("otel_name"));
        obj.setAddress(rs.getString("otel_address"));
        obj.setMail(rs.getString("otel_mail"));
        obj.setPhoneno(rs.getString("otel_phoneno"));
        obj.setStar(rs.getInt("otel_star"));

        int pensionId = rs.getInt("otel_pensiontype_id");
        Pension pension = pensionDao.findById(pensionId);
        obj.setPensiontype(pension);

        String featuresIdsStr = rs.getString("otel_feature_ids");
        if (featuresIdsStr != null && !featuresIdsStr.isEmpty()) {
            featuresIdsStr = featuresIdsStr.replaceAll("[{}]", ""); // '{}' karakterlerini kaldır
            String[] featureIds = featuresIdsStr.split(",");
            List<Feature> features = new ArrayList<>();
            for (String featureIdStr : featureIds) {
                try {
                    int featureId = Integer.parseInt(featureIdStr.trim());
                    Feature feature = featureDao.findById(featureId);
                    if (feature != null) {
                        features.add(feature);
                    } else {
                        System.out.println("Feature not found with id: " + featureId);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid feature id: " + featureIdStr);
                }
            }
            obj.setFeatures(features);
        }
        int roomId = rs.getInt("otel_room_id");
        Room room = roomDao.findById(roomId);
        obj.setRoomtype(room);

        return obj;
    }
}
