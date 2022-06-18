package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.user.UserCustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage {

	private WebDriver driver;

	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectGenderRadio(String gender) {
		if (gender.equalsIgnoreCase("male")) {
			waitForAllElementVisible(driver, UserCustomerInfoPageUI.MALE_RADIO_BUTTON);
			checkTheCheckboxOrRadio(driver, UserCustomerInfoPageUI.MALE_RADIO_BUTTON);
		} else if (gender.equalsIgnoreCase("female")) {
			waitForAllElementVisible(driver, UserCustomerInfoPageUI.FEMALE_RADIO_BUTTON);
			checkTheCheckboxOrRadio(driver, UserCustomerInfoPageUI.FEMALE_RADIO_BUTTON);
		} else {
			throw new RuntimeException("Invalid Gender");
		}
	}

	public void selectItemInBirthDayDropdown(String birthDay) {
		selectItemInDefaultDropdown(driver, UserCustomerInfoPageUI.BIRTH_DAY_DROPDOWN, birthDay);
	}

	public void selectItemInBirthMonthDropdown(String birthMonth) {
		selectItemInDefaultDropdown(driver, UserCustomerInfoPageUI.BIRTH_MONTH_DROPDOWN, birthMonth);

	}

	public void selectItemInBirthYearDropdown(String birthYear) {
		selectItemInDefaultDropdown(driver, UserCustomerInfoPageUI.BIRTH_YEAR_DROPDOWN, birthYear);

	}

	public void clickToSaveButton() {
		waitForElementClickable(driver, UserCustomerInfoPageUI.SAVE_BUTTON);
		clickToElement(driver, UserCustomerInfoPageUI.SAVE_BUTTON);
	}

	public boolean isGenderRadioChecked(String gender) {
		if (gender.equalsIgnoreCase("male")) {
			waitForAllElementVisible(driver, UserCustomerInfoPageUI.MALE_RADIO_BUTTON);
			return isElementSelected(driver, UserCustomerInfoPageUI.MALE_RADIO_BUTTON);
		} else if (gender.equalsIgnoreCase("female")) {
			waitForAllElementVisible(driver, UserCustomerInfoPageUI.FEMALE_RADIO_BUTTON);
			return isElementSelected(driver, UserCustomerInfoPageUI.FEMALE_RADIO_BUTTON);
		} else {
			throw new RuntimeException("Invalid Gender");
		}
	}

	public String getSelectedItemInDropdownBirthDay() {
		return getSelectedItemInDropdown(driver, UserCustomerInfoPageUI.BIRTH_DAY_DROPDOWN);
	}

	public String getSelectedItemInDropdownBirthMonth() {
		return getSelectedItemInDropdown(driver, UserCustomerInfoPageUI.BIRTH_MONTH_DROPDOWN);
	}

	public String getSelectedItemInDropdownBirthYear() {
		return getSelectedItemInDropdown(driver, UserCustomerInfoPageUI.BIRTH_YEAR_DROPDOWN);
	}

}
