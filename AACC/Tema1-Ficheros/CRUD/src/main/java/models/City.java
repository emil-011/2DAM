package models;

import java.util.Objects;

public class City {

	private String name;
	private String countryCode;
	private String district;
	private int population;

	public City(String name, String countryCode, String district, int population) {
		this.name = name;
		this.countryCode = countryCode;
		this.district = district;
		this.population = population;
	}

	public String getName() {
		return name;
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
