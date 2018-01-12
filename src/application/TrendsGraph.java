package application;

import java.text.ParseException;
import java.util.ArrayList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * 
 * @author tramog01
 *Class for a sales price trends graph
 */
public class TrendsGraph {
	/**
	 * Takes in an array of house sales and builds a Scatterplot graph from the array list
	 * @param houseSales an array list of house sales that a graph will be built from
	 * @return A chart panel that contains the built graph
	 * @throws ParseException
	 */
	public static ChartPanel createGraph(ArrayList<HouseSale> houseSales) throws ParseException {
		// Sale denotes what each point on the graph represents
		XYSeries series = new XYSeries("Sale");
		// I used a XYSeries instead of a TimeSeries because a TimeSeriesCollection doesn't allow for multiple entries on the x axis
		// and more than one house sale could be done on the same day making it unsuitable
	    XYSeriesCollection salesData = new XYSeriesCollection();
	    
		for(HouseSale house : houseSales) {
			// Uses the year of sale for the graphs x axis and the price for the y axis
			series.add(house.getYear(), house.getPrice());;
			house.getYear();
		}
		
	    salesData.addSeries(series);
	    // Creates a graph with the title SEARCHTERM Price Trends, gets the search term from the index
	    // Has a X Axis title of Date (Year)
	    // Has a Y Axis title of Price (£)
	    JFreeChart chart = ChartFactory.createScatterPlot(Index.searchTerm + " Price trends", "Date (Year)", "Price (£)", salesData);
	    
	    ChartPanel graph = new ChartPanel(chart);

		return graph;

	}
}
