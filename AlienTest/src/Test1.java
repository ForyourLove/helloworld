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

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.alien.enterpriseRFID.reader.*;
import com.alien.enterpriseRFID.tags.*;

/**
 * Connects to a Reader on COM port #1 and asks it to read tags.
 *
 * @version 1.2 Feb 2004
 * @author David Krull
 */
public class Test1 {

/**
 * Constructor
 */
public Test1() throws AlienReaderException {

  AlienClass1Reader reader = new AlienClass1Reader();
  reader.setConnection("192.168.1.117:23");
  reader.setUsername("alien");
  reader.setPassword("password");
  // To connect to a networked reader instead, use the following:
/*
  reader.setConnection("10.1.60.107", 23);
  reader.setUsername("alien");
  reader.setPassword("password");
 */

  // Open a connection to the reader
  try {
		reader.open();
		reader.setAcquireMode(AlienClass1Reader.GLOBAL_SCROLL);
		reader.setTagListFormat("text");

	} catch (Exception err) {
		System.out.println("reader setting error+ "+err.getMessage());
	}
  String readerName = reader.doReaderCommand("get ReaderName");
  String format = reader.getTagListFormat();
  String sadd = reader.getTagStreamAddress();
  String tagid;
  try{
	  Tag tag = reader.getTag();
	  tag.setG2Data(3, "BB BB BB BB");
	  tagid = tag.getTagID();
	  System.out.println("tagid=="+tagid+"###data=="+tag.getG2Data(3));
	  //reader.setProgUserData("AA AA AA AA");
  }catch(AlienReaderException e){
	  System.out.println("write failure");
	  e.printStackTrace();
  }
  System.out.println("readerName=="+readerName+"----format="+format+"---stringadd=="+sadd);
  try {
	Thread.sleep(1000);
} catch (InterruptedException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
try{
	Tag tag1 = reader.getTag();
	String string = tag1.getG2Data(3);
	reader.clearTagList();
	System.out.println("user data == "+string);
}catch(AlienReaderException e){
	  e.printStackTrace();
}
  // Ask the reader to read tags and print them
  
}

/**
 * Main
 */
public static final void main(String args[]){
//	DiscoveryItem dsi = new DiscoveryItem();
//	String string = dsi.getReaderAddress();
//	System.out.println("address=="+string);
  try {
//	  try {
//		String string = InetAddress.getLocalHost().getHostName();
//		System.out.println("ip =="+string);
//	} catch (UnknownHostException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
    new Test1();
  } catch(AlienReaderException e) {
    System.out.println("Error: " + e.toString());
  }
}

} // End of class AlienClass1ReaderTest