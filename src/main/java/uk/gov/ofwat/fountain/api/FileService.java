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

import java.io.File;
import java.util.List;

import uk.gov.ofwat.fountain.domain.ImportResponse;
import uk.gov.ofwat.fountain.rest.dto.ImportResponseDto;

public interface FileService {
	/**
	 * Takes a dictionary document and processes this. Currently supports:
	 * <ul>
	 * <li>uk.gov.ofwat.version1.DictionaryDocument - the reservoir format</li>
	 * </ul>
	 * @param Dictionary (XMLBeans)
	 * @return a List of strings containing problems encountered. 
	 * The process failed if this list is not empty
	 */
	public List<String> processDictionary(Object dictionary);

	
	/**
	 * Takes a model document and processes this. Currently supports:
	 * <ul>
	 * <li>uk.gov.ofwat.model2.ModelDocument - the reservoir format</li>
	 * </ul>
	 * @param model a marshalled XML model
	 * @return a List of Strings containing problems encountered
	 * The process failed if this list is not empty
	 */
	public List<String> processModel(Object model);
	
	public List<String> bulkModelImport();

	/**
	 * @param model a marshalled XML model
	 * @return a List of Strings containing problems encountered
	 * The process failed if this list is not empty
	 */
	public List<String> processModelForms(Object model);

	public String writeTableUploadLogFile(ImportResponseDto responseDto);
	public File readTableUploadLogFile(long id);

	public ImportResponse singleModelImport(String filename);
}
