package business;

import core.Helper;
import dao.OtelDao;
import entity.Feature;
import entity.Otel;

import java.util.ArrayList;
import java.util.List;

public class OtelManager {
    private final OtelDao otelDao;

    public OtelManager(){
        this.otelDao=new OtelDao();
    }

    public ArrayList<Otel> findAll(){
        return this.otelDao.findAll();
    }
    public Otel findById(int id){
        return this.otelDao.findById(id);
    }
    public boolean delete(int id){
        if(this.findById(id)==null){
            Helper.showMessage(id+" ID kayıtlı otel bulunmadı.");
            return false;
        }
        return this.otelDao.delete(id);
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Otel> all){
        ArrayList<Object[]> otelRowList = new ArrayList<>();
        for(Otel otel : all){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++] = otel.getId();
            rowObject[i++] = otel.getName();
            rowObject[i++] = otel.getAddress();
            rowObject[i++] = otel.getMail();
            rowObject[i++] = otel.getPhoneno();
            rowObject[i++] = otel.getStar();
            rowObject[i++] = otel.getPensiontype().getName();
            rowObject[i++] = otel.getRoomtype().getName();
            rowObject[i++] = otel.getFeatures();
            otelRowList.add(rowObject);
        }
        return otelRowList;
    }
    public boolean save(Otel otel){
        if(otel.getId() != 0){
            Helper.showMessage("error");
        }
        return this.otelDao.save(otel);
    }
    public boolean update(Otel otel){
        if(this.findById(otel.getId()) == null){
            Helper.showMessage("notfound");
        }
        return this.otelDao.update(otel);
    }
    public  ArrayList<Otel> searchForTable(int pension_id, int room_id  ){
        String select = "SELECT * FROM public.otel";
        ArrayList<String> wherelist = new ArrayList<>();
        if(pension_id != 0){
            wherelist.add("otel_pensiontype_id ="+pension_id);

        }
        if (room_id != 0){
            wherelist.add("otel_room_id ="+room_id);
        }
        String whereStr = String.join(" AND ",wherelist);
        String query = select;
        if(whereStr.length() > 0){
            query += " WHERE "+whereStr;
        }
        return  this.otelDao.selectByQuery(query);
    }
}
