package BOs.SalesReporting;
import javax.swing.JFrame;

import dao.DatabaseDAO;
import Entities.Drink;
import Entities.Sale;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.lightcouch.CouchDbClient;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;


public class WeeklySalesCount extends JFrame {

    private static final long serialVersionUID = 1L;

    public WeeklySalesCount(String applicationTitle, String chartTitle) {
        super(applicationTitle);
        // This will create the dataset
        PieDataset dataset = createDataset();
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);

    }

    /**
     * Creates a sample dataset
     */
    private  PieDataset createDataset() {
        DefaultPieDataset result = new DefaultPieDataset();
        int itemcount[] = new int[8];




        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.AM_PM,Calendar.AM);
        //System.out.println(dateFormat.format(cal.getTime()));

        long diff = 0;
        DatabaseDAO appDatabase = new DatabaseDAO();
        CouchDbClient salesCafe1 = new CouchDbClient("salescafe1", true, "http", "127.0.0.1", 5984, "nash","1234");
        List<Sale> sales = appDatabase.getAllSales(salesCafe1);
        for(Sale sale:sales){


            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR,sale.getTimeOfSale().getYear());
            c.set(Calendar.MONTH,sale.getTimeOfSale().getMonth()-1);
            c.set(Calendar.DAY_OF_MONTH,sale.getTimeOfSale().getDay());
            c.set(Calendar.HOUR,sale.getTimeOfSale().getHour());
            c.set(Calendar.MINUTE,sale.getTimeOfSale().getMinute());
            System.out.println(c.getTime().toString());
            Date d2 = c.getTime();
            diff = cal.getTime().getTime() - d2.getTime();
            if(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) <=7){
                itemcount[d2.getDay()] ++;
            }
            else
                System.out.println(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));

        }

        result.setValue("Monday", itemcount[1]);
        result.setValue("Tuesday", itemcount[2]);
        result.setValue("Wednesday", itemcount[3]);
        result.setValue("Thursday", itemcount[4]);
        result.setValue("Friday", itemcount[5]);
        result.setValue("Saturday", itemcount[6]);
        result.setValue("Sunday", itemcount[0]);


        return result;

    }

    /**
     * Creates a chart
     */
    private JFreeChart createChart(PieDataset dataset, String title) {

        JFreeChart chart = ChartFactory.createPieChart3D(
                title,                  // chart title
                dataset,                // data
                true,                   // include legend
                true,
                false
        );

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        return chart;

    }
}

