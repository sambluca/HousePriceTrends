package application;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 * 
 * @author tramog01
 *Class that is the index of the application
 */
public class Index {
	public static PathToDB path = new PathToDB();
	/**
	 * On start calls the EnterPathDB method to make sure the user has to enter a path to their database before anything else
	 * @param args
	 */
	public static void main(String[] args) {
		EnterPathDB();
	}
	
	public static String searchTerm;
	private static ArrayList<HouseSale> houseSales = null;
	
	// This creates an empty table, with just the column names, that is seen before the user searches
	private static Object[] columnNames = {"Date", "Price", "No", "Road", "Postcode"};
	private static TableModel emptyTableModel = new DefaultTableModel(null, columnNames);
	private static JTable table = new JTable(emptyTableModel);
	
	// Creates a new window that the graph is opened in
	private static JFrame graphFrame = new JFrame("Trends graph!");
	private static Container graphCont = graphFrame.getContentPane();
	
	// The main window, is used for entering the databases path and the table view after that
	private static JFrame s = new JFrame("Please enter a path!");
	private static Container d = s.getContentPane();

	private static JPanel p = new JPanel(new GridBagLayout());
	private static JPanel sp = new JPanel(new GridBagLayout());
	private static JPanel fp = new JPanel(new GridBagLayout());


	private static JButton button = new JButton("Search");
	private static JButton pathButton = new JButton("Submit");
	private static JButton showGraph = new JButton("Graph");

	private static JTextField searchBar= new JTextField(12);
	private static JTextField houseNumberFilter= new JTextField(3);
	private static JTextField minPriceFilter= new JTextField(7);
	private static JTextField maxPriceFilter= new JTextField(7);

	private static ButtonGroup filters = new ButtonGroup();

	private static JRadioButton noFilters = new JRadioButton("Search Postcodes only"); 
	private static JRadioButton houseNo = new JRadioButton("House Number"); 
	private static JRadioButton maxPrice = new JRadioButton("Max Price"); 
	private static JRadioButton minPrice = new JRadioButton("Min Price"); 
	
	// Creates a scroll pane for the table as it means all the data is accessible 
	private static JScrollPane scrollPane = new JScrollPane(table);	
	
