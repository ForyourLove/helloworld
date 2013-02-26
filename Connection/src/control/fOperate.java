package control;

import java.io.IOException;
import java.io.RandomAccessFile;

public class fOperate {

	/**
	 * @param args
	 */
	private String name,phone,qq,waddress,company,laddress,comment;
	private long len;
	
	//通过写操作把要操作的文件名传递过来，纯粹的写操作。
	public void write(String fname,long length,String s){
	try{
		RandomAccessFile file=new RandomAccessFile(fname, "rw");
		file.seek(length);
		file.writeChars(s);
	}
	catch(IOException e){
		System.err.println("发生异常："+e);
		e.printStackTrace();
	}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
