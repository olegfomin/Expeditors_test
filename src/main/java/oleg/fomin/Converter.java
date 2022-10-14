package oleg.fomin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/* This is the helper class that contains miscellaneous converter function. TODO: This class maybe split
 * in the future to contain functions that are relevant to the different use-case */
public class Converter {
	
	private static final int CUTOVER_AGE = 18;
	
	public static class HouseHoldOrig2Count {
	  private String  household;
	  private Integer count=0;
	  
      public HouseHoldOrig2Count(String household, Integer count) {
		super();
		this.household = household;
		this.count = count;
	  }
	  public String getHousehold() {
		return household;
	  }
	  public Integer getCount() {
		return count;
	  }
	  @Override
	  public int hashCode() {
		return Objects.hash(household);
	  }
	  @Override
      public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HouseHoldOrig2Count other = (HouseHoldOrig2Count) obj;
		return Objects.equals(household, other.household);
	  }
	}
	
	
	public Map<Household, HouseHoldOrig2Count> countHouseHolds(List<InputCSVRecord> csvRecordList) {
		Map <Household, HouseHoldOrig2Count> houseHoldCanonical2hhcMap = new HashMap<>();
		for(InputCSVRecord ir : csvRecordList) {
			Household houseHoldOriginal = new Household(ir.getStreetAddress(), ir.getCity(), ir.getState());
			Household houseHoldCanonical = new Household(ir.getStreetAddressCanonical(), ir.getCityCanonical(), ir.getStateCanonical());
			HouseHoldOrig2Count hhTuple = houseHoldCanonical2hhcMap.get(houseHoldCanonical);
			if(hhTuple != null) {
				Integer newCount = hhTuple.getCount()+1;
				HouseHoldOrig2Count newTuple = new HouseHoldOrig2Count(hhTuple.getHousehold(), newCount);
				houseHoldCanonical2hhcMap.put(houseHoldCanonical, newTuple);
			} else {
				houseHoldCanonical2hhcMap.put(houseHoldCanonical, new HouseHoldOrig2Count(houseHoldOriginal.toString(),1));
			}
			
		}
	    return houseHoldCanonical2hhcMap;	
	}
	
	public List<String> convertIntoHHCSVList(Map<Household, HouseHoldOrig2Count> inputMap) {
		List<String> output = new ArrayList<>();
		for(Map.Entry<Household,HouseHoldOrig2Count> entry : inputMap.entrySet()) {
			output.add(entry.getValue().getHousehold()+",\""+entry.getValue().getCount()+"\"");
		}
		return output;
	}
	
	public List<String> convertIntoPersonListOverAge(List<InputCSVRecord> csvRecordList) {
		List<Person> personList = new ArrayList<>();
		for(InputCSVRecord csvRecord : csvRecordList) {
			if(csvRecord.getAgeAsInt() > CUTOVER_AGE) {
				String address = csvRecord.getStreetAddress()+","+csvRecord.getCity()+","+csvRecord.getState(); 
				Person person = new Person(csvRecord.getLastName(), csvRecord.getFirstName(), address, csvRecord.getAgeAsInt());
				personList.add(person);
			}
		}
		Collections.sort(personList);
		List<String> csvList = new ArrayList<>();
		for(Person person : personList) {
			csvList.add(person.toString());
		}
		
		return csvList;
		
	}

}
