package dao;

import core.Db;
import entity.Pension;
import entity.Season;
import entity.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    private final Connection connection;

    public UserDao() {
        this.connection = Db.getInstance();
    }

    public boolean save(User user){
        String query = "INSERT INTO public.user (user_name,user_password,user_role,user_fullname) VALUES (?,?,?,?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1,user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3,user.getRole());
            pr.setString(4,user.getFullname());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public ArrayList<User> findAll(){
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM public.user";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()){
                userList.add(this.match(rs));
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return userList;
    }
    public User findById(int id) {
        User user = null;
        String query = "SELECT * FROM public.user WHERE user_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                user = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public boolean update(User user){
        String query = "UPDATE public.user SET user_name = ?,user_password = ?,user_role = ?,user_fullname = ? WHERE user_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1,user.getUsername());
            pr.setString(2,user.getPassword());
            pr.setString(3,user.getRole());
            pr.setString(4,user.getFullname());
            pr.setInt(5,user.getId());
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.user WHERE user_id = ?";
        try{
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
    public User findByLogin(String username,String password){
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
               obj = this.match(rs);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  obj;
    }

    public User match(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setId(rs.getInt("user_id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_password"));
        obj.setRole(rs.getString("user_role"));
        obj.setFullname(rs.getString("user_fullname"));
       return obj;
    }
}
