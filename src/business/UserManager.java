package business;

import core.Helper;
import dao.UserDao;
import entity.Pension;
import entity.Season;
import entity.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;

    public UserManager(){
        //Constructor
        this.userDao = new UserDao();
    }
    public User findByLogin(String username,String password){
        return this.userDao.findByLogin(username,password);
    }
    public ArrayList<User> findAll(){
        return this.userDao.findAll();
    }

    public boolean save(User user){
        if(user.getId() != 0){
            Helper.showMessage("error");
        }
        return this.userDao.save(user);
    }
    public  User findById(int id){
        return this.userDao.findById(id);
    }
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for(User user : this.findAll()){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getPassword();
            rowObject[i++] = user.getRole();
            rowObject[i++] = user.getFullname();
            userRowList.add(rowObject);
        }
        return userRowList;
    }
    public boolean delete(int id){
        if(this.findById(id)==null){
            Helper.showMessage(id+" ID kayıtlı marka bulunmadı.");
            return false;
        }
        return this.userDao.delete(id);
    }
    public boolean update(User user){
        if(this.findById(user.getId()) == null){
            Helper.showMessage("notfound");
        }
        return this.userDao.update(user);
    }
}
