package business;

import core.Helper;
import dao.FeatureDao;
import dao.SeasonDao;
import entity.Feature;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao;

    public SeasonManager(){
        this.seasonDao=new SeasonDao();
    }

    public ArrayList<Season> findAll(){
        return this.seasonDao.findAll();
    }
    public boolean save(Season season){
        if(season.getId() != 0){
            Helper.showMessage("error");
        }
        return this.seasonDao.save(season);
    }

    public  Season findById(int id){
        return this.seasonDao.findById(id);
    }

    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> seasonRowList = new ArrayList<>();
        for(Season season : this.findAll()){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++] = season.getId();
            rowObject[i++] = season.getName();
            rowObject[i++] = season.getStartDate();
            rowObject[i++] = season.getEndDate();
            rowObject[i++] = season.getRateMultiplier();
            seasonRowList.add(rowObject);
        }
        return seasonRowList;
    }
}
