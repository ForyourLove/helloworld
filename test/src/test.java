
/*class out{
	public int a=0;
	public String print(){
		return this.toString();
	}
}*/
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class test {
	public static String bytesToHexString(byte[] src){  
	    StringBuilder stringBuilder = new StringBuilder("");  
	    if (src == null || src.length <= 0) {  
	        return null;  
	    }  
	    for (int i = 0; i < src.length; i++) {  
	        int v = src[i] & 0xFF;  
	        String hv = Integer.toHexString(v);  
	        if (hv.length() < 2) {  
	            stringBuilder.append(0);  
	        }  
	        stringBuilder.append(hv);  
	    }  
	    return stringBuilder.toString();  
	}  
	
	public static void main(String[] args){
		byte b[] = {12};
		String str = bytesToHexString(b);
		System.out.println("str====="+str);
		/*//out o=new out();
		//Integer i=new Integer(51341);
		StringBuffer s=new StringBuffer();
		StringBuffer s1=new StringBuffer(20);
		StringBuffer s2=new StringBuffer("hello world!!");
		//Integer e=i.parseInt(s);
		System.out.println(s.length());
		System.out.println(s.capacity());
		System.out.println(s1.length());
		System.out.println(s1.capacity());
		System.out.println(s2.length());
		System.out.println(s2.capacity());*/
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
		catch(Exception e){
			System.out.println("异常："+e.getMessage());
		}
	}

}
