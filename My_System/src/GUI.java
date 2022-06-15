import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GUI {
	
	/**
	 * Accepts a frame and puts to it size approximately to the screen size
	 */
	public static void setSizeOfTheWindow(JFrame frame)
	{
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		width = Math.floor(width);
		//String w = Double.toString(width);
		double height = screenSize.getHeight();
		height = Math.floor(height);
		String h = Double.toString(height);
		//System.out.println((int)Double.parseDouble(w));
		frame.setSize((int)width,(int)Double.parseDouble(h));
	}
	
	public static void setPadding(JPanel panel)
	{
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
	}
	
	public static void setPaddingAtJTextField(JTextField txtfield)
	{
		txtfield.setBorder(BorderFactory.createCompoundBorder(
				txtfield.getBorder(), 
		        BorderFactory.createEmptyBorder(15, 15, 15, 15)));
	}
	
	public static void setLeftPaddingAtJTextField(JTextField txtfield)
	{
		txtfield.setBorder(BorderFactory.createCompoundBorder(
				txtfield.getBorder(), 
		        BorderFactory.createEmptyBorder(0,15,0,0)));
	}
	
	public static void setPaddingAtJTextArea(JTextArea txtarea)
	{
		txtarea.setBorder(BorderFactory.createCompoundBorder(
				txtarea.getBorder(), 
		        BorderFactory.createEmptyBorder(15, 15, 15, 15)));
	}
	
	public static void setPaddingAtJLabel(JLabel label)
	{
		label.setBorder(BorderFactory.createCompoundBorder(
				label.getBorder(), 
		        BorderFactory.createEmptyBorder(15, 15, 15, 15)));
	}
	
	public static void showConfirmationWindow(String message, int length)
	{
		Confirmation_GUI confirmation = new Confirmation_GUI(message);
		confirmation.setSize(length, 200);
		confirmation.setLocation(670, 500);
	}
}
