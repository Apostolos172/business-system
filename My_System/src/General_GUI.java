import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.html.dom.HTMLBuilder;
import org.xml.sax.SAXException;

/**
 * Η κλάση της οποίας τα αντικείμενα που θα δημιουργηθούν θα αποτελούν μια ενημερωτική, απλή 
 * καρτέλα για τα περιεχόμενα του συστήματος, οι λεπτομέρειες φαίνονται στην επίσημη τεκμηρίωση, 
 * εδώ.
 */
public class General_GUI extends JFrame {
	private JPanel mainPanel,northPanel,southPanel,westPanel,centralPanel;
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
	private JLabel labelHtml;
	private JTextArea area;
	
	private Main_GUI main_GUI;
	
	public General_GUI(Main_GUI main_GUI)  {
		super("Χρώματα Βαδραχάνης");
		this.main_GUI = main_GUI;
		main_GUI.setVisible(false);
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
	}

	public void makeHtmlPage()
	{
		HTMLBuilder html = new HTMLBuilder();
		
		try {
			html.startDocument();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			html.endDocument();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public void makeCentralPanel() 
	{
		centralPanel = new JPanel();
		GUI.setPadding(centralPanel);
		centralPanel.setLayout(new GridLayout(2,1,10,10));
        
        labelHtml = new JLabel("<html>"
        		+ "<p><strong>Περιεχόμενα εφαρμογής</strong></p>"
        		+ "<ol>"
        		+ "<li>Αρχική σελίδα</li>"
        		+ "<li>Κοστολόγηση</li>"
        		+ "<li>Τιμολόγιο</li>"
        		+ "<li>Τιμοκατάλογος</li>"
        		+ "<li>Προμηθευτές</li>"
        		+ "<li>Αποθέματα</li>"
        		+ "<li>Γενικά</li>"
        		+ "</ol>"
        		+ "</html>");
        GUI.setPaddingAtJLabel(labelHtml);
        
        area = new JTextArea();
        area.append(""
        		+ "Στην αρχική σελίδα πλοηγήσε στις διάφορες δυνατότητες του συστήματος, καθώς υπάρχουν και τα \n"
        		+ "στοιχεία της επιχείρησης τα οποία δύνανται να αποθηκευτούν σε αρχείο. Επιλέγοντας κοστολόγηση \n"
        		+ "ανοίγει καρτέλα στην οποία μπορείς να βγάλεις τιμές για τα προϊόντα της επιχείρησης. Στην \n"
        		+ "καρτέλα τιμολόγιο οργανώνεις απόλυτα και εύκολα τα απαραίτητα στοιχεία για την έκδοση ενός \n"
        		+ "τιμολογίου. Στον τιμοκατάλογο μπορείς να βρεις τα προϊόντα της επιχείρησης. Στους προμηθευτές \n" 
        		+ "πληροφορίες για αυτούς. Στα αποθέματα πληροφορίες για τη διαθεσιμότητα των προϊόντων. Ενώ στην \n"
        		+ "καρτέλα γενικά πληροφορίες για το σύστημα.");
        area.setFont(sansserifFontMedium);
        GUI.setPaddingAtJTextArea(area);
        JScrollPane pane = new JScrollPane(area);
        
        labelHtml.setFont(sansserifFontMedium);
        labelHtml.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        centralPanel.add(labelHtml);
        centralPanel.add(pane);
		
        //makeHtmlPage();
        
        centralPanel.setBackground(Color.LIGHT_GRAY);
        
        mainPanel.add(centralPanel,BorderLayout.CENTER);
	}
	
	class writeInfoInFilebtnListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			FileWriterLocal.writeInfo();
			GUI.showConfirmationWindow("Το αρχείο βρίσκεται στην επιφάνεια εργασίας, δείτε το.", 500);
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
		sansserifFontMedium = new Font("Sans Serif", Font.PLAIN, 20);
		serifFont = new Font("serif", Font.BOLD, 22);
		serifFontSmall = new Font("serif", Font.ITALIC|Font.BOLD, 17);
		sansSerifFontSmall = new Font("Sans Serif", Font.ITALIC|Font.BOLD, 17);
	}

}
