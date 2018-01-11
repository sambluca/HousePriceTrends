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

public class Index {
	public static PathToDB path = new PathToDB();
	public static void main(String[] args) {
		EnterPathDB();
	}
	
	private static ArrayList<House> housesArray = null;
	
	private static Object[] columnNames = {"Date", "Price", "No", "Road", "Postcode"};
	private static TableModel emptyTableModel = new DefaultTableModel(null, columnNames);
	private static JTable table = new JTable(emptyTableModel);
	
	
	private static JFrame graphFrame = new JFrame("Trends graph!");
	private static Container graphCont = graphFrame.getContentPane();
	
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

	private static JScrollPane scrollPane = new JScrollPane(table);	
	
	private static boolean isStringValidInteger(String string) {
	    try {
	        Integer.valueOf(string);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	private static void buttonAction() throws SQLException {
		String userInput = searchBar.getText();
		String filterChoice = filters.getSelection().getActionCommand();
		
		if(userInput.equals(null) || userInput.equals("")) {
			JOptionPane.showMessageDialog(d, "Please enter a postcode!");
		} else {
			housesArray = null;
	
			if(filterChoice == "noFilters") {
				housesArray = HousesSearch.noFilterSearch(userInput);
			}
			
			if(isStringValidInteger(maxPriceFilter.getText()) && filterChoice =="maxPrice") {
				int maxPrice = Integer.parseInt(maxPriceFilter.getText());
				
				housesArray = HousesSearch.maxPriceSearch(userInput, maxPrice);
			}
			
			if(isStringValidInteger(minPriceFilter.getText()) && filterChoice =="minPrice") {
				int minPrice = Integer.parseInt(minPriceFilter.getText());
				
				housesArray = HousesSearch.minPriceSearch(userInput, minPrice);
			}
			
			if(filterChoice =="houseNo") {
				String houseNo = houseNumberFilter.getText();
				housesArray = HousesSearch.houseNoSearch(userInput, houseNo);
			}
	
			Object[][] rowData = null;
	
			if(housesArray == null || housesArray.size() == 0 ) {
				JOptionPane.showMessageDialog(d, "No data found! Either we don't have this data or the postcode was incorrect.\nIf you chose to filter the results further please make sure you entered what you want to filter by.");
			} else {
				rowData = new Object[housesArray.size()][5];
				for(int i = 0; i < housesArray.size(); i++) {
					rowData[i][0] = housesArray.get(i).getDate();
					rowData[i][1] = "£" + housesArray.get(i).getPrice();
					rowData[i][2] = housesArray.get(i).getPaon();
					rowData[i][3] = housesArray.get(i).getStreet();
					rowData[i][4] = housesArray.get(i).getPostcode();
				}
				showGraph.setEnabled(true);
			}
			
			TableModel tableModel = new DefaultTableModel(rowData, columnNames)
					{
						public boolean isCellEditable(int row, int column)
						{
							return false;
						}
					};
			
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






	private static void UserInterface() {
		d.setLayout(new GridBagLayout());
		
		maxPrice.setActionCommand("maxPrice");
		minPrice.setActionCommand("minPrice");
		houseNo.setActionCommand("houseNo");
		noFilters.setActionCommand("noFilters");

		filters.add(maxPrice);
		filters.add(minPrice);
		filters.add(houseNo);
		filters.add(noFilters);
		noFilters.setSelected(true);

		
		sp.add(searchBar, searchCons());
		sp.add(button, buttonCons());

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

		p.add(sp, layout(0));
		p.add(fp, layout(1));

		d.add(scrollPane);
		d.add(p, panelCons());
		
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
				new Thread(){
                    @Override
                    public void run() {
                        try {
                        	graphCont.removeAll();
            				graphCont.add(TrendsGraph.createGraph(housesArray));
            				graphFrame.setVisible(true);	
            				graphFrame.pack();
            				graphFrame.setResizable(false);
            				graphFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        } catch (Exception e) {
                            //what to do when it fails
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
	
	private static void EnterPathDB() {
		sp.add(searchBar, searchCons());
		sp.add(pathButton, buttonCons());
		d.add(sp);
		pathButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String pathDB = searchBar.getText();
					searchBar.setText("");
					if(pathDB.equals(null) || pathDB.equals("")) {
						JOptionPane.showMessageDialog(d, "Please enter a path!");
					} else {
						path.setPath(pathDB);
						sp.remove(searchBar);
						sp.remove(pathButton);
						d.remove(sp);
						s.setTitle("House Price Trends!");
						UserInterface();						
					}
			}
		});
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s.pack();
		s.setVisible(true);
	}
}
