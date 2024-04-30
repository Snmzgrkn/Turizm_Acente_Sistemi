package view;

import business.OtelManager;
import business.ReservationManager;
import core.ComboItem;
import core.Helper;
import entity.Otel;
import entity.Pension;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import java.sql.Date;
import java.time.LocalDate;

public class ReservationView extends Layout {
    private JPanel container;
    private JLabel lbl_reservation;
    private JTextField fld_customer_name;
    private JComboBox cmb_otel;
    private JTextField fld_adult_number;
    private JTextField fld_child_number;
    private JButton btn_reservation_save;
    private JLabel lbl_customer_name;
    private JLabel lbl_otel;
    private JLabel lbl_start_date;
    private JTextField fld_strt_date;
    private JTextField fld_fnsh_date;
    private JLabel lbl_fnsh_date;
    private JLabel lbl_adult_number;
    private JLabel lbl_child_number;
    private ReservationManager reservationManager;
    private OtelManager otelManager;
    private Reservation reservation;

    public ReservationView(Reservation reservation) {
        this.reservation =reservation;
        this.reservationManager=new ReservationManager();
        this.otelManager=new OtelManager();
        this.add(container);
        this.guiInitilaze(500,500);

        for(Otel otel :this.otelManager.findAll()){
            this.cmb_otel.addItem(otel.getComboItem());
        }

        if(reservation != null){
            this.fld_customer_name.setText(reservation.getCustomer_name());
            ComboItem selectedItem = reservation.getOtel().getComboItem();
            this.cmb_otel.getModel().setSelectedItem(selectedItem);
            this.fld_strt_date.setText(String.valueOf(Date.valueOf(reservation.getStrt_date())));
            this.fld_fnsh_date.setText(String.valueOf(Date.valueOf(reservation.getFnsh_date())));
            this.fld_adult_number.setText(Integer.toString(reservation.getAdult_number()));
            this.fld_child_number.setText(Integer.toString(reservation.getChild_number()));
        }

        this.btn_reservation_save.addActionListener(e -> {
            if(Helper.isFieldListEmpty(new JTextField[]{fld_customer_name,fld_adult_number})){
                Helper.showMessage("fill");
            }else {
                String customer_name = this.fld_customer_name.getText();
                LocalDate strt_date = LocalDate.parse(this.fld_strt_date.getText());
                LocalDate fnsh_date = LocalDate.parse(this.fld_fnsh_date.getText());
                String adult_number = this.fld_adult_number.getText();
                String child_number = this.fld_child_number.getText();

                ComboItem selectedOtelItem = (ComboItem) this.cmb_otel.getSelectedItem();
                int otelId = selectedOtelItem.getKey();
                Otel selectedOtel = this.otelManager.findById(otelId);
                System.out.println(selectedOtel);


                Reservation newReservation = new Reservation(customer_name, selectedOtel, strt_date, fnsh_date, adult_number, child_number);
                boolean result;

                if (this.reservation == null) {
                    // Yeni Otel nesnesini kaydet
                    result = this.reservationManager.save(newReservation);
                } else {
                    // Var olan Otel nesnesini güncelle
                    newReservation.setId(this.reservation.getId()); // Güncelleme için mevcut Otel'in ID'sini ayarla
                    result = this.reservationManager.update(newReservation);
                }

                if (result) {
                    Helper.showMessage("Reservation saved successfully.");
                    dispose();
                } else {
                    Helper.showMessage("Error occurred while saving hotel.");
                }

            }
        });
    }

}

