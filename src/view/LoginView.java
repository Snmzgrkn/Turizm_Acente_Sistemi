package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends Layout{
    private JPanel container;
    private JLabel lbl_welcome;
    private JPanel w_top;
    private JPanel w_bottom;
    private JTextField fld_username;
    private JPasswordField fld_password;
    private JButton btn_login;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private final UserManager userManager;
    public LoginView(){
        this.userManager = new UserManager();
        this.add(container);
        this.guiInitilaze(400,400);
        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_username,this.fld_password};
            if (Helper.isFieldListEmpty(checkFieldList)){
                Helper.showMessage("fill");
            }else {
               User loginUser = this.userManager.findByLogin(this.fld_username.getText(),this.fld_password.getText());
               if (loginUser == null){
                   Helper.showMessage("notFound");
               }else {String role = loginUser.getRole();
                   if ("admin".equalsIgnoreCase(role)) {
                       AdminView adminView = new AdminView(loginUser);
                       adminView.setVisible(true);
                   } else if ("employee".equalsIgnoreCase(role)) {
                       EmployeeView employeeView = new EmployeeView(loginUser);
                       employeeView.setVisible(true);
                   } else {
                       Helper.showMessage("Invalid role");
                   }
                   dispose(); // LoginView penceresini kapat
               }
            }

        });
    }
}
