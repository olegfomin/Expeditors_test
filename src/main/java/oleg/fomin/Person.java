package oleg.fomin;

import java.util.Objects;

/* Contains information about a person like name and address plus their age */
public class Person implements Comparable<Person> {
	private String lastName;
	private String firstName;
	private String address;
	private int age;

	public Person() {
		
	}
	
	public Person(String lastName, String firstName, String address, int age) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.age = age;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getAddress() {
		return address;
	}

	public int getAge() {
		return age;
	}

	@Override
	public int compareTo(Person that) {
		int result4LastName = this.lastName.compareTo(that.lastName);
		if(result4LastName != 0) return result4LastName;
		return this.firstName.compareTo(that.firstName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}
	
    @Override
    public String toString() {
    	return "\""+lastName+"\","+"\""+firstName+"\",\""+address+"\","+"\""+age+"\"";
    }

}
