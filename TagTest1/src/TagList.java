import com.alien.enterpriseRFID.reader.AlienClass1Reader;
import com.alien.enterpriseRFID.reader.AlienReaderException;
import com.alien.enterpriseRFID.tags.Tag;
import com.alien.enterpriseRFID.tags.TagTableListener;


public class TagList implements TagTableListener {
	public TagList() throws Exception{
		 reader = new AlienClass1Reader("192.168.1.114:23");
		 reader.open();
		 System.out.println("Configuring Reader");

		  // Set up TagStream.
		  // Use this host's IPAddress, and the port number that the service is listening on.
		  // getHostAddress() may find a wrong (wireless) Ethernet interface, so you may
		  // need to substitute your computers IP address manually.
		 reader.setTagStreamFormat(AlienClass1Reader.TEXT_FORMAT); // Make sure service can decode it.
		 reader.setTagStreamMode(AlienClass1Reader.ON);
		  //reader.setNotifyTrigger("ADD");
		  // Set up AutoMode - use standard settings.
		 reader.autoModeReset();
		 reader.setAutoMode(AlienClass1Reader.ON);
	}
	public void tagAdded(Tag arg0) {
		// TODO Auto-generated method stub
		try {
			reader.setProgUserData(" 66 66 66 66");
		} catch (AlienReaderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("tagid=="+arg0.getTagID()+"userdata=="+arg0.getG2Data(0));
	}

	public void tagRemoved(Tag arg0) {
		// TODO Auto-generated method stub
		System.out.println("tagid=="+arg0.getTagID()+"userdata=="+arg0.getG2Data(0));
	}

	public void tagRenewed(Tag arg0) {
		// TODO Auto-generated method stub
		System.out.println("tagid=="+arg0.getTagID()+"userdata=="+arg0.getG2Data(0));
	}

	private  AlienClass1Reader reader;
	//**
	/**
	 * Main
	 */
	public static final void main(String args[]){
	  try {
		  new TagList();
	  } catch (Exception e) {
	    System.out.println("Error:" + e.toString());
	  }
	}
}
