import java.awt.Color;
import java.io.*;

import javax.swing.JLabel;

public class FileWriterLocal {
	
	public static void writeInfo()
	{
		try
		{
			String no = "C:\\Users\\user\\Desktop\\information of company.txt";
			File f = new File("C:\\Users\\apost\\OneDrive\\Desktop\\information_of_company.txt");
			FileWriter writer = new FileWriter(f);
			writer.write("�������� �����������: �������\n");
			writer.write("���. 24670 24670\n");
			writer.write("���������: ����\n");
			writer.write("�������: �����������, 11111\n");
			writer.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
