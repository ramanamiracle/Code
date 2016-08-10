import java.awt.Color; 
import java.awt.BasicStroke; 

import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

public class JfreeChart extends ApplicationFrame 
{
   public JfreeChart( String applicationTitle, String chartTitle, XYDataset dataSet )
   {
      super(applicationTitle);
      
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Time(MS)" ,
         "Hits" ,
         dataSet ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 800 , 600 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
     
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.GREEN );
      renderer.setSeriesPaint( 2 , Color.YELLOW );
      
      renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 1.0f ) );
      renderer.setSeriesStroke( 2 , new BasicStroke( 1.0f ) );
      
      /*
      plot.setBackgroundPaint(Color.lightGray);
      plot.setDomainGridlinePaint(Color.white);
      plot.setRangeGridlinePaint(Color.white);
      plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
      plot.setDomainCrosshairVisible(true);
      plot.setRangeCrosshairVisible(true);
      */
      
      DateAxis xAxis = new DateAxis("Time");
      
      //xAxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
     
      plot.setDomainAxis(xAxis);
      plot.setRenderer( renderer ); 
      
      setContentPane( chartPanel ); 
   }
 
}