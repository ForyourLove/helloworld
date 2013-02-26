/**
 * Copyright 2006 Alien Technology Corporation. All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <p>
 * 1)	Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <p>
 * 2)	Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * <p>
 * 3)	Neither the name of Alien Technology Corporation nor the names of any
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 * <p>
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL ALIEN TECHNOLOGY CORPORATION OR ITS CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * <p>
 * For further information, contact :
 * <p>
 * Alien Technology
 * 18220 Butterfield Blvd.
 * Morgan Hill, CA 95037
 */

import com.alien.enterpriseRFID.reader.*;
import com.alien.enterpriseRFID.tags.*;

/**
 * Connects to a Reader on COM port #1 and asks it to read tags.
 *
 * @version 1.2 Feb 2004
 * @author David Krull
 */
public class AlienClass1ReaderTest{

/**
 * Constructor
 */
public AlienClass1ReaderTest() throws AlienReaderException {
	
  AlienClass1Reader reader = new AlienClass1Reader();
  //reader.setConnection("COM1");

  // To connect to a networked reader instead, use the following:

  reader.setConnection("192.168.1.114:23");
  reader.setUsername("alien");
  reader.setPassword("password");
 

  // Open a connection to the reader
  reader.open();
  reader.setAcquireMode(AlienClass1Reader.INVENTORY);
  reader.setTagStreamAddress("192.168.1.102", 3988);
  System.out.println("Configuring Reader");

  // Set up TagStream.
  // Use this host's IPAddress, and the port number that the service is listening on.
  // getHostAddress() may find a wrong (wireless) Ethernet interface, so you may
  // need to substitute your computers IP address manually.
  reader.setTagStreamFormat(AlienClass1Reader.TEXT_FORMAT); // Make sure service can decode it.
  reader.setTagStreamMode(AlienClass1Reader.ON);

  // Set up AutoMode - use standard settings.
  reader.autoModeReset();
  reader.setAutoMode(AlienClass1Reader.ON);

  // Close the connection and spin while messages arrive
  reader.close();
    try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  
  // Reconnect to the reader and turn off AutoMode and TagStreamMode.
  System.out.println("\nResetting Reader");
  reader.open();
  reader.autoModeReset();
  int t=0;
  // Ask the reader to read tags and print them
  while(true)
  {
	  t++;
	  Tag tagList[] = reader.getTagList();
	  if (tagList == null) {
		  if(t==3) break;
		  //String str = reader.getInfo();
		  System.out.println("No Tags Found");
		  try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  } else {
		  System.out.println("Tag(s) found:");
		  for (int i=0; i<tagList.length; i++) {
			  Tag tag = tagList[i];
			  if(t==2)
//				  tag.setTagID("1001 1002 1003 1006 1007 8888");
			  System.out.println("ID:" + tag.getTagID() +
                         	", Discovered:" + tag.getDiscoverTime() +
                         	", Last Seen:" + tag.getRenewTime() +
                         	", Antenna:" + tag.getAntenna() +
                         	", Reads:" + tag.getRenewCount()+
                         	",data: "+ tag.getG2Data(3)+
                         	",speed: "+tag.getSpeed()
                         	);
			  try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  }
	  }
  }
  // Close the connection
  reader.close();
}

/**
 * Main
 */
public static final void main(String args[]){
  try {
    new AlienClass1ReaderTest();
  } catch(AlienReaderException e) {
    System.out.println("Error: " + e.toString());
  }
}

} // End of class AlienClass1ReaderTest