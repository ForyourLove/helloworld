import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JPanel;

public class Dialog{
	public static void main(String args[]){
		JFrame app=new JFrame("框架");
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(200,100);
		
//		JDialog d=new JDialog(app,"对话框",false);
		Container c1=app.getContentPane();
		c1.setLayout(new FlowLayout());
//		c1.add(new JLabel("hello"));
		JSlider s=new JSlider(JSlider.HORIZONTAL,0,30,10);
		JPanel p=new JPanel();
		p.setPreferredSize(new Dimension(100,60));
		p.setBackground(Color.green);
		c1.add(s);
		c1.add(p);
		JDialog d=new JDialog(app,"对话框",false);
		Container c=d.getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.LEFT));
		c.add(new JLabel("你好"));
		d.setSize(80,80);
		
		ImageIcon ic=new ImageIcon("img.gif");
		JOptionPane.showMessageDialog(null,"你好！！！你中毒了。。。。。","hello world",JOptionPane.WARNING_MESSAGE,ic);
		//JOptionPane.show
		app.setVisible(true);
		d.setVisible(true);
	}

}
