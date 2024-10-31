package models;

import java.util.Objects;

public class City {

	private String name;
	private String countryCode;
	private String district;
	private int population;
	private int id;

	public City(int id, String name, String countryCode, String district, int population) {
		this.id = id;
		this.name = name;
		this.countryCode = countryCode;
		this.district = district;
		this.population = population;
	}

	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getDistrict() {
		return district;
	}

	public int getPopulation() {
		return population;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		City other = (City) obj;
		return Objects.equals(countryCode, other.countryCode) && Objects.equals(district, other.district)
				&& Objects.equals(name, other.name) && population == other.population;
	}

	@Override
	public int hashCode() {
		return Objects.hash(countryCode, district, name, population);
	}

	@Override
	public String toString() {
		return "City: " + name + " | Country Code: " + countryCode + " | District: " + district + " | Population: "
				+ population;
	}

}
