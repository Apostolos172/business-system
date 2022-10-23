import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Confirmation_GUI extends JFrame{
	private JPanel panel,centralPanel,northPanel;
	private JButton buttonOK;
    private ImageIcon image;
    private JLabel imageLabel,messageTxt;
	
	private String message;
	
	public Confirmation_GUI(String message)
	{
		super("Μήνυμα επιβεβαίωσης");
		this.message = message;
		makeFrame();
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
		buttonOKListener OKlistener = new buttonOKListener();
		buttonOK.addActionListener(OKlistener);
		
		panel.add(northPanel);
		panel.add(centralPanel);
		setContentPane(panel);
		
		centralPanel.setBackground(Color.cyan);
		northPanel.setBackground(Color.cyan);
		panel.setBackground(Color.cyan);
		
		this.setResizable(false);
		this.setVisible(true);
		this.setSize(500,200);
	}
	
	class buttonOKListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			if(buttonOK.getModel().isArmed())
				System.out.println("yes");
			setVisible(false); 
			dispose(); 
		}
	}
}
