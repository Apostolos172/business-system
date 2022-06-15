import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;

/**
 * Η κλάση της οποίας τα αντικείμενα που θα δημιουργηθούν θα αποτελούν την κυρίως γραφική διασύνδεση
 * με το χρήστη (GUI) από όπου θα έχει πρόσβαση στις υπόλοιπες λειτουργίες-καρτέλες-παράθυρα
 */
public class Main_GUI extends JFrame {
	private JPanel mainPanel,northPanel,southPanel,westPanel,centralPanel;
	//fonts
	Font serifFontBig,serifFont,serifFontSmall,sansSerifFontSmall;
	//northPanel
	private ImageIcon image;
	private JLabel imageLabel;
	//southPanel
	private JLabel footerLabel1,footerLabel2;
	//westPanel
	private JLabel infoLabel1,infoLabel2,infoLabel3,infoLabel4;
	private JButton writeInfoInFile, visitSite;
	//centralPanel
	private JButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8;
	
	private Main_GUI main_GUI = this;
	private JButton visitDocumentation;
	
	public Main_GUI()  {
		super("Χρώματα Βαδραχάνης");
		makeFrame();
	}
	
	public void makeFrame() {
		
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
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		infoLabel1 = new JLabel("Επωνυμία επιχείρησης: Χρώματα Βαδραχάνης");
		infoLabel1.setFont(serifFont);
		infoLabel1.setForeground(Color.white);
		infoLabel2 = new JLabel("Τηλ. 24670 29159");
		infoLabel2.setFont(serifFont);
		infoLabel2.setForeground(Color.white);
		infoLabel3 = new JLabel("Διεύθυνση: Μεγάλου Αλεξάνδρου 39");
		infoLabel3.setFont(serifFont);
		infoLabel3.setForeground(Color.white);
		infoLabel4 = new JLabel("Περιοχή: Καστοριά, 52100");
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
		
		visitDocumentation = new JButton("<html>Δες την τεκμηρίωση του λογισμικού</html>");
		visitDocumentation.setFont(sansSerifFontSmall);
		visitDocumentation.setBackground(Color.magenta);
		
		visitDocumentationListener visitDocumentationlistener = new visitDocumentationListener();
		visitDocumentation.addActionListener(visitDocumentationlistener);
		 
		westPanel.add(infoLabel1);
		westPanel.add(infoLabel2);
		westPanel.add(infoLabel3);
		westPanel.add(infoLabel4);
		westPanel.add(new JLabel("\n"));
		westPanel.add(writeInfoInFile);
		westPanel.add(new JLabel("\n"));
		westPanel.add(visitSite);
		westPanel.add(new JLabel("\n"));
		westPanel.add(visitDocumentation);
		
		westPanel.setBackground(Color.gray);
        
        mainPanel.add(westPanel,BorderLayout.WEST);
	}
	
	public void makeCentralPanel() 
	{
		centralPanel = new JPanel();
		GUI.setPadding(centralPanel);
		centralPanel.setLayout(new GridLayout(4,2,10,10));
		
		btn1 = new JButton("Τιμολόγηση");
		btn1.setFont(serifFontBig);
		btn1Listener btn1listener = new btn1Listener();
		btn1.addActionListener(btn1listener);
		
		btn2 = new JButton("Τιμολόγιο");
		btn2.setFont(serifFontBig);
		btn2Listener btn2listener = new btn2Listener();
		btn2.addActionListener(btn2listener);
		
		btn3 = new JButton("Τιμοκατάλογος");
		btn3.setFont(serifFontBig);
		btn3Listener btn3listener = new btn3Listener();
		btn3.addActionListener(btn3listener);
		
		btn4 = new JButton("Προμηθευτές");
		btn4.setFont(serifFontBig);
		btn4Listener btn4listener = new btn4Listener();
		btn4.addActionListener(btn4listener);
		
		btn5 = new JButton("Αποθέματα");
		btn5.setFont(serifFontBig);
		
		btn6 = new JButton("Γενικά");
		btn6.setFont(serifFontBig);
		btn6Listener btn6listener = new btn6Listener();
		btn6.addActionListener(btn6listener);
		
		btn7 = new JButton("Έσοδα-Έξοδα");
		btn7.setFont(serifFontBig);
		btn7Listener btn7listener = new btn7Listener();
		btn7.addActionListener(btn7listener);
		
		btn8 = new JButton("Αριθμομηχανή");
		btn8.setFont(serifFontBig);
		btn8Listener btn8listener = new btn8Listener();
		btn8.addActionListener(btn8listener);
		 
		centralPanel.add(btn1);
        centralPanel.add(btn2);
        centralPanel.add(btn3);
        centralPanel.add(btn4);
        centralPanel.add(btn5);
        centralPanel.add(btn6);
        centralPanel.add(btn7);
        centralPanel.add(btn8);
		
        centralPanel.setBackground(Color.white);
        
        mainPanel.add(centralPanel,BorderLayout.CENTER);
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
	
	class visitDocumentationListener implements ActionListener 
	{
		@Override
		public void actionPerformed(ActionEvent ev) {
		    if (Desktop.isDesktopSupported()) {
		        try {
		        	Desktop.getDesktop().browse(new URI("file:///C:/Users/user/Desktop/applications/My_System/documentation/allclasses-index.html"));
			        } catch (IOException e) { 
			        	e.printStackTrace();
			        } catch (URISyntaxException e) {
						e.printStackTrace();
					}
		    	} else { 
		    		
		    	}
		}	
	}
	
	public void makeMyFonts()
	{
		serifFontBig = new Font("serif", Font.BOLD, 40);
		serifFont = new Font("serif", Font.BOLD, 22);
		serifFontSmall = new Font("serif", Font.ITALIC|Font.BOLD, 17);
		sansSerifFontSmall = new Font("Sans Serif", Font.ITALIC|Font.BOLD, 17);
	}
	
	class writeInfoInFilebtnListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			FileWriterLocal.writeInfo();
			GUI.showConfirmationWindow("Το αρχείο βρίσκεται στην επιφάνεια εργασίας, δείτε το.",500);
		}
		
	}
	
	class btn1Listener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			new Costs_GUI(main_GUI);
		}
	}
	
	class btn2Listener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			new Invoice_GUI(main_GUI);
		}
	}
	
	class btn3Listener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			try {
				new Prices_GUI(main_GUI);
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	class btn4Listener implements ActionListener {
		
		public void actionPerformed(ActionEvent ev) {
			if (Desktop.isDesktopSupported()) {
		        try {
		        	Desktop.getDesktop().browse(new URI("https://e-invoicing.retail-link.gr/"));
			        } catch (IOException e) { 
			        	e.printStackTrace();
			        } catch (URISyntaxException e) {
						e.printStackTrace();
					}
		    	} else { 
		    		
		    	}
		}
	}
	
	class btn5Listener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	
	class btn6Listener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			new General_GUI(main_GUI);
		}
		
	}
	
	class btn7Listener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {

		}
		
	}
	
	class btn8Listener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {

		}
		
	}

}

