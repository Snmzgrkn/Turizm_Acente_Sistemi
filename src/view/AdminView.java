package view;

import business.*;
import core.ComboItem;
import core.Helper;
import entity.Otel;
import entity.Pension;
import entity.Room;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class AdminView extends Layout {
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JTabbedPane tab_menu;
    private JButton btn_logout;
    private JPanel pnl_otel;
    private JTable tbl_otel;
    private JScrollPane scrl_otel;
    private JPanel pnl_pension;
    private JPanel pnl_feature;
    private JTable tbl_pension;
    private JScrollPane scrl_pension;
    private JTable tbl_feature;
    private JScrollPane scrl_feature;
    private JPanel pnl_season;
    private JTable tbl_season;
    private JScrollPane scrl_season;
    private JTable tbl_user;
    private JPanel pnl_user;
    private JScrollPane scrl_user;
    private JTable tbl_room;
    private JScrollPane scrl_room;
    private JPanel pnl_room;
    private JTable tbl_reservation;
    private JPanel pnl_reservation;
    private JScrollPane scrl_reservation;
    private JComboBox cmb_s_pensiontype;
    private JComboBox cmb_s_room;
    private JButton btn_search_otel;
    private JButton btn_cncl_otel;

    private User user;
    private DefaultTableModel tmdl_otel = new DefaultTableModel();
    private DefaultTableModel tmdl_pension= new DefaultTableModel();
    private DefaultTableModel tmdl_feature = new DefaultTableModel();
    private DefaultTableModel tmdl_season = new DefaultTableModel();
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private OtelManager otelManager;
    private PensionManager pensionManager;
    private FeatureManager featureManager;
    private SeasonManager seasonManager;
    private ReservationManager reservationManager;
    private UserManager userManager;
    private RoomManager roomManager;
    private JPopupMenu pensionMenu;
    private JPopupMenu featureMenu;
    private JPopupMenu otelMenu;
    private JPopupMenu seasonMenu;
    private JPopupMenu userMenu;
    private JPopupMenu roomMenu;
    private JPopupMenu reservationMenu;
    private  Object[] col_otel;

    public AdminView(User user){
        this.otelManager = new OtelManager();
        this.pensionManager = new PensionManager();
        this.featureManager = new FeatureManager();
        this.seasonManager=new SeasonManager();
        this.userManager = new UserManager();
        this.roomManager=new RoomManager();
        this.reservationManager = new ReservationManager();

        this.add(container);
        this.guiInitilaze(1000,500);
        this.user = user;
        if (user == null){
            dispose();
        }

        this.lbl_welcome.setText("Hoşgeldiniz :" + this.user.getFullname());

        //---------------------------------------------------------------------------------------------

        loadOtelTable(null);
        loadOtelComponent();
        loadOtelFliter();

        //---------------------------------------------------------------------------------------------
        loadPensionTable();
        loadPensionComponent();
        //---------------------------------------------------------------------------------------------
        loadFeatureTable();
        loadFeatureComponent();
        //---------------------------------------------------------------------------------------------
        loadSeasonTable();
        loadSeasonComponent();
        //---------------------------------------------------------------------------------------------
        loadUserTable();
        loadUserComponent();
        //---------------------------------------------------------------------------------------------
        loadRoomTable();
        loadRoomComponent();
        //---------------------------------------------------------------------------------------------
        loadReservationTable();
        loadReservationComponent();




    }

    public void loadPensionTable(){
        Object[] col_pension = {"ID","Pansiyon Adı"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length);
        this.createTable(tmdl_pension,tbl_pension,col_pension,pensionList);
    }
    public void loadPensionComponent(){
        this.tbl_pension.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_pension.rowAtPoint(e.getPoint());
                tbl_pension.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        this.pensionMenu = new JPopupMenu();

        JMenuItem newPensionItem = new JMenuItem("Yeni");
        this.pensionMenu.add("Yeni").addActionListener(e -> {
            PensionView pensionView = new PensionView(null);
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();
                }
            });
        });
        this.pensionMenu.add("Güncelle").addActionListener(e -> {
            int selectPensionId = Integer.parseInt(tbl_pension.getValueAt(tbl_pension.getSelectedRow(),0).toString());
            PensionView pensionView = new PensionView(this.pensionManager.findById(selectPensionId));
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable();
                }
            });
        });
        this.pensionMenu.add("Sil").addActionListener(e -> {
            if(Helper.confirm("sure")){
                int selecPensionId = this.getTableSelectedRow(tbl_pension,0);
                if(this.pensionManager.delete(selecPensionId)){
                    loadPensionTable();
                }else{
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_pension.setComponentPopupMenu(pensionMenu);
    }
    public void loadFeatureTable(){
        Object[] col_feature = {"ID","Tesis Özellik Adı"};
        ArrayList<Object[]> featureList = this.featureManager.getForTable(col_feature.length);
        this.createTable(tmdl_feature,tbl_feature,col_feature,featureList);
    }
    public void loadFeatureComponent(){
        this.tbl_feature.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_feature.rowAtPoint(e.getPoint());
                tbl_feature.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        this.featureMenu = new JPopupMenu();

        JMenuItem newPensionItem = new JMenuItem("Yeni");
        this.featureMenu.add("Yeni").addActionListener(e -> {
            FeatureView featureView = new FeatureView(null);
            featureView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadFeatureTable();
                }
            });
        });
        this.featureMenu.add("Güncelle").addActionListener(e -> {
            int selectFeatureId = Integer.parseInt(tbl_feature.getValueAt(tbl_feature.getSelectedRow(),0).toString());
            FeatureView featureView = new FeatureView(this.featureManager.findById(selectFeatureId));
            featureView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadFeatureTable();

                }
            });
        });
        this.featureMenu.add("Sil").addActionListener(e -> {
            if(Helper.confirm("sure")){
                int selectFeatureId = this.getTableSelectedRow(tbl_feature,0);
                if(this.featureManager.delete(selectFeatureId)){
                    loadFeatureTable();
                }else{
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_feature.setComponentPopupMenu(featureMenu);
    }
    public void loadOtelTable(ArrayList<Object[]> otelList){
        this.col_otel = new Object[]{"Otel ID", "Otel Adı", "Otel Adresi", "Mail", "Telefon No", "Yıldız", "Pansiyon Tipi", "Oda Tipi", "Tesis Özellikleri"};
        if(otelList == null){
            otelList = this.otelManager.getForTable(this.col_otel.length,this.otelManager.findAll());
        }
        createTable(this.tmdl_otel,this.tbl_otel,col_otel,otelList);
    }
    public void loadOtelComponent(){
        this.tbl_otel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_otel.rowAtPoint(e.getPoint());
                tbl_otel.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        this.otelMenu = new JPopupMenu();

        JMenuItem newUserItem = new JMenuItem("Yeni");
        this.otelMenu.add("Yeni").addActionListener(e -> {
            OtelView OtelView = new OtelView(null);
            OtelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadOtelTable(null);
                    loadOtelFliter();
                }
            });
        });
        this.otelMenu.add("Güncelle").addActionListener(e -> {
            int selectFeatureId = Integer.parseInt(tbl_otel.getValueAt(tbl_otel.getSelectedRow(),0).toString());
            OtelView otelView = new OtelView(this.otelManager.findById(selectFeatureId));
            otelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadOtelTable(null);
                    loadOtelFliter();
                }
            });
        });
        this.otelMenu.add("Sil").addActionListener(e -> {
            if(Helper.confirm("sure")){
                int selectOtelId = this.getTableSelectedRow(tbl_otel,0);
                if(this.otelManager.delete(selectOtelId)){
                    loadOtelTable(null);
                    loadOtelFliter();
                }else{
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_otel.setComponentPopupMenu(otelMenu);

        this.btn_search_otel.addActionListener(e -> {
            ComboItem selectedPension = (ComboItem) this.cmb_s_pensiontype.getSelectedItem();
            ComboItem selectedRoom = (ComboItem) this.cmb_s_room.getSelectedItem();

            ArrayList<Otel> otelListBySearch = this.otelManager.searchForTable(selectedPension.getKey(),selectedRoom.getKey());
            System.out.println(otelListBySearch);

            ArrayList<Object[]> otelRowListBySearch = this.otelManager.getForTable(this.col_otel.length,otelListBySearch);
            loadOtelTable(otelRowListBySearch);
        });

        this.btn_cncl_otel.addActionListener(e -> {
            this.cmb_s_pensiontype.setSelectedItem(null);
            this.cmb_s_room.setSelectedItem(null);
            loadOtelTable(null);
        });


    }
    public void loadSeasonTable(){
        Object[] col_season = {"ID","Sezon Adı","Başlangıç Tarihi","Bitiş Tarihi","Fiyat Katsayısı"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length);
        this.createTable(tmdl_season,tbl_season,col_season,seasonList);
    }
    public void loadSeasonComponent(){
        this.tbl_season.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_season.rowAtPoint(e.getPoint());
                tbl_season.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        this.seasonMenu = new JPopupMenu();

        JMenuItem newPensionItem = new JMenuItem("Yeni");
        this.seasonMenu.add("Yeni").addActionListener(e -> {
            SeasonView seasonView = new SeasonView(null);
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadOtelTable(null);
                }
            });
        });
        this.seasonMenu.add("Güncelle").addActionListener(e -> {
            int selectFeatureId = Integer.parseInt(tbl_season.getValueAt(tbl_season.getSelectedRow(),0).toString());
            SeasonView seasonView = new SeasonView(this.seasonManager.findById(selectFeatureId));
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadFeatureTable();
                }
            });
        });
        this.seasonMenu.add("Sil");

        this.tbl_season.setComponentPopupMenu(seasonMenu);
    }
    public void loadUserTable(){
        Object[] col_user = {"ID","Kullanıcı Adı","Şifre","Rol","İsim Soyisim"};
        ArrayList<Object[]> userList = this.userManager.getForTable(col_user.length);
        this.createTable(tmdl_user,tbl_user,col_user,userList);
    }
    public void loadUserComponent(){
        this.tbl_user.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_user.rowAtPoint(e.getPoint());
                tbl_user.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        this.userMenu = new JPopupMenu();

        JMenuItem newPensionItem = new JMenuItem("Yeni");
        this.userMenu.add("Yeni").addActionListener(e -> {
            UserView userView = new UserView(null);
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });
        this.userMenu.add("Güncelle").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_user,0);
            UserView userView = new UserView(this.userManager.findById(selectUserId));
            userView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadUserTable();
                }
            });
        });
        this.userMenu.add("Sil").addActionListener(e -> {
            if(Helper.confirm("sure")){
                int selectUserId = this.getTableSelectedRow(tbl_user,0);
                if(this.userManager.delete(selectUserId)){
                    loadUserTable();
                }else{
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_user.setComponentPopupMenu(userMenu);
    }
    public void loadRoomTable(){
        Object[] col_room= {"ID","Oda Adı","Oda Fiyatı","Stok"};
        ArrayList<Object[]> roomList = this.roomManager.getForTable(col_room.length);
        this.createTable(tmdl_room,tbl_room,col_room,roomList);
    }
    public void loadRoomComponent(){
        this.tbl_room.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_room.rowAtPoint(e.getPoint());
                tbl_room.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        this.roomMenu = new JPopupMenu();

        JMenuItem newPensionItem = new JMenuItem("Yeni");
        this.roomMenu.add("Yeni").addActionListener(e -> {
            RoomView roomView = new RoomView(null);
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable();
                }
            });
        });
        this.roomMenu.add("Güncelle").addActionListener(e -> {
            int selectUserId = this.getTableSelectedRow(tbl_room,0);
            RoomView roomView = new RoomView(this.roomManager.findById(selectUserId));
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable();
                }
            });
        });
        this.roomMenu.add("Sil").addActionListener(e -> {
            if(Helper.confirm("sure")){
                int selectUserId = this.getTableSelectedRow(tbl_room,0);
                if(this.roomManager.delete(selectUserId)){
                    loadRoomTable();
                }else{
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_room.setComponentPopupMenu(roomMenu);
    }
    public void loadReservationTable(){
        Object[] col_reservation = {"ID","Müşteri İsim","Otel Bilgileri","Başlangıç Tarihi","Bitiş Tarihi","Yetişkin Sayısı","Çocuk Sayısı","Toplam Ücret"};
        ArrayList<Object[]> reservationList = this.reservationManager.getForTable(col_reservation.length);
        this.createTable(tmdl_reservation,tbl_reservation,col_reservation,reservationList);
    }
    public void loadReservationComponent(){
        this.tbl_reservation.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tbl_reservation.rowAtPoint(e.getPoint());
                tbl_reservation.setRowSelectionInterval(selected_row,selected_row);
            }
        });

        this.reservationMenu = new JPopupMenu();

        JMenuItem newPensionItem = new JMenuItem("Yeni");
        this.reservationMenu.add("Yeni").addActionListener(e -> {
            ReservationView  reservationView = new ReservationView(null);
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable();
                }
            });
        });
        this.reservationMenu.add("Güncelle").addActionListener(e -> {
            int selectReservationId = this.getTableSelectedRow(tbl_reservation,0);
            ReservationView reservationView = new ReservationView(this.reservationManager.findById(selectReservationId));
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable();
                }
            });
        });
        this.reservationMenu.add("Sil").addActionListener(e -> {
            if(Helper.confirm("sure")){
                int selectUserId = this.getTableSelectedRow(tbl_reservation,0);
                if(this.reservationManager.delete(selectUserId)){
                    loadReservationTable();
                }else{
                    Helper.showMessage("error");
                }
            }
        });

        this.tbl_reservation.setComponentPopupMenu(reservationMenu);
    }

    public void loadOtelFliter(){
        this.cmb_s_pensiontype.removeAllItems();
        for(Pension obj : pensionManager.findAll()){
            this.cmb_s_pensiontype.addItem(new ComboItem(obj.getId(),obj.getName()));
        }
        this.cmb_s_pensiontype.setSelectedItem(null);
        this.cmb_s_room.removeAllItems();
        for(Room obj : roomManager.findAll()){
            this.cmb_s_room.addItem(new ComboItem(obj.getId(),obj.getName()));
        }
        this.cmb_s_room.setSelectedItem(null);

    }

}
