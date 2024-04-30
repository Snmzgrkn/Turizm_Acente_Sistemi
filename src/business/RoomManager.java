package business;

import core.Helper;
import dao.FeatureDao;
import dao.RoomDao;
import entity.Feature;
import entity.Pension;
import entity.Room;

import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;

    public RoomManager(){
        this.roomDao=new RoomDao();
    }

    public ArrayList<Room> findAll(){
        return this.roomDao.findAll();
    }
    public boolean save(Room room){
        if(room.getId() != 0){
            Helper.showMessage("error");
        }
        return this.roomDao.save(room);
    }

    public Room findById(int id){
        return this.roomDao.findById(id);
    }

    public boolean update(Room room){
        if(this.findById(room.getId()) == null){
            Helper.showMessage("notfound");
        }
        return this.roomDao.update(room);
    }
    public boolean delete(int id){
        if(this.findById(id)==null){
            Helper.showMessage(id+" ID kayıtlı oda bulunmadı.");
            return false;
        }
        return this.roomDao.delete(id);
    }
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> roomRowList = new ArrayList<>();
        for(Room room : this.findAll()){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++] = room.getId();
            rowObject[i++] = room.getName();
            rowObject[i++] = room.getPrice();
            rowObject[i++] = room.getStock();
            roomRowList.add(rowObject);
        }
        return roomRowList;
    }
}
