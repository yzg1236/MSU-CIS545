import java.awt.Dimension;
import java.awt.Event;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JWindow;

/**
 * @author yzg1236
 *
 */
public class JavaDemo {
	

	
    public static void main(String[] args) {
		final JFrame f1 = new JFrame();
		JWindow w1 = new JWindow();
		
		JPanel p1 = new JPanel();		
		f1.setContentPane(p1);		
		f1.setBounds(20, 20, 400, 300);
		
		
		JButton okBtn = new JButton("OK");		
		okBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				javax.swing.JOptionPane.showConfirmDialog(f1, "You are awesome!", "CoolApp", 1, 1, null);
			}
		});
		
		//okBtn.setBounds(10, 10, 40, 30);
		
		p1.add(okBtn);
		
		f1.setVisible(true);
		
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	
}