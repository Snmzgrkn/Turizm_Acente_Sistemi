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
    public boolean save(Otel otel){
        String query = "INSERT INTO otel (otel_name, otel_address, otel_mail, otel_phoneno, otel_star, otel_pensiontype_id, otel_room_id, otel_features) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setString(1, otel.getName());
            ps.setString(2, otel.getAddress());
            ps.setString(3, otel.getMail());
            ps.setString(4, otel.getPhoneno());
            ps.setInt(5, otel.getStar());
            ps.setInt(6, otel.getPensiontype().getId()); // Pension type ID
            ps.setInt(7,otel.getRoomtype().getId()); // Feature IDs
            ps.setString(8, otel.getFeatures() ); // Room type ID
            return ps.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Otel otel) {
        String query = "UPDATE otel " +
                "SET otel_name = ?, otel_address = ?, otel_mail = ?, otel_phoneno = ?, otel_star = ?, " +
                "otel_pensiontype_id = ?, otel_room_id = ?,otel_features = ?" +
                "WHERE otel_id = ?";

        try {
            PreparedStatement ps = this.connection.prepareStatement(query);
            ps.setString(1, otel.getName());
            ps.setString(2, otel.getAddress());
            ps.setString(3, otel.getMail());
            ps.setString(4, otel.getPhoneno());
            ps.setInt(5, otel.getStar());
            ps.setInt(6, otel.getPensiontype().getId()); // Pension type ID
            ps.setInt(7, otel.getRoomtype().getId()); // Feature IDs
            ps.setString(8, otel.getFeatures()); // Room type ID
            ps.setInt(9, otel.getId()); // Otel ID

            return ps.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<Otel> getByListPensionId(int pensionId) {
        return this.selectByQuery("SELECT * FROM public.otel WHERE otel_pensiontype_id = "+pensionId);
    }
    public ArrayList<Otel> selectByQuery(String query) {
        ArrayList<Otel> otelList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                otelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return otelList;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.otel WHERE otel_id = ?";
        try{
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
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

        int roomId = rs.getInt("otel_room_id");
        Room room = roomDao.findById(roomId);
        obj.setRoomtype(room);

        obj.setFeatures(rs.getString("otel_features"));
        return obj;
    }

}
