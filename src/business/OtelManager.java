package business;

import dao.OtelDao;
import entity.Feature;
import entity.Otel;
import entity.Pension;

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

    public ArrayList<Object[]> getForTable(int size){
        ArrayList<Object[]> otelRowList = new ArrayList<>();
        for(Otel otel : this.findAll()){
            Object[] rowObject = new Object[size];
            int i =0;
            rowObject[i++] = otel.getId();
            rowObject[i++] = otel.getName();
            rowObject[i++] = otel.getAddress();
            rowObject[i++] = otel.getMail();
            rowObject[i++] = otel.getPhoneno();
            rowObject[i++] = otel.getStar();
            rowObject[i++] = otel.getPensiontype().getName();
            rowObject[i++] = formatFeatures(otel.getFeatures());
            rowObject[i++] = otel.getRoomtype().getName();
            otelRowList.add(rowObject);
        }
        return otelRowList;
    }
    private String formatFeatures(List<Feature> features) {
        StringBuilder sb = new StringBuilder();
        if (features != null && !features.isEmpty()) {
            for (Feature feature : features) {
                sb.append(feature.getName()).append(", "); // Özellik ismini ekleyerek virgülle ayır
            }
            sb.setLength(sb.length() - 2); // Son virgülü ve boşluğu kaldır
        }
        return sb.toString();
    }
}
