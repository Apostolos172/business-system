import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class PricesBig_GUI extends JFrame {
	private JPanel mainPanel, centralPanel,centralCentralPanel;
	//fonts
	Font serifFontBig,serifFont,serifFontSmall,sansSerifFontSmall,sansserifFontMedium;
	//centralPanel
	private JTextArea areaProducts, areaPrices, areaInfo;
	private JScrollPane paneProducts, panePrices, paneInfo;
	//centralNorthPanel
	private JButton search;
	private JTextField product;
	//centralSouthPanel
	private JLabel productFoundLabel;
	private JTextField productFoundField;
	
	private StockList menu;
	private JButton deletebtn;
	
	public PricesBig_GUI() throws ClassNotFoundException, SQLException  {
		super("Χρώματα");
		this.createTheStockList();
		makeFrame();
	}
	
	public void createTheStockList() throws ClassNotFoundException, SQLException
	{
		 this.menu = new StockList();
	}
	
	public void makeFrame() throws SQLException {
		
		makeMyFonts();
		
		mainPanel = new JPanel(new BorderLayout(20,20));

		makeCentralPanel();
		
		mainPanel.setBackground(Color.green);
		mainPanel.setBackground(Color.black);
		
        this.setContentPane(mainPanel);
		
        GUI.setPadding(mainPanel);
		GUI.setSizeOfTheWindow(this);
		
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void makeCentralPanel() throws SQLException 
	{
		centralPanel = new JPanel(new BorderLayout(10,10));
		GUI.setPadding(centralPanel);
		
		JPanel centralNorthPanel = new JPanel();
		GUI.setPadding(centralNorthPanel);
		JPanel centralSouthPanel = new JPanel();
		GUI.setPadding(centralSouthPanel);

		centralCentralPanel = new JPanel();
		GUI.setPadding(centralCentralPanel);
		centralCentralPanel.setLayout(new GridLayout(1,3,10,10));
        
		//centralNorthPanel
		search = new JButton("Αναζήτησε προϊόν");
		search.setFont(serifFont);
		searchListener searchlistener = new searchListener();
		search.addActionListener(searchlistener);
		
		product = new JTextField(40);
		GUI.setLeftPaddingAtJTextField(product);
		product.setText("superPlastic");
		product.setFont(serifFont);
		
		searchFocusListener searchFocuslistener = new searchFocusListener();
		product.addFocusListener(searchFocuslistener);
		
		searchKeyListener searchKeylistener = new searchKeyListener();
		product.addKeyListener(searchKeylistener);
		
		centralNorthPanel.add(search);
		centralNorthPanel.add(product);
		
		//centralCentralPanel
        areaProducts = new JTextArea();
        areaProducts.setFont(sansserifFontMedium);
        areaProducts.append("Προϊόντα \n\n");
        
        for(Product product: menu.getProducts())
            areaProducts.append(product.getName() + "\n");
        
        areaProducts.setEditable(false);
        GUI.setPaddingAtJTextArea(areaProducts);
        paneProducts = new JScrollPane(areaProducts);
        
        //---------------------
        
        areaInfo = new JTextArea();
        areaInfo.setFont(sansserifFontMedium);
        areaInfo.append("Πληροφορίες \n\n");
        
        for(Product product: menu.getProducts())
        	areaInfo.append(product.getDescription() + "\n");
        
        areaInfo.setEditable(false);
        GUI.setPaddingAtJTextArea(areaInfo);
        paneInfo = new JScrollPane(areaInfo);
        
        //---------------------
        
        areaPrices = new JTextArea();
        areaPrices.setFont(sansserifFontMedium);
        areaPrices.append("Τιμές \n\n");
        
        for(Product product: menu.getProducts())
        	areaPrices.append(product.getFinalPrice() + " ευρώ \n");
        
        areaPrices.setEditable(false);
        GUI.setPaddingAtJTextArea(areaPrices);
        panePrices = new JScrollPane(areaPrices);
 
        centralCentralPanel.add(paneProducts);
        centralCentralPanel.add(panePrices);
        centralCentralPanel.add(paneInfo);
        
        //centralSouthPanel
        
		productFoundLabel = new JLabel("Το προϊόν");
		productFoundLabel.setFont(serifFont);
		
		productFoundField = new JTextField(40);
		GUI.setLeftPaddingAtJTextField(productFoundField);
		productFoundField.setFont(serifFont);
		productFoundField.setEditable(false);
		productFoundField.setBackground(Color.green);
		
		deletebtn = new JButton("Διέγραψέ το");
		deletebtnListener deletebtnlistener = new deletebtnListener();
		deletebtn.addActionListener(deletebtnlistener);
		
		centralSouthPanel.add(productFoundLabel);
		centralSouthPanel.add(productFoundField);
		centralSouthPanel.add(deletebtn);
		
		//additions to centralPanel
        
        centralPanel.add(centralNorthPanel,BorderLayout.NORTH);
        centralPanel.add(centralCentralPanel,BorderLayout.CENTER);
        centralPanel.add(centralSouthPanel,BorderLayout.SOUTH);
        
        centralPanel.setBackground(Color.LIGHT_GRAY);
        
        //additions to mainPanel
        mainPanel.add(centralPanel,BorderLayout.CENTER);
	}
	
	class deletebtnListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				menu.deleteProductFromStockList(product.getText());
				GUI.showConfirmationWindow("Το προϊόν αφαιρέθηκε από τον κατάλογο", 400);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	
	class searchFocusListener implements FocusListener
	{

		@Override
		public void focusGained(FocusEvent arg0) {
			// TODO Auto-generated method stub
			productFoundField.setText("");
			product.setText("");
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class searchKeyListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if(e.getKeyCode()==KeyEvent.VK_ENTER)
			{
				search.doClick();;
			}
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class searchListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			String nameOfProduct = product.getText();
			String info;
			try {
				info = menu.getInfoOfTheProductWithThisName("'" + nameOfProduct + "'");
				productFoundField.setText(info);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void makeMyFonts()
	{
		serifFontBig = new Font("serif", Font.BOLD, 30);
		sansserifFontMedium = new Font("Sans Serif", Font.PLAIN, 30);
		serifFont = new Font("serif", Font.BOLD, 22);
		serifFontSmall = new Font("serif", Font.ITALIC|Font.BOLD, 17);
		sansSerifFontSmall = new Font("Sans Serif", Font.ITALIC|Font.BOLD, 17);
	}

}
