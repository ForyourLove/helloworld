
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
	/**
	 * Main
	 */
	public static final void main(String args[]){
	  try {
		  byte[] b = new byte[16];
		  for(int i=0;i<16;i++)
			  b[i]=126;
	    System.out.println("result"+test.bytesToHexString(b));
	  } catch (Exception e) {
	    System.out.println("Error:" + e.toString());
	  }
	}
}
