package com.ftdes.migrights.managedBean;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.HorizontalBarChartModel;
import org.primefaces.model.chart.ChartSeries;

@ManagedBean
@SessionScoped
public class ChartView implements Serializable {

    private BarChartModel barModel;
    private HorizontalBarChartModel horizontalBarModel;
    private int max = 5;

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    @Inject
    private ReclamationController reqctrl;

    public BarChartModel getBarModel() {
        createBarModels();
        return barModel;
    }

    public HorizontalBarChartModel getHorizontalBarModel() {
        return horizontalBarModel;
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        model.setSeriesColors("2E2EFE, F781BE");

      ChartSeries boys = new ChartSeries();
        boys.setLabel("Hommes");
        boys.set("1", reqctrl.count_filter_sexe_type("Acte raciste verbal", "Homme"));
        boys.set("2", reqctrl.count_filter_sexe_type("Acte raciste physique", "Homme"));
        boys.set("3", reqctrl.count_filter_sexe_type("Exploitation", "Homme"));
        boys.set("4", reqctrl.count_filter_sexe_type("Violence sexuelle", "Homme"));
        boys.set("5", reqctrl.count_filter_sexe_type("Problème dû à la régularisation", "Homme"));
        boys.set("6", reqctrl.count_filter_sexe_type("Escroquerie", "Homme"));
        boys.set("7", reqctrl.count_filter_sexe_type("Discrimination à l'administration", "Homme"));
        boys.set("8", reqctrl.count_filter_sexe_type("Autre...", "Homme"));
        
        
        
        ChartSeries girls = new ChartSeries();
        girls.setLabel("Femmes");
        girls.set("1", reqctrl.count_filter_sexe_type("Acte raciste verbal", "Femme"));
        girls.set("2", reqctrl.count_filter_sexe_type("Acte raciste physique", "Femme"));
        girls.set("3", reqctrl.count_filter_sexe_type("Exploitation", "Femme"));
        girls.set("4", reqctrl.count_filter_sexe_type("Violence sexuelle", "Femme"));
        girls.set("5", reqctrl.count_filter_sexe_type("Problème dû à la régularisation", "Femme"));
        girls.set("6", reqctrl.count_filter_sexe_type("Escroquerie", "Femme"));
        girls.set("7", reqctrl.count_filter_sexe_type("Discrimination à l'administration", "Femme"));
        girls.set("8", reqctrl.count_filter_sexe_type("Autre...", "Femme"));
        model.addSeries(boys);
        model.addSeries(girls);

        return model;
    }

    private void createBarModels() {
        createBarModel();
        createHorizontalBarModel();
    }

    private void createBarModel() {
        barModel = initBarModel();

        barModel.setTitle("Total des réclamations filtrer par sexe");
        barModel.setLegendPosition("ne");

        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Sexe");

        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Réclamation");
        yAxis.setMin(0);
        yAxis.setMax(max);
    }

    private void createHorizontalBarModel() {
        horizontalBarModel = new HorizontalBarChartModel();
        horizontalBarModel.setSeriesColors("1e94d2, F781BE");
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Hommes");
        boys.set("janvier", 50);
        boys.set("février", 96);
        boys.set("mars", 44);
        boys.set("avril", 55);
        boys.set("mai", 61);
        boys.set("juin", 80);
        boys.set("juillet", 25);
        boys.set("août", 25);
        boys.set("septembre", 43);
        boys.set("octobre", 25);
        boys.set("novembre", 37);
        boys.set("décembre", 25);

        ChartSeries girls = new ChartSeries();
        girls.setLabel("Femmes");
        girls.set("janvier", 52);
        girls.set("février", 60);
        girls.set("mars", 82);
        girls.set("avril", 35);
        girls.set("mai", 66);
        girls.set("juin", 58);
        girls.set("juillet", 44);
        girls.set("août", 90);
        girls.set("septembre", 33);
        girls.set("octobre", 80);
        girls.set("novembre", 20);
        girls.set("décembre", 12);

        horizontalBarModel.addSeries(boys);
        horizontalBarModel.addSeries(girls);

        horizontalBarModel.setTitle("Total reclamations pour 2017");
        horizontalBarModel.setLegendPosition("e");
        horizontalBarModel.setStacked(true);

        Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
        xAxis.setLabel("Réclamation");
        xAxis.setMin(0);
        xAxis.setMax(100);

        Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
        yAxis.setLabel("Sexe");
    }

}
