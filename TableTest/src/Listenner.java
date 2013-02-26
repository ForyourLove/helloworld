import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JButton;

class JListener implements ActionListener{
	int m_count=0;
	
	public void actionPerformed(ActionEvent e){
		JButton b=(JButton)e.getSource();
		b.setText("单击"+(++m_count)+"次");
		System.out.println("操你妹。。。。。");
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection c
			=DriverManager.getConnection(
					"jdbc:odbc:studentDatabase","sa","1"
					);
			Statement s=c.createStatement();
			//s.executeUpdate("create table school(sno integer,sname char(40))");
			s.executeUpdate("insert into school values(2008302973,'安徽理工大学')");
			s.close();
			c.close();
			System.out.println("创建数据库学校表。。成功。。。");
		}
		catch(Exception e0){
			System.out.println("异常："+e0.getMessage());
		}
	
	}

}
@SuppressWarnings("serial")
public class Listenner extends JFrame{
	public Listenner(){
		super("动作事件例程");
		Container c=getContentPane();
		JButton b=new JButton("单击0次");
		JListener a=new JListener();
		b.addActionListener(a);
		c.add(b,BorderLayout.CENTER);
	}
	public static void main(String args[]){
		Listenner app=new Listenner();
		app.setDefaultCloseOperation(EXIT_ON_CLOSE);
		app.setSize(100,80);
		app.setVisible(true);
	}
}
