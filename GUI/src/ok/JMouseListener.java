package ok;

import javax.swing.JPanel;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Point;

@SuppressWarnings("serial")
public class JMouseListener extends JPanel{
	private Vector<Vector<Point>> m_vectorset=new Vector<Vector<Point>>();
	public JMouseListener(){
		addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e){}
			public void mousePressed(MouseEvent e){
				Point p=new Point(e.getX(),e.getY());
				Vector<Point> v=new Vector<Point>();
				v.add(p);
				m_vectorset.add(v);
			}
			public void mouseReleased(MouseEvent e){}
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				Point p=new Point(e.getX(),e.getY());
				int n=m_vectorset.size()-1;
				Vector<Point> v=m_vectorset.get(n);
				v.add(p);
				repaint();
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	protected void paintComponent(Graphics g){
		g.clearRect(0,0,getWidth(),getHeight());
		Vector<Point> v;
		Point s,t;
		int i,j,m;
		int n=m_vectorset.size();
		for(i=0;i<n;i++){
			v=m_vectorset.get(i);
			m=v.size()-1;
			for(j=0;j<m;j++){
				s=(Point)v.get(j);
				t=(Point)v.get(j+1);
				g.setColor(Color.GREEN);
				g.drawLine(s.x,s.y,t.x,t.y);
			}
		}
	}
	public Dimension getPreferredSize(){
		return new Dimension(250,120);
	}
}
