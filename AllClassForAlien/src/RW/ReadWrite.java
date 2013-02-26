package RW;


import java.net.InetAddress;

import com.alien.enterpriseRFID.notify.Message;
import com.alien.enterpriseRFID.notify.MessageListener;
import com.alien.enterpriseRFID.notify.MessageListenerService;
import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.tags.Tag;

public class ReadWrite implements MessageListener{
	
	public ReadWrite()throws Exception{
		  service = new MessageListenerService(3988);
		  service.setMessageListener(this);
		  service.startService();
		  System.out.println("Message Listener has Started");

		  // Instantiate a new reader object, and open a connection to it on COM1
		  reader = new AlienClass1Reader("192.168.1.114:23");
		  reader.open();
		  System.out.println("Configuring Reader");
		  reader.setNotifyMode(AlienClass1Reader.ON);
		  reader.setNotifyRetryPause(30);
		// Set up TagStream.
		  // Use this host's IPAddress, and the port number that the service is listening on.
		  // getHostAddress() may find a wrong (wireless) Ethernet interface, so you may
		  // need to substitute your computers IP address manually.
		  reader.setTagStreamAddress(InetAddress.getLocalHost().getHostAddress(), service.getListenerPort());
		  reader.setTagStreamFormat(AlienClass1Reader.XML_FORMAT); // Make sure service can decode it.
		  reader.setTagStreamMode(AlienClass1Reader.ON);
		  reader.setNotifyTrigger("Add");
		  reader.autoModeReset();
		  reader.setAutoMode(AlienClass1Reader.ON);
		  System.out.println("action=="+reader.getAutoAction());
		  try {
			  reader.setAcquireMode(AlienClass1Reader.INVENTORY);
			  reader.setReaderFunction(AlienClass1Reader.FUNCTION_PROGRAMMER);
			  reader.saveSettings();
			  //reader.unlockUser();
//			  reader.programUser("AAAA AAAA AAAA AAAA 6666 6666 6666 6666 8888 FFFF");
			  //reader.programEPC("8888 8888 8888 8888 8888 8888");
			} catch (AlienReaderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try{
//				reader.setAcqG2Mask(AlienClass1Reader.ALL_MASK);
//				String str1 = reader.getAcqG2Mask();
//				System.out.println("mask=="+str1);
				reader.setTagMask("1001 1002 1003 1004 1005 1006");
				byte[] b = new byte[100];
				b=reader.g2Read(3, 0, 0);
				System.out.println("g2read==="+bytesToHexString(b)); 
//				for(int i=0;i<16;i++)
//					System.out.print("byte["+i+"]"+b[i]+"--");
				reader.setTagMask(AlienClass1Reader.ALL_MASK);
			}catch(AlienReaderException e){
				e.printStackTrace();
				//System.out.println("g2read");
			}
		  long runTime = 4000; // milliseconds
		  long startTime = System.currentTimeMillis();
		  do {
		    Thread.sleep(1000);
		  } while(service.isRunning() && (System.currentTimeMillis()-startTime) < runTime);
		  // Reconnect to the reader and turn off AutoMode and TagStreamMode.
		  
		  System.out.println("\nResetting Reader");
		  reader.open();
		  //reader.programEPC("8888 8888 8888 8888 8888 8888");
		  reader.autoModeReset();
		  reader.setTagStreamMode(AlienClass1Reader.OFF);
		  reader.close();
	}
	
	//listen action
	public void messageReceived(Message mess) {
		// TODO Auto-generated method stub
		
		//System.out.println("::reason ="+mess.getReason());
		int c = mess.getTagCount();
		Tag[] tags = mess.getTagList(); 
		for(int i=0;i<c;i++){
			Tag  tag = tags[i];
			System.out.print("tagpersisttime=="+tag.getPersistTime());
//			String tid = tags[0].getTagID();
//			String string= tag.getG2Data(3);
			System.out.println("  tagid="+tag.getTagID());
		}
	}
	private AlienClass1Reader reader;
	private MessageListenerService service;
	/**
	 * byte转换为十六进制字符串
	 */
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
	/**
	 * Main
	 */
	public static final void main(String args[]){
	  try {
	    new ReadWrite();
	  } catch (Exception e) {
	    System.out.println("Error:" + e.toString());
	  }
	}	
}
