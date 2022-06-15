import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;

public class AreYouSure_GUI extends JFrame{
	private JPanel panel,centralPanel,northPanel;
	private JButton buttonOK;
    private ImageIcon image;
    private JLabel imageLabel,messageTxt;
	
	private String message;
	private boolean accepted;
	private JButton buttonCancel;
	private String product;
	
	public AreYouSure_GUI(String message,String product)
	{
		super("Μήνυμα επιβεβαίωσης");
		this.message = message;
		this.product = product;
		makeFrame();
	}
	
	public JButton getButtonOK() {
		return buttonOK;
	}

	public JButton getButtonCancel() {
		return buttonCancel;
	}

	public void makeFrame() {
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		centralPanel = new JPanel();
		northPanel = new JPanel();
		
		Font serifFont = new Font("serif", Font.BOLD, 20);
		Font sansSerifFont = new Font("Sans Serif", Font.BOLD, 16);
		
        image = new ImageIcon("info_black.png");
        imageLabel = new JLabel(image);
        northPanel.add(imageLabel);
        
        messageTxt = new JLabel(message);
        messageTxt.setFont(sansSerifFont);
		northPanel.add(messageTxt);
		
		buttonOK = new JButton("OK");
		buttonOK.setFont(serifFont);
		centralPanel.add(buttonOK);
		buttonListener OKlistener = new buttonListener();
		buttonOK.addActionListener(OKlistener);
		
		buttonCancel = new JButton("’κυρο");
		buttonCancel.setFont(serifFont);
		centralPanel.add(buttonCancel);
		buttonListener buttonCancellistener = new buttonListener();
		buttonCancel.addActionListener(buttonCancellistener);
		
		panel.add(northPanel);
		panel.add(centralPanel);
		setContentPane(panel);
		
		centralPanel.setBackground(Color.cyan);
		northPanel.setBackground(Color.cyan);
		panel.setBackground(Color.cyan);
		
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(400,200);
	}
	
	public boolean acceptedConfirmation(String product) throws ClassNotFoundException
	{
		if(buttonOK.getModel().isArmed())
		{
			accepted = true;
			System.out.println(accepted);
			try {
				StockList menu = new StockList();
				menu.deleteProductFromStockList(product);
				GUI.showConfirmationWindow("Το προϊόν αφαιρέθηκε από τον κατάλογο", 400);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else if(buttonCancel.getModel().isArmed())
		{
			accepted = false;
			System.out.println(accepted);
		}
		return accepted;
	}
	
	class buttonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				acceptedConfirmation(product);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			setVisible(false); 
			dispose(); 			
		}
		
	}

}
