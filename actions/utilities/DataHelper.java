package utilities;

import java.util.Random;

import com.github.javafaker.Faker;

public class DataHelper {
	private Faker faker;

	public DataHelper() {
		faker = new Faker();
	}

	public static DataHelper getData() {
		return new DataHelper();
	}

	public String getFirstName() {
		return faker.name().firstName();
	}

	public String getLastName() {
		return faker.name().lastName();
	}

	public String getEmailAddress() {
		return faker.internet().emailAddress();
	}

	public String getUserName() {
		return faker.name().username();
	}

	public String getPassword() {
		return faker.internet().password();
	}

	public String getCompany() {
		return faker.company().name();
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
