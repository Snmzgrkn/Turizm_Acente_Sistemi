package view;

import business.SeasonManager;
import entity.Season;

import javax.swing.*;

public class SeasonView extends Layout{
    private JPanel container;
    private Season season;
    private SeasonManager seasonManager;
    public SeasonView(Season season){
        this.seasonManager = new SeasonManager();
        this.add(container);
        this.guiInitilaze(300,400);
        this.season = season;

    }
}
