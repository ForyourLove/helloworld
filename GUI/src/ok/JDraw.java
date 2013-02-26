package ok;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class JDraw extends JFrame{
		public JDraw(){
			super("ËæÊÖ»­Àý³Ì");
			Container c=getContentPane();
			c.add(new JMouseListener(),BorderLayout.CENTER);
		}
		public static void main(String []args){
			JDraw app=new JDraw();
			app.setDefaultCloseOperation(EXIT_ON_CLOSE);
			app.setSize(300,150);
			app.setVisible(true);
		}
}
