package com.art.entities.learning;

import java.util.Arrays;

public class SearchResponse {
	
	private int total;
	private int objectIDs[];
	
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int[] getObjectIDs() {
		return objectIDs;
	}
	public void setObjectIDs(int[] objectIDs) {
		this.objectIDs = objectIDs;
	}
	
	@Override
	public String toString() {
		return "SearchResponse [total=" + total + ", objectIDs=" + Arrays.toString(objectIDs) + "]";
	}
	
	

}
