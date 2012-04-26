package level;

import java.lang.reflect.Array;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class OptionPaneMultiple extends JFrame
{
	JTextField att1 = new JTextField();
	JTextField att2 = new JTextField();
	
	public OptionPaneMultiple()
	{
		
		Object[] msg = {"Attribute1:", att1, "Attribute2:", att2};
 
		JOptionPane op = new JOptionPane(
			msg,
			JOptionPane.QUESTION_MESSAGE,
			JOptionPane.OK_CANCEL_OPTION,
			null,
			null);
 
		JDialog dialog = op.createDialog(this, "Set Attributes");
		dialog.setVisible(true);
 
		int result = JOptionPane.OK_OPTION;
 
		try
		{
		    result = ((Integer)op.getValue()).intValue();
		}
		catch(Exception uninitializedValue)
		{}
 
		if(result == JOptionPane.OK_OPTION)
		{
			System.out.println(att1.getText() + " : " + att2.getText());
		}
		else
		{
			System.out.println("Canceled");
		}
 
	}
 
 
	public String[] optionPane()
	{
		
		return null;
	}
}
