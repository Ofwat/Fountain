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

package uk.gov.ofwat.fountain.api;


/**
 * A class of Exception that includes a list of strings for cumulative errors
 */
import java.util.List;

public class ListException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6763967136995161849L;
	private List<String> errors;
	
	public ListException(List<String> errors){
		super();
		this.errors = errors;
	}
	
	public ListException(List<String> errors, Exception cause){
		super(cause);
		this.errors = errors;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	

}
