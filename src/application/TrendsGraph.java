package application;

import java.text.ParseException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class TrendsGraph {
	public static ChartPanel createGraph(ArrayList<House> houses) throws ParseException {
		XYSeries series = new XYSeries("Price Trends!");
	    XYSeriesCollection dataset = new XYSeriesCollection();

		for(House house : houses) {
			series.add(house.getYear(), house.getPrice());;
			house.getYear();
		}
		
		
	    dataset.addSeries(series);

		   JFreeChart chart = ChartFactory.createScatterPlot("Price trends", "Date", "Price (£)", dataset);
		   ChartPanel CP = new ChartPanel(chart);

		return CP;

	}
}
