package view;

import business.FeatureManager;
import business.OtelManager;
import business.PensionManager;
import business.RoomManager;
import core.ComboItem;
import core.Helper;
import dao.FeatureDao;
import dao.PensionDao;
import entity.Otel;
import entity.Pension;
import entity.Room;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class OtelView extends Layout {
    private JPanel container;
    private JTextField fld_otel_name;
    private JTextField fld_otel_address;
    private JTextField fld_otel_mail;
    private JTextField fld_otel_phoneno;
    private JTextField fld_otel_star;
    private JComboBox<ComboItem> cmb_otel_pensiontype;
    private JLabel lbl_otel;
    private JLabel lbl_otel_name;
    private JLabel lbl_otel_address;
    private JLabel lbl_otel_mail;
    private JLabel lbl_otel_phoneno;
    private JLabel lbl_otel_star;
    private JLabel lbl_otel_pensiontype;
    private JList list_features;
    private JButton btn_otel_save;
    private JComboBox cmb_room;
    private JLabel lbl_room;
    private JTextField fld_otel_features;
    private JLabel lbl_otel_features;
    private Otel otel;
    private OtelManager otelManager;
    private PensionManager pensionManager;
    private FeatureManager featureManager;
    private RoomManager roomManager;
    private PensionDao pensionDao;
    private FeatureDao featureDao;

    public OtelView(Otel otel){
        this.otelManager=new OtelManager();
        this.pensionManager=new PensionManager();
        this.featureManager= new FeatureManager();
        this.roomManager = new RoomManager();
        this.add(container);
        this.guiInitilaze(700,700);
        this.otel=otel;

        if(otel != null){
            this.fld_otel_name.setText(otel.getName());
            this.fld_otel_address.setText(otel.getAddress());
            this.fld_otel_mail.setText(otel.getMail());
            this.fld_otel_phoneno.setText(otel.getPhoneno());
            this.fld_otel_star.setText(String.valueOf(otel.getStar()));
            this.cmb_otel_pensiontype.addItem(new ComboItem(otel.getPensiontype().getId(),otel.getPensiontype().getName()));
            this.cmb_room.addItem(new ComboItem(otel.getRoomtype().getId(),otel.getRoomtype().getName(),otel.getRoomtype().getPrice()));
            this.fld_otel_features.setText(String.valueOf(otel.getFeatures()));
        }

        for(Pension pension : this.pensionManager.findAll()){
            this.cmb_otel_pensiontype.addItem(new ComboItem(pension.getId(),pension.getName()));
        }

        for(Room room : this.roomManager.findAll()){
            this.cmb_room.addItem(new ComboItem(room.getId(),room.getName(),room.getPrice()));
        }


        btn_otel_save.addActionListener(e -> {
            if (Helper.isFieldEmpty(this.fld_otel_name)) {
                Helper.showMessage("Please fill in the hotel name.");
            } else {
                String name = this.fld_otel_name.getText();
                String address = this.fld_otel_address.getText();
                String mail = this.fld_otel_mail.getText();
                String phoneno = this.fld_otel_phoneno.getText();
                int star = Integer.parseInt(this.fld_otel_star.getText());

                ComboItem selectedPensionItem = (ComboItem) this.cmb_otel_pensiontype.getSelectedItem();
                int pensionTypeId = selectedPensionItem.getKey();
                Pension selectedPension = this.pensionManager.findById(pensionTypeId);

                // Seçilen özelliklerle bir Otel nesnesi oluşturalım

                ComboItem selectedRoomItem = (ComboItem) this.cmb_room.getSelectedItem();
                int roomId = selectedRoomItem.getKey();
                Room selectedRoom = this.roomManager.findById(roomId);
                String features = this.fld_otel_features.getText();

                Otel newOtel = new Otel(name, address, mail, phoneno, star, selectedPension, selectedRoom,features);
                boolean result;

                if (this.otel == null) {
                    // Yeni Otel nesnesini kaydet
                    result = this.otelManager.save(newOtel);
                } else {
                    // Var olan Otel nesnesini güncelle
                    newOtel.setId(this.otel.getId()); // Güncelleme için mevcut Otel'in ID'sini ayarla
                    result = this.otelManager.update(newOtel);
                }

                if (result) {
                    Helper.showMessage("Hotel saved successfully.");
                    dispose(); // Pencereyi kapat
                } else {
                    Helper.showMessage("Error occurred while saving hotel.");
                }

            }
        });
    }
}
