/*
 *  Copyright (C) 2009 Water Services Regulation Authority (Ofwat)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */

package uk.gov.ofwat.fountain.tools;

import java.io.File;
import java.io.FileInputStream;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class FormexPreProcessor {

	
	private String[] formsToAdjust = {"10a(i)", "10a(ii)", "10a(iii)"};
	
	 // File to read XML from
	  private File inputXML;

	  // File to serialize XML to
	  private File outputXML;

	/**
	 * @param file
	 */
	public FormexPreProcessor(File inFile, File outFile) {
		this.inputXML = inFile;
		this.outputXML = outFile;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			FormexPreProcessor tester = 
		        new FormexPreProcessor(new File(args[0]), new File(args[1]));
		      tester.run();
		    } catch (Exception e) {
		      e.printStackTrace( );
		    }

		

	}
	
	public void run() 
    throws Exception {

    DOMParser parser = new DOMParser();

    // Get the DOM tree as a Document object

    // Serialize
    // Get the DOM tree as a Document object
    parser.parse(new InputSource(
        new FileInputStream(inputXML)));
    Document doc = parser.getDocument();
    NodeList codeNodes = doc.getElementsByTagName("code");
    for(int i = 0; i < codeNodes.getLength(); i++){
    	Node codeNode = codeNodes.item(i);
    	
    	for(String formName: formsToAdjust){
    		if(codeNode.getParentNode().getNodeName().equals("pagedetails")){
    			if(codeNode.getFirstChild().getNodeValue().equals(formName)){
    				processPage(codeNode.getParentNode().getParentNode());
    			}
        	}
    	}
    }
    
    // serialise the output
    
    DomSerialiser serialiser = new DomSerialiser();
    serialiser.serialize(doc, outputXML);
  }

	private void processPage(Node pageNode){
		// get a page node and find it's section children
		for(int i = 0; i < pageNode.getChildNodes().getLength(); i++){
			
			if(pageNode.getChildNodes().item(i).getNodeName().equals("sections")){
				NodeList nl = pageNode.getChildNodes().item(i).getChildNodes();
				for(int j = 0; j < nl.getLength(); j++){
					if(nl.item(j).getNodeName().equals("section")){
						processSection(nl.item(j));
					}
				}
				break; // only 1 sections element
			}
			
		}
	}
	
	private void processSection(Node sectionNode){

		
		for(int i = 0; i < sectionNode.getChildNodes().getLength(); i++){
			
			if(sectionNode.getChildNodes().item(i).getNodeName().equals("forms")){
				NodeList nl = sectionNode.getChildNodes().item(i).getChildNodes();
				for(int j = 0; j < nl.getLength(); j++){
					if(nl.item(j).getNodeName().equals("form")){
						processForm(nl.item(j));
					}
				}
				break; // only 1 forms element
			}
		}
	}
	
	private void processForm(Node formNode){
		for(int i = 0; i < formNode.getChildNodes().getLength(); i++){
			
			if(formNode.getChildNodes().item(i).getNodeName().equals("form-headings-left")){
				NodeList nl = formNode.getChildNodes().item(i).getChildNodes();
				for(int j = 0; j < nl.getLength(); j++){
					if(nl.item(j).getNodeName().equals("form-heading-cell")){
						Node fhc = nl.item(j);
						String rowNum = null;
						String groupName = null;
						for(int k = 0; k < fhc.getChildNodes().getLength(); k++){
							if(fhc.getChildNodes().item(k).getNodeName().equals("row")){
								Node rowNode = fhc.getChildNodes().item(k);
								rowNum = rowNode.getFirstChild().getNodeValue();
							}
							if(fhc.getChildNodes().item(k).getNodeName().equals("group-description-code")){
								Node gdcNode = fhc.getChildNodes().item(k);
								groupName = gdcNode.getFirstChild().getNodeValue();
							}	
						}
						if(null != rowNum && !("1".equals(rowNum)) && null != groupName && !("NONE".equals(groupName))){
							// remove this row
							fhc.getParentNode().removeChild(fhc);
						}
					}
				}
			}
			if(formNode.getChildNodes().item(i).getNodeName().equals("form-cells")){
				NodeList nl = formNode.getChildNodes().item(i).getChildNodes();
				for(int j = 0; j < nl.getLength(); j++){
					if(nl.item(j).getNodeName().equals("form-cell")){
						Node fc = nl.item(j);
						String rowNum = null;
						String groupName = null;
						for(int k = 0; k < fc.getChildNodes().getLength(); k++){
							if(fc.getChildNodes().item(k).getNodeName().equals("row")){
								Node rowNode = fc.getChildNodes().item(k);
								rowNum = rowNode.getFirstChild().getNodeValue();
							}
							if(fc.getChildNodes().item(k).getNodeName().equals("group-description-code")){
								Node gdcNode = fc.getChildNodes().item(k);
								groupName = gdcNode.getFirstChild().getNodeValue();
							}	
						}
						if(null != rowNum && !("1".equals(rowNum)) && null != groupName && !("NONE".equals(groupName))){
							// remove this row
							fc.getParentNode().removeChild(fc);
						}
					}
				}
			}	
		}
	}


}
