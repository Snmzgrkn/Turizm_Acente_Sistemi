package view;

import business.FeatureManager;
import business.OtelManager;
import business.PensionManager;
import business.RoomManager;
import core.ComboItem;
import dao.FeatureDao;
import dao.PensionDao;
import dao.RoomDao;
import entity.Feature;
import entity.Otel;
import entity.Pension;
import entity.Room;

import javax.swing.*;

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
    private JLabel lbl_otel_features;
    private JList list_features;
    private JButton kaydetButton;
    private JComboBox cmb_room;
    private JLabel lbl_room;
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

        }

        for(Pension pension : this.pensionManager.findAll()){
            this.cmb_otel_pensiontype.addItem(new ComboItem(pension.getId(),pension.getName()));
        }
        DefaultListModel<ComboItem> listModel = new DefaultListModel<>();
        for (Feature feature : this.featureManager.findAll()) {
            ComboItem comboItem = new ComboItem(feature.getId(), feature.getName());
            listModel.addElement(comboItem);
        }
        this.list_features.setModel(listModel);

        for(Room room : this.roomManager.findAll()){
            this.cmb_room.addItem(new ComboItem(room.getId(),room.getName(),room.getPrice()));
        }
    }
}
