package view;

import business.FeatureManager;
import core.Helper;
import entity.Feature;
import entity.Pension;

import javax.swing.*;

public class FeatureView extends Layout {
    private JPanel container;
    private JLabel lbl_feature;
    private JLabel lbl_feature_name;
    private JTextField fld_feature_name;
    private JButton btn_feature_save;
    private Feature feature;
    private FeatureManager featureManager;

    public FeatureView(Feature feature){
        this.featureManager = new FeatureManager();
        this.add(container);
        this.guiInitilaze(300,200);
        this.feature = feature;

        if(feature != null){
            this.fld_feature_name.setText(feature.getName());
        }

        btn_feature_save.addActionListener(e -> {
            if(Helper.isFieldEmpty(this.fld_feature_name)){
                Helper.showMessage("fill");
            }else {
                boolean result = true;
                if(this.feature == null){
                    Feature obj = new Feature(fld_feature_name.getText());
                    result = this.featureManager.save(obj);
                }else {
                    this.feature.setName(fld_feature_name.getText());
                    result = this.featureManager.update(this.feature);
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
