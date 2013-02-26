import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;

@SuppressWarnings("serial")
public class TableTest extends JFrame{
	DefaultTableModel m_data;
	JTable m_view;
	
	public TableTest(){
		super("表格应用实例");
		Container c=getContentPane();
		c.setLayout(new FlowLayout());
		int i;
		
		JButton []b={new JButton("添加行"),new JButton("添加列"),
				new JButton("删去行"),new JButton("删除列")};
		for(i=0;i<4;i++){
			c.add(b[i]);
		}
		m_data=new DefaultTableModel();
		m_view=new JTable(m_data);
		m_view.setPreferredScrollableViewportSize(
				new Dimension(300,150));
		m_view.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane sPane=new JScrollPane(m_view);
		c.add(sPane);
		b[0].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mb_addRow();
				System.out.println("添加一行");
			}
		});
		b[1].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mb_addColumn();
				System.out.println("添加一列");
			}
		});
		b[2].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mb_deleteRow();
				System.out.println("删除一行");
			}
		});
		b[3].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mb_deleteColumn();
				System.out.println("删除一列");
			}
		});
	}
	public void mb_addColumn(){//增加一列
		int cNum=m_data.getColumnCount();
		int rNum=m_data.getRowCount();
		String s="列"+(cNum+1);
		int c=m_view.getSelectedColumn();
		System.out.println("当前列号为：" + c);
		if(cNum==0||rNum==0||c<0){
			m_data.addColumn(s);
			return;
		}
		c++;
		Vector<String> vs=mb_getColumnNames();
		vs.add(c,s);
		Vector data=m_data.getDataVector();
		for(int i=0;i<data.size();i++){
			Vector e=(Vector) data.get(i);
			e.add(c,new String(""));
		}
		m_data.setDataVector(data,vs);
	}
	public void mb_addRow(){//增加一行
		int cNum=m_data.getColumnCount();
		if(cNum==0)
			mb_addColumn();
		int rNum=m_data.getRowCount();
		int r=mb_getRowCurrent();
		String []s={new String("姓名"),new String("分类"),
				new String("现居地"),new String("电话"),new String("QQ")};
		m_data.addRow(s);
		System.out.println("当前行号为："+r);
		m_data.insertRow(r,(Vector)null);
	}
	public void mb_deleteColumn(){//删除一列
		int cNum=m_data.getColumnCount();
		if(cNum==0){
			return;
		}
		int c=m_view.getSelectedColumn();
		if(c<0)
			c=0;
		System.out.println("当前列号为：" + c);
		
		Vector<String> vs=mb_getColumnNames();//表头处理
		vs.remove(c);
		
		Vector data=m_data.getDataVector();
		for(int i=0;i<data.size();i++){
			Vector e=(Vector) data.get(i);
			e.remove(c);
		}
		m_data.setDataVector(data,vs);
	}
	public void mb_deleteRow(){//删除一行
		int rNum=m_data.getRowCount();
		System.out.println(rNum);
		if(rNum>0)
		{
			int rEdit=mb_getRowCurrent();
			m_data.removeRow(rEdit);
		}
	}
	public Vector<String> mb_getColumnNames(){
		Vector<String> vs=new Vector<String>();
		int cNum=m_data.getColumnCount();
		for(int i=0;i<cNum;i++){
			vs.add(m_data.getColumnName(i));
		}
		return(vs);
	}
	public int mb_getRowCurrent(){
		int r=m_view.getSelectedRow();
		if(r<0) r=0;
		return(r);
	}
	public static void main(String args[]){
		JFrame app=new TableTest();
		
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setSize(600,450);
		app.setVisible(true);
	}
}
