package com.ftdes.migrights.managedBean;
 
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import org.primefaces.model.chart.PieChartModel;
 
@ManagedBean
@SessionScoped
public class ChartViewPie implements Serializable {
 
    private PieChartModel pieModel1;
    private PieChartModel pieModel2;
    
    @Inject
    private ReclamationController reqctrl;
    
  
 
    public PieChartModel getPieModel1() {
        createPieModel1();
        return pieModel1;
    }
     
    public PieChartModel getPieModel2() {
        createPieModel2();
        return pieModel2;
    }
     
  
 
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
         
        pieModel1.set("Brand 1", 540);
        pieModel1.set("Brand 2", 325);
        pieModel1.set("Brand 3", 702);
        pieModel1.set("Brand 4", 421);
         
        pieModel1.setTitle("Simple Pie");
        pieModel1.setLegendPosition("w");
    }
     
    private void createPieModel2() {
        pieModel2 = new PieChartModel();
         
        pieModel2.set("Moin de 18", reqctrl.count_filter_age(0, 17));
        pieModel2.set("Entre 18-35", reqctrl.count_filter_age(18, 35));
        pieModel2.set("Entre 36-60", reqctrl.count_filter_age(36, 60));
        pieModel2.set("Plus de 60", reqctrl.count_filter_age(60, 200));
         
        pieModel2.setTitle("Custom Pie");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }
     
}