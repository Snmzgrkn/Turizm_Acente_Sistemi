package business;

import core.Helper;
import dao.FeatureDao;
import dao.OtelDao;
import entity.Feature;
import entity.Otel;
import entity.Pension;

import java.util.ArrayList;

public class FeatureManager {
    private final FeatureDao featureDao;

    public FeatureManager(){
        this.featureDao=new FeatureDao();
    }

    public ArrayList<Feature> findAll(){
        return this.featureDao.findAll();
    }
    public boolean save(Feature feature){
        if(feature.getId() != 0){
            Helper.showMessage("error");
        }
        return this.featureDao.save(feature);
    }

    public  Feature findById(int id){
        return this.featureDao.findById(id);
    }

    public boolean update(Feature feature){
        if(this.findById(feature.getId()) == null){
            Helper.showMessage("notfound");
        }
        return this.featureDao.update(feature);
    }
    public boolean delete(int id){
        if(this.findById(id)==null){
            Helper.showMessage(id+" ID kayıtlı marka bulunmadı.");
            return false;
        }
        return this.featureDao.delete(id);
    }
    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> featureRowList = new ArrayList<>();
        for(Feature feature : this.findAll()){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++] = feature.getId();
            rowObject[i++] = feature.getName();
            featureRowList.add(rowObject);
        }
        return featureRowList;
    }
}
