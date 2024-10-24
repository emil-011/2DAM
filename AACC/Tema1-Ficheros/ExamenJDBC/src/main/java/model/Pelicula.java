package model;

import java.time.Year;

public class Pelicula {

	private int film_id;
	private String title;
	private String description;
	private int release_year;
	private int language_id;
	private int length;
	private String rating;

	public Pelicula(int film_id, String title, String description, int release_year, int language_id, int length,
			String rating) {
		this.film_id = film_id;
		this.title = title;
		this.description = description;
		this.release_year = release_year;
		this.language_id = language_id;
		this.length = length;
		this.rating = rating;
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

	public int getLength() {
		return length;
	}

	public String getRating() {
		return rating;
	}

}
