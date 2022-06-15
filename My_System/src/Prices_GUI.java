import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

public class Prices_GUI extends JFrame {
	private JPanel mainPanel,northPanel,southPanel,westPanel,centralPanel,centralCentralPanel;
	//fonts
	Font serifFontBig,serifFont,serifFontSmall,sansSerifFontSmall,sansserifFontMedium;
	//northPanel
	private ImageIcon image;
	private JLabel imageLabel;
	//southPanel
	private JLabel footerLabel1,footerLabel2;
	//westPanel
	private JLabel infoLabel1,infoLabel2,infoLabel3,infoLabel4;
	private JButton writeInfoInFile, visitSite, btnret;
	//centralPanel
	private JTextArea areaProducts, areaPrices, areaInfo;
	private JScrollPane paneProducts, panePrices, paneInfo;
	//centralNorthPanel
	private JButton search, fullScreenButton;
	private JTextField product;
	//centralSouthPanel
	private JLabel productFoundLabel;
	private JTextField productFoundField;
	
	private StockList menu;
	private Main_GUI main_GUI;
	private JButton deletebtn;
	
	public Prices_GUI(Main_GUI main_GUI) throws ClassNotFoundException, SQLException  {
		super("Χρώματα");
		this.main_GUI = main_GUI;
		main_GUI.setVisible(false);
		this.createTheStockList();
		makeFrame();
	}
	
	public void createTheStockList() throws ClassNotFoundException
	{
		try {
			this.menu = new StockList();
		} catch(SQLException e) {
			new Confirmation_GUI("Δεν μπορεί να δημιουγηθεί σύνδεση με την βάση δεδομένων");
		} finally {
			setVisible(false);
			dispose(); 
			main_GUI.setVisible(true);
		}
		
	}
	
