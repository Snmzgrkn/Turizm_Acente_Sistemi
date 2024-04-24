package business;

import core.Helper;
import dao.OtelDao;
import dao.PensionDao;
import entity.Otel;
import entity.Pension;

import java.util.ArrayList;

public class PensionManager {
    private final PensionDao pensionDao;

    public PensionManager(){
        this.pensionDao=new PensionDao();
    }

    public ArrayList<Pension> findAll(){
        return this.pensionDao.findAll();
    }
    public boolean save(Pension pension){
        if(pension.getId() != 0){
            Helper.showMessage("error");
        }
        return this.pensionDao.save(pension);
    }

    public  Pension findById(int id){
        return this.pensionDao.findById(id);
    }

    public boolean update(Pension pension){
        if(this.findById(pension.getId()) == null){
            Helper.showMessage("notfound");
        }
        return this.pensionDao.update(pension);
    }
    public boolean delete(int id){
        if(this.findById(id)==null){
            Helper.showMessage(id+" ID kayıtlı marka bulunmadı.");
            return false;
        }
        return this.pensionDao.delete(id);
    }
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> pensionRowList = new ArrayList<>();
        for(Pension pension : this.findAll()){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++] = pension.getId();
            rowObject[i++] = pension.getName();
            pensionRowList.add(rowObject);
        }
        return pensionRowList;
    }
}
