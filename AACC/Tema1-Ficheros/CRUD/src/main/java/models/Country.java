package models;

import java.util.Objects;

public class Country {
	private String code;
	private String name;
	private String continent;
	private String region;
	private double surfaceArea;
	private int indepYear;
	private int population;
	private double lifeExpectancy;
	private double gnp;
	private double gnpOld;
	private String localName;
	private String governmentForm;
	private String headOfState;
	private int capital;
	private String code2;

	// Constructor
	public Country(String code, String name, String continent, String region, double surfaceArea, int indepYear,
			int population, double lifeExpectancy, double gnp, double gnpOld, String localName, String governmentForm,
			String headOfState, int capital, String code2) {
		this.code = code;
		this.name = name;
		this.continent = continent;
		this.region = region;
		this.surfaceArea = surfaceArea;
		this.indepYear = indepYear;
		this.population = population;
		this.lifeExpectancy = lifeExpectancy;
		this.gnp = gnp;
		this.gnpOld = gnpOld;
		this.localName = localName;
		this.governmentForm = governmentForm;
		this.headOfState = headOfState;
		this.capital = capital;
		this.code2 = code2;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public String getContinent() {
		return continent;
	}

	public String getRegion() {
		return region;
	}

	public double getSurfaceArea() {
		return surfaceArea;
	}

	public int getIndepYear() {
		return indepYear;
	}

	public int getPopulation() {
		return population;
	}

	public double getLifeExpectancy() {
		return lifeExpectancy;
	}

	public double getGnp() {
		return gnp;
	}

	public double getGnpOld() {
		return gnpOld;
	}

	public String getLocalName() {
		return localName;
	}

	public String getGovernmentForm() {
		return governmentForm;
	}

	public String getHeadOfState() {
		return headOfState;
	}

	public int getCapital() {
		return capital;
	}

	public String getCode2() {
		return code2;
	}

	@Override
	public int hashCode() {
		return Objects.hash(capital, code, code2, continent, gnp, gnpOld, governmentForm, headOfState, indepYear,
				lifeExpectancy, localName, name, population, region, surfaceArea);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return capital == other.capital && Objects.equals(code, other.code) && Objects.equals(code2, other.code2)
				&& Objects.equals(continent, other.continent)
				&& Double.doubleToLongBits(gnp) == Double.doubleToLongBits(other.gnp)
				&& Double.doubleToLongBits(gnpOld) == Double.doubleToLongBits(other.gnpOld)
				&& Objects.equals(governmentForm, other.governmentForm)
				&& Objects.equals(headOfState, other.headOfState) && indepYear == other.indepYear
				&& Double.doubleToLongBits(lifeExpectancy) == Double.doubleToLongBits(other.lifeExpectancy)
				&& Objects.equals(localName, other.localName) && Objects.equals(name, other.name)
				&& population == other.population && Objects.equals(region, other.region)
				&& Double.doubleToLongBits(surfaceArea) == Double.doubleToLongBits(other.surfaceArea);
	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + ", continent=" + continent + ", region=" + region
				+ ", surfaceArea=" + surfaceArea + ", indepYear=" + indepYear + ", population=" + population
				+ ", lifeExpectancy=" + lifeExpectancy + ", gnp=" + gnp + ", gnpOld=" + gnpOld + ", localName="
				+ localName + ", governmentForm=" + governmentForm + ", headOfState=" + headOfState + ", capital="
				+ capital + ", code2=" + code2 + "]";
	}

}
