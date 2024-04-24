package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

public class UserView extends Layout{
    private JPanel container;
    private JTextField fld_username;
    private JTextField fld_password;
    private JTextField fld_role;
    private JTextField fld_fullname;
    private JButton btn_user_save;
    private JLabel lbl_username;
    private JLabel lbl_password;
    private JLabel lbl_role;
    private JLabel lbl_fullname;
    private UserManager userManager;
    private User user;

    public UserView(User user){
        this.userManager=new UserManager();
        this.add(container);
        this.guiInitilaze(300,400);
        this.user=user;

        if(user != null){
            this.fld_username.setText(user.getUsername());
            this.fld_password.setText(user.getPassword());
            this.fld_role.setText(user.getRole());
            this.fld_fullname.setText(user.getFullname());

        }

        btn_user_save.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_username,this.fld_password,this.fld_role,this.fld_fullname};
            if(Helper.isFieldListEmpty(checkFieldList)){
                Helper.showMessage("fill");
            }else {
                boolean result = true;
                if(this.user == null){
                    User obj = new User(this.fld_username.getText(),this.fld_password.getText(),this.fld_role.getText(),this.fld_fullname.getText());
                    result = this.userManager.save(obj);
                }else {
                    this.user.setUsername(this.fld_username.getText());
                    this.user.setPassword(this.fld_password.getText());
                    this.user.setFullname(this.fld_role.getText());
                    this.user.setRole(this.fld_fullname.getText());

                    result = this.userManager.update(this.user);
                }

                if (result){
                    Helper.showMessage("done");
                    dispose();
                }else {
                    Helper.showMessage("error");
                }
            }
        });

    }


}