	/**
	 * Checks whether a string can be successfully converted to an integer
	 * @param string the string you want to check is a valid integer
	 * @return
	 */
	private static boolean isStringValidInteger(String string) {
	    try {
	        Integer.valueOf(string);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	/**
	 * On button click run a database search and update the table
	 * @throws SQLException
	 */
	private static void buttonAction() throws SQLException {
		String userInput = searchBar.getText();
		searchTerm = userInput;
		String filterChoice = filters.getSelection().getActionCommand();
		
		if(userInput.equals(null) || userInput.equals("")) {
			// If the input is null creates a pop up prompting the user to enter a postcode
			JOptionPane.showMessageDialog(d, "Please enter a postcode!");
		} else {
			houseSales = null;
			
			// Looks through the radio button filters and searches for the appropriate data accordingly
			if(filterChoice == "noFilters") {
				houseSales = HousesSearch.noFilterSearch(userInput);
			}
			
			if(isStringValidInteger(maxPriceFilter.getText()) && filterChoice =="maxPrice") {
				int maxPrice = Integer.parseInt(maxPriceFilter.getText());
				
				houseSales = HousesSearch.maxPriceSearch(userInput, maxPrice);
			}
			
			if(isStringValidInteger(minPriceFilter.getText()) && filterChoice =="minPrice") {
				int minPrice = Integer.parseInt(minPriceFilter.getText());
				
				houseSales = HousesSearch.minPriceSearch(userInput, minPrice);
			}
			
			if(filterChoice =="houseNo") {
				String houseNo = houseNumberFilter.getText();
				houseSales = HousesSearch.houseNoSearch(userInput, houseNo);
			}
	
			Object[][] rowData = null;
	
			if(houseSales == null || houseSales.size() == 0 ) {
				// If no data is returning a pop up will come up and prompt the user to try again
				// This also covers the scenario where the user chooses to filter but doesn't enter something to filter by
				JOptionPane.showMessageDialog(d, "No data found! Either we don't have this data or the postcode was incorrect.\nIf you chose to filter the results further please make sure you entered what you want to filter by.");
			} else {
				// When there is data build the tables rowData array
				rowData = new Object[houseSales.size()][5];
				for(int i = 0; i < houseSales.size(); i++) {
					rowData[i][0] = houseSales.get(i).getDate();
					rowData[i][1] = "£" + houseSales.get(i).getPrice();
					rowData[i][2] = houseSales.get(i).getPaon();
					rowData[i][3] = houseSales.get(i).getStreet();
					rowData[i][4] = houseSales.get(i).getPostcode();
				}
				// Enables the Graph button as there is now data that a graph can be made out of
				showGraph.setEnabled(true);
			}
			
			TableModel tableModel = new DefaultTableModel(rowData, columnNames)
					{
					// I don't want the user to edit the data in the table
						public boolean isCellEditable(int row, int column)
						{
							return false;
						}
					};
			// Updates the table with the new model
			table.setModel(tableModel);
		}
	}

	
	private static GridBagConstraints buttonCons() {
		GridBagConstraints bc = new GridBagConstraints();
		bc.gridx = 1;
		bc.gridy = 0;

		return bc;
	}
	private static GridBagConstraints searchCons() {
		GridBagConstraints sc = new GridBagConstraints();
		sc.gridy = 0;
		sc.gridx = 0;

		return sc;
	}
	private static GridBagConstraints panelCons() {
		GridBagConstraints pc = new GridBagConstraints();
		pc.fill = GridBagConstraints.HORIZONTAL;

		pc.anchor = GridBagConstraints.NORTH;

		return pc;
	}
	private static GridBagConstraints filtCons() {
		GridBagConstraints fc = new GridBagConstraints();
		fc.fill = GridBagConstraints.HORIZONTAL;
		fc.gridy = 2;
		fc.gridx = 0;

		return fc;
	}

	private static GridBagConstraints radioButtonCons(int yPos) {
		GridBagConstraints rc = new GridBagConstraints();
		rc.fill = GridBagConstraints.HORIZONTAL;
		rc.gridy = yPos;
		rc.gridx = 0;

		return rc;
	}
	
	private static GridBagConstraints layout(int yPos) {
		GridBagConstraints rc = new GridBagConstraints();
		rc.gridy = yPos;
		
		return rc;
	}
	
	private static GridBagConstraints filterInputCons(int yPos, int xPos) {
		GridBagConstraints rc = new GridBagConstraints();
		rc.gridy = yPos;
		rc.gridx = xPos;
		return rc;
	}




	/**
	 * The primary user interface, has the table, search bar and all filtering options on it
	 */
	private static void UserInterface() {
		d.setLayout(new GridBagLayout());
		
		// Sets action command for each radio button, this is how when the program searches for data it knows what to filter by
		maxPrice.setActionCommand("maxPrice");
		minPrice.setActionCommand("minPrice");
		houseNo.setActionCommand("houseNo");
		noFilters.setActionCommand("noFilters");
		
		// Adds all the radio buttons to the same button group
		filters.add(maxPrice);
		filters.add(minPrice);
		filters.add(houseNo);
		filters.add(noFilters);
		// Makes the starting selected button noFilter
		noFilters.setSelected(true);

		
		// Adds the search bar and search button to there own JPanel
		sp.add(searchBar, searchCons());
		sp.add(button, buttonCons());
		
		// Adds the filtering options to a different JPanel
		fp.add(new JLabel("Filter search further!"), filtCons());
		fp.add(noFilters, radioButtonCons(3));
		fp.add(houseNo, radioButtonCons(4));
		fp.add(houseNumberFilter, filterInputCons(4, 1));
		fp.add(maxPrice, radioButtonCons(5));
		fp.add(maxPriceFilter, filterInputCons(5, 1));
		fp.add(minPrice, radioButtonCons(6));
		fp.add(minPriceFilter, filterInputCons(6, 1));
		fp.add(showGraph,filterInputCons(7,0));
		showGraph.setEnabled(false);

		// Adds both these JPanels to the same JPanel
		p.add(sp, layout(0));
		p.add(fp, layout(1));
		
		// Adds the table and the search and filter panel to the main frame
		d.add(scrollPane);
		d.add(p, panelCons());
		
		// On button click run the buttonAction method
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buttonAction();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}			
			}
		});
	
		showGraph.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// New thread as otherwise the main application cannot run in the background, or when the graph window is closed
				new Thread(){
                    @Override
                    public void run() {
                        try {
                        	// When clicked remove the previous graph from the graph container
                        	graphCont.removeAll();
                        	// Calls the createGraph method which returns a graph and adds it to the container
            				graphCont.add(TrendsGraph.createGraph(houseSales));
            				graphFrame.setVisible(true);	
            				graphFrame.pack();
            				graphFrame.setResizable(false);
            				graphFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

				
			}
		});

		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s.pack();
		s.setResizable(false);
		s.setVisible(true);
	}
	
	/**
	 * This is what the user sees when they start the program, requires the user to enter a path to a database of their choice
	 */
	private static void EnterPathDB() {
		// Reuses the search bar as it doesn't need to be different
		sp.add(searchBar, searchCons());
		sp.add(pathButton, buttonCons());
		d.add(sp);
		
		// On button click
		pathButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String pathDB = searchBar.getText();
					// Clears the search bar in anticipation of it being reused
					searchBar.setText("");
					if(pathDB.equals(null) || pathDB.equals("")) {
						// Checks the user actually enters a path, if not prompts them to
						JOptionPane.showMessageDialog(d, "Please enter a path!");
					} else {
						// Sets the data bases path
						path.setPath(pathDB);
						// Removes everything from the frame
						sp.remove(searchBar);
						sp.remove(pathButton);
						d.remove(sp);
						// Changes the title of the frame
						s.setTitle("House Price Trends!");
						// Calls the main user interface
						UserInterface();						
					}
			}
		});
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s.pack();
		s.setResizable(false);
		s.setVisible(true);
	}
}
