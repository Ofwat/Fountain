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

package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileSaver {
	
	
	private static sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
	
	public static void main(String[] args){
		String filename = "2SewAdmin menu tweaks.doc";
		File infile = new File("d:/restored/" +filename+ ".txt");
		File outfile = new File("d:/restored/" + filename);
		FileSaver fs = new FileSaver();
		fs.decode(infile, outfile);
	}
	
	private void decode(File infile, File outfile){
		try{
			InputStream is = new FileInputStream(infile);
			OutputStream os = new FileOutputStream(outfile);
			sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
			decoder.decodeBuffer(is, os);
			}
			catch(IOException ioe){
				System.out.println("Error: " + ioe.getMessage());
			}
	}

}
