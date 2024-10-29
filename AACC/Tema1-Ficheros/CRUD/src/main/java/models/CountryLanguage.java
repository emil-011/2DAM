package models;

import java.util.Objects;

public class CountryLanguage {

	private String countryCode;
	private String language;
	private String isOficial;
	private double percentege;

	public CountryLanguage(String countryCode, String language, String isOficial, double percentege) {
		this.countryCode = countryCode;
		this.language = language;
		this.isOficial = isOficial;
		this.percentege = percentege;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public String getLanguage() {
		return language;
	}

	public String getIsOficial() {
		return isOficial;
	}

	public double getPercentege() {
		return percentege;
	}

	@Override
	public int hashCode() {
		return Objects.hash(countryCode, isOficial, language, percentege);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryLanguage other = (CountryLanguage) obj;
		return Objects.equals(countryCode, other.countryCode) && Objects.equals(isOficial, other.isOficial)
				&& Objects.equals(language, other.language)
				&& Double.doubleToLongBits(percentege) == Double.doubleToLongBits(other.percentege);
	}

	@Override
	public String toString() {
		return "CountryLanguage [countryCode=" + countryCode + ", language=" + language + ", isOficial=" + isOficial
				+ ", percentege=" + percentege + "]";
	}

}
