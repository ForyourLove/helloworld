package ok;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class test extends JFrame{
	
	public test(){
		super("框架与标签实例");
		// TODO Auto-generated constructor stub
		String []s={"文本标签","文字在图表的左侧","文字在图表的下方"};
		ImageIcon []ic={null,new ImageIcon("img1.gif"),new ImageIcon("img2.gif")};
		int []ih={0,JLabel.LEFT,JLabel.CENTER};
		int []iv={0,JLabel.CENTER,JLabel.BOTTOM};
		Container c=getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER));
		for(int i=0;i<3;i++){
			JLabel jl=new JLabel(s[i],ic[i],JLabel.LEFT);
			if(i>0){
				jl.setHorizontalTextPosition(ih[i]);
				jl.setVerticalTextPosition(iv[i]);
			}
			jl.setIcon(new ImageIcon("ok/img1.gif"));
			jl.setToolTipText("第"+(i+1)+"个标签");
			c.add(jl);
		}
	}

	public static void main(String []agrs){
		test t=new test();
		JButton yb=new JButton("yellow");
		JButton rb=new JButton("red");
		JPanel jp=new JPanel();
		jp.add(yb);
		jp.add(rb);
		t.add(jp,BorderLayout.SOUTH);
		System.out.println("hello........");
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.setSize(560,350);
		t.setVisible(true);
	}
}
