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
package uk.gov.ofwat.fountain.domain;

public class CacheLevel {

	private String cacheName;
	private String targetName;
	private int percentageFull;
	
	public CacheLevel(String cacheName, String targetName, int percentageFull) {
		this.cacheName = cacheName;
		this.targetName = targetName;
		this.percentageFull = percentageFull;
	}

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public int getPercentageFull() {
		return percentageFull;
	}

	public void setPercentageFull(int percentageFull) {
		this.percentageFull = percentageFull;
	}

	@Override
	public String toString() {
		return "CacheLevel [cacheName=" + cacheName + ", targetName="
				+ targetName + ", percentageFull=" + percentageFull + "]";
	}
	
}
