package view;

import entity.User;

import javax.swing.*;

public class EmployeeView extends Layout{
    private JPanel container;
    private User user;

    public EmployeeView(User user){
        this.add(container);
        this.guiInitilaze(1000,500);
        this.user=user;
    }
}
