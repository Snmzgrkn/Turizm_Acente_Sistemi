package dao;

import core.Db;
import entity.Feature;
import entity.Otel;
import entity.Pension;
import entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {
    private final Connection connection;
    private final OtelDao otelDao;

    public ReservationDao() {
        this.connection = Db.getInstance();
        this.otelDao=new OtelDao();
    }

    public ArrayList<Reservation> findAll() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String query = "SELECT * FROM public.reservation ORDER BY reservation_id ASC";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                reservationList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }

    public Reservation findById(int id) {
        Reservation reservation = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                reservation = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }
    public boolean save(Reservation reservation){
        String query = "INSERT INTO public.reservation (reservation_customer_name,reservation_otel_id,reservation_strt_date,reservaton_fnsh_date,reservation_adult_number,reservation_child_number) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1,reservation.getCustomer_name());
            pr.setInt(2,reservation.getOtel().getId());
            pr.setString(3, String.valueOf((reservation.getStrt_date())));
            pr.setString(4, String.valueOf((reservation.getFnsh_date())));
            pr.setInt(5,reservation.getAdult_number());
            pr.setInt(6,reservation.getChild_number());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Reservation reservation){
        String query = "UPDATE public.reservation " +
                "SET reservation_customer_name = ?, reservation_otel_id = ?, reservation_strt_date = ?, reservation_fnsh_date = ?, reservation_adult_number = ?, " +
                "reservation_child_number = ? " +
                "WHERE reservation_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1,reservation.getCustomer_name());
            pr.setInt(2,reservation.getOtel().getId());
            pr.setString(3, String.valueOf((reservation.getStrt_date())));
            pr.setString(4, String.valueOf((reservation.getFnsh_date())));
            pr.setInt(5,reservation.getAdult_number());
            pr.setInt(6,reservation.getChild_number());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.reservation WHERE reservation_id = ?";
        try{
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public Reservation match(ResultSet rs) throws SQLException {
        Reservation obj = new Reservation();
        obj.setId(rs.getInt("reservation_id"));
        obj.setCustomer_name(rs.getString("reservation_customer_name"));

        int otelId = rs.getInt("reservation_otel_id");
        Otel otel = otelDao.findById(otelId);
        obj.setOtel(otel);

        obj.setStrt_date(LocalDate.parse(rs.getString("reservation_strt_date")));
        obj.setFnsh_date(LocalDate.parse(rs.getString("reservation_fnsh_date")));
        obj.setAdult_number(rs.getInt("reservation_adult_number"));
        obj.setChild_number(rs.getInt("reservation_child_number"));
        obj.setTotal_price(rs.getInt("reservation_total_price"));

        return obj;
    }
}
