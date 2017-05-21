package BOs.SalesReporting;
import dao.DatabaseDAO;
import Entities.Sale;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.lightcouch.CouchDbClient;

import java.util.List;

/**
 * Created by Taha-PC on 5/21/2017.
 */
public class TimeOfDaySales extends ApplicationFrame {

    public TimeOfDaySales(String applicationTitle , String chartTitle ) {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Time of Day",
                "Number of Orders",
                createDataset(),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 1000, 367 ) );
        setContentPane( chartPanel );
    }

    private DefaultCategoryDataset createDataset( ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
        int data[] = new int[6];
        DatabaseDAO appDatabase = new DatabaseDAO();
        CouchDbClient salesCafe1 = new CouchDbClient("salescafe1", true, "http", "127.0.0.1", 5984, "nash","1234");
        List<Sale> sales = appDatabase.getAllSales(salesCafe1);
        for(Sale sale:sales){
            int time = sale.getTimeOfSale().getHour();
            if(time<=4)
                data[0]++;
            else if (time <=8)
                data[1]++;
            else if (time <=12)
                data[2]++;
            else if (time <=16)
                data[3]++;
            else if (time <=20)
                data[4]++;
            else
                data[5]++;

        }
        dataset.addValue( data[0] , "orders" , "Midnight - 04:00 AM" );
        dataset.addValue( data[1] , "orders" , "04:00 AM - 08:00 AM" );
        dataset.addValue( data[2] , "orders" ,  "08:00 AM - noon" );
        dataset.addValue( data[3] , "orders" , "noon - 04:00 PM" );
        dataset.addValue( data[4], "orders" , "04:00 PM - 08:00 PM" );
        dataset.addValue( data[5] , "orders" , "08:00 PM - Midnight" );
        return dataset;
    }


}
