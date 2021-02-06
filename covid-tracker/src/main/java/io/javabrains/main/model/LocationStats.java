package io.javabrains.main.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationStats {
	
	private String state;
	private String country;
	private int latestTotalCases;
	private int diffFromPrevDay;
	
	@Override
	public String toString() {
		return "LocationStats [state=" + state 
				+ ", country=" + country 
				+ ", latestTotalCases=" + latestTotalCases
				+ ", diffFromPrevDay=" + diffFromPrevDay + "]";
	}	
	
}
