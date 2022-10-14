package oleg.fomin;

import java.util.Objects;

/* Contains information about household full address*/
public class Household {
	private String streetAddress;
	private String city;
	private String state;

	public Household(String streetAddress, String city, String state) {
		super();
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, state, streetAddress);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Household other = (Household) obj;
		return Objects.equals(city, other.city)
				&& Objects.equals(state, other.state)
				&& Objects.equals(streetAddress, other.streetAddress);
	}

	// Making the CSV record here so it easier to print it out later
	@Override
	public String toString() {
		return "\""+streetAddress+"\","+"\""+city+"\",\""+state+"\"";
	}
	
	
}
