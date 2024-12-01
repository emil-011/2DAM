package utils;

import java.sql.Timestamp;

public class Film {

	private int film_id;
	private String title;
	private String description;
	private int release_year;
	private int language_id;
	private int original_language_id;
	private int rental_duration;
	private double rental_rate;
	private int length;
	private double replacement_cost;
	private String rating;
	private String special_features;
	private Timestamp last_update;

	public Film(int film_id, String title, String description, int release_year, int language_id,
			int original_language_id, int rental_duration, double rental_rate, int length, double replacement_cost,
			String rating, String special_features, Timestamp last_update) {
		this.film_id = film_id;
		this.title = title;
		this.description = description;
		this.release_year = release_year;
		this.language_id = language_id;
		this.original_language_id = original_language_id;
		this.rental_duration = rental_duration;
		this.rental_rate = rental_rate;
		this.length = length;
		this.replacement_cost = replacement_cost;
		this.rating = rating;
		this.special_features = special_features;
		this.last_update = last_update;

	}
	
	

	public int getFilm_id() {
		return film_id;
	}



	public String getTitle() {
		return title;
	}



	public String getDescription() {
		return description;
	}



	public int getRelease_year() {
		return release_year;
	}



	public int getLanguage_id() {
		return language_id;
	}



	public int getOriginal_language_id() {
		return original_language_id;
	}



	public int getRental_duration() {
		return rental_duration;
	}



	public double getRental_rate() {
		return rental_rate;
	}



	public int getLength() {
		return length;
	}



	public double getReplacement_cost() {
		return replacement_cost;
	}



	public String getRating() {
		return rating;
	}



	public String getSpecial_features() {
		return special_features;
	}



	public Timestamp getLast_update() {
		return last_update;
	}



	@Override
	public String toString() {
		return "Film {" + "\n" + "  film_id: " + film_id + "," + "\n" + "  title: '" + title + "'," + "\n"
				+ "  description: '" + description + "'," + "\n" + "  release_year: " + release_year + "," + "\n"
				+ "  language_id: " + language_id + "," + "\n" + "  original_language_id: " + original_language_id + ","
				+ "\n" + "  rental_duration: " + rental_duration + "," + "\n" + "  rental_rate: " + rental_rate + ","
				+ "\n" + "  length: " + length + "," + "\n" + "  replacement_cost: " + replacement_cost + "," + "\n"
				+ "  rating: '" + rating + "'," + "\n" + "  special_features: '" + special_features + "'," + "\n"
				+ "  last_update: " + last_update + "\n" + "}";
	}

}