	public void makeFrame() throws SQLException {
		
		makeMyFonts();
		
		mainPanel = new JPanel(new BorderLayout(20,20));
		
		makeNorthPanel();
		makeSouthPanel();
		makeWestPanel();
		makeCentralPanel();
		
		mainPanel.setBackground(Color.green);
		mainPanel.setBackground(Color.black);
		
        this.setContentPane(mainPanel);
		
        GUI.setPadding(mainPanel);
		GUI.setSizeOfTheWindow(this);
		
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void makeNorthPanel()
	{
		northPanel = new JPanel();
		GUI.setPadding(northPanel);
		
		image = new ImageIcon("logo.png");
        imageLabel = new JLabel(image);
        northPanel.add(imageLabel);
        
        northPanel.setBackground(Color.cyan);
        
        mainPanel.add(northPanel,BorderLayout.NORTH);
	}
	
	public void makeSouthPanel()
	{
		southPanel = new JPanel();
		GUI.setPadding(southPanel);
		southPanel.setLayout(new BoxLayout(southPanel,BoxLayout.Y_AXIS));
		southPanel.setAlignmentY(CENTER_ALIGNMENT);
		southPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		footerLabel1 = new JLabel("Designed by Tolis' s group");
		footerLabel1.setFont(serifFontSmall);
		footerLabel2 = new JLabel("Copyright 2020");
		footerLabel2.setFont(serifFontSmall);
		 
        southPanel.add(footerLabel1);
        southPanel.add(footerLabel2);
		
        southPanel.setBackground(Color.cyan);
        
        mainPanel.add(southPanel,BorderLayout.SOUTH);
	}
	
	public void makeWestPanel()
	{
		westPanel = new JPanel();
		GUI.setPadding(westPanel);
		westPanel.setLayout(new BoxLayout(westPanel,BoxLayout.Y_AXIS));

		infoLabel1 = new JLabel("Επωνυμία επιχείρησης: Χρώματα");
		infoLabel1.setFont(serifFont);
		infoLabel1.setForeground(Color.white);
		infoLabel2 = new JLabel("Τηλ. 24670 24670");
		infoLabel2.setFont(serifFont);
		infoLabel2.setForeground(Color.white);
		infoLabel3 = new JLabel("Διεύθυνση: τάδε");
		infoLabel3.setFont(serifFont);
		infoLabel3.setForeground(Color.white);
		infoLabel4 = new JLabel("Περιοχή: Θεσσαλονίκη, 11111");
		infoLabel4.setFont(serifFont);
		infoLabel4.setForeground(Color.white);
		writeInfoInFile = new JButton("<html>Αποθήκευσε τις πληροφορίες σε <br>αρχείο στην επιφάνεια εργασίας</html>");
		writeInfoInFile.setFont(sansSerifFontSmall);
		writeInfoInFile.setBackground(Color.green);
		
		writeInfoInFilebtnListener writeInfoInFilebtnlistener = new writeInfoInFilebtnListener();
		writeInfoInFile.addActionListener(writeInfoInFilebtnlistener);
		
		visitSite = new JButton("<html>Επισκέψου την ιστοσελίδα</html>");
		visitSite.setFont(sansSerifFontSmall);
		visitSite.setBackground(Color.magenta);
		
		visitSiteListener visitSitelistener = new visitSiteListener();
		visitSite.addActionListener(visitSitelistener);
		
		btnret = new JButton("Επιστροφή στην αρχική καρτέλα");
		btnret.setFont(sansSerifFontSmall);
		btnReturnListener btn1listener = new btnReturnListener();
		btnret.addActionListener(btn1listener);
		
		westPanel.add(infoLabel1);
		westPanel.add(infoLabel2);
		westPanel.add(infoLabel3);
		westPanel.add(infoLabel4);
		westPanel.add(new JLabel("\n"));
		westPanel.add(writeInfoInFile);
		westPanel.add(new JLabel("\n"));
		westPanel.add(visitSite);
		westPanel.add(new JLabel("\n"));
		westPanel.add(btnret);
		
		westPanel.setBackground(Color.gray);
        
        mainPanel.add(westPanel,BorderLayout.WEST);
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
		
		product = new JTextField(30);
		GUI.setLeftPaddingAtJTextField(product);
		product.setText("superPlastic");
		product.setFont(serifFont);
		searchFocusListener searchFocuslistener = new searchFocusListener();
		product.addFocusListener(searchFocuslistener);
		searchKeyListener searchKeylistener = new searchKeyListener();
		product.addKeyListener(searchKeylistener);
		
		fullScreenButton = new JButton("Πλήρης Οθονή");
		fullScreenButton.setFont(serifFont);
		fullScreenButtonListener fullScreenButtonlistener = new fullScreenButtonListener();
		fullScreenButton.addActionListener(fullScreenButtonlistener);
		
		centralNorthPanel.add(search);
		centralNorthPanel.add(product);
		centralNorthPanel.add(fullScreenButton);
		
		//centralCentralPanel
		int count=0;
		
        areaProducts = new JTextArea();
        areaProducts.setFont(sansserifFontMedium);
        areaProducts.append("Προϊόντα \n\n");
        
        for(Product product: menu.getProducts())
        {
        	count++;
        	areaProducts.append(count + ". " + product.getName() + "\n");
        }
        
        areaProducts.setEditable(false);
        GUI.setPaddingAtJTextArea(areaProducts);
        paneProducts = new JScrollPane(areaProducts);
        
        //---------------------
        count=0;
        
        areaInfo = new JTextArea();
        areaInfo.setFont(sansserifFontMedium);
        areaInfo.append("Πληροφορίες \n\n");
        
        for(Product product: menu.getProducts())
        {
        	count++;
        	areaInfo.append(count + ". " + product.getDescription() + "\n");
        }
        
        areaInfo.setEditable(false);
        GUI.setPaddingAtJTextArea(areaInfo);
        paneInfo = new JScrollPane(areaInfo);
        
        //---------------------
        count=0;
        
        areaPrices = new JTextArea();
        areaPrices.setFont(sansserifFontMedium);
        areaPrices.append("Τιμές \n\n");
        
        for(Product product: menu.getProducts())
        {
        	count++;
        	areaPrices.append(count + ". " + product.getFinalPrice() + " ευρώ \n");
        }
        
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
			AreYouSure_GUI areYouSure = new AreYouSure_GUI("Σίγουρα θέλεις να διαγραφεί το προϊόν;",product.getText());
			areYouSure.setLocation(670, 500);
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
	
	class fullScreenButtonListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			try {
				new PricesBig_GUI();
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}	
	}
	
	class writeInfoInFilebtnListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			FileWriterLocal.writeInfo();
			GUI.showConfirmationWindow("Το αρχείο βρίσκεται στην επιφάνεια εργασίας, δείτε το.", 670);
		}
		
	}
	
	class visitSiteListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent ev) {
		    if (Desktop.isDesktopSupported()) {
		        try {
		        	Desktop.getDesktop().browse(new URI("http://19172.byethost11.com"));
			        } catch (IOException e) { 
			        	e.printStackTrace();
			        } catch (URISyntaxException e) {
						e.printStackTrace();
					}
		    	} else { 
		    		
		    	}
		}	
	}
	
	class btnReturnListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
			dispose(); 
			main_GUI.setVisible(true);
			
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
