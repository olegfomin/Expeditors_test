package oleg.fomin;

import java.util.Objects;

/* Contains information about the input record that was read from a CSV file given */
public class InputCSVRecord {
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String city;
	private String state;
	private String age;
	private int pointer = 0; // The convenience index here that initially points at firstName, when it is 1 then it point at lastName and so on  
	
	public InputCSVRecord() {
		super();
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	// Here we remove all the redundant dots which can be inside address we also bring all the address letters into the lower case
	public String getStreetAddressCanonical() {
		return streetAddress.toLowerCase().replace("\\.", "");
	}
	
	public String getCity() {
		return city;
	}
   
	public String getCityCanonical() {
		return city.toUpperCase();
	}
	
	public String getState() {
		return state;
	}
	// Here we bring all the state letters into the upper case
	public String getStateCanonical() {
		return state.toUpperCase();
	}

	public String getAge() {
		return age;
	}
	
	public int getAgeAsInt() {
		return Integer.parseInt(age);
	}
	
	public void setByIndexNumber(String input) {
		switch(pointer) {
		  case 0: this.firstName = input; break;
		  case 1: this.lastName  = input; break;
		  case 2: this.streetAddress = input; break;
		  case 3: this.city = input; break;
		  case 4: this.state = input; break;
		  case 5: this.age = input; break;
		  default: throw new IllegalStateException("Probably the input CSV contains to many params: '"+input+"'"); 
		}  
		pointer++;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getStateCanonical(), this.getStreetAddressCanonical());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InputCSVRecord other = (InputCSVRecord) obj;
		return Objects.equals(getStateCanonical(), other.getStateCanonical()) && 
			   Objects.equals(getStreetAddressCanonical(), other.getStreetAddressCanonical());
	}
// Here is the biggest challenge is to disregard the separating commas inside the double quotes 	
	public static InputCSVRecord parseString(String input) {
	  InputCSVRecord inputCSVRecord = new InputCSVRecord();
	  String previousElement = "";	
      String[] splitWithDoubleQuotes = input.split("\""); 
      for(String element : splitWithDoubleQuotes) {
        if(element.trim().equals("")) continue; 
        if(element.trim().equals(",")) {
        	inputCSVRecord.setByIndexNumber(previousElement);
        }
        previousElement = element;
      }
      if(previousElement != null && !previousElement.trim().equals("")) inputCSVRecord.setByIndexNumber(previousElement);
      if(inputCSVRecord.pointer != 6) throw new IllegalArgumentException("Not enough values in: '"+input+"'");
      return inputCSVRecord;
	}

}
