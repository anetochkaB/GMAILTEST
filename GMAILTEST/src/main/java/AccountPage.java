package main.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
	
	private WebDriver driver; //определяем переменную driver
	
	public AccountPage (WebDriver driver) { //конструктор класса
			this.driver = driver;
		}

	By EmailLoginFieldLocator = By.id("Email"); //поле для ввода e-mail
	By PasswdLoginFieldLocator = By.id("Passwd"); //поле для ввода пароля
	By signInButtonLocator = By.id("signIn"); //кнопка Войти
	By sloganLocator = By.cssSelector("h1"); //слоган "Один аккаунт. Весь мир Google!"
	
	
	public void userTypeEmailInLoginForm(String email){ //метод для ввода e-mail, принимает на вход строковый параметр email
		driver.findElement(EmailLoginFieldLocator).click(); //находим поле для ввода пароля и кликаем по нему
		driver.findElement(EmailLoginFieldLocator).clear(); //очищаем поле
		driver.findElement(EmailLoginFieldLocator).sendKeys(email); //передаем в него email
	}
	
	public void userTypePasswdInLoginForm(String passwd){ //метод для ввода пароля, аналогично методу для ввода e-mail
		driver.findElement(PasswdLoginFieldLocator).click();
		driver.findElement(PasswdLoginFieldLocator).clear();
		driver.findElement(PasswdLoginFieldLocator).sendKeys(passwd);
	}
	
	public void userClickSignInButton(){ //метод для клика по кнопке войти
		driver.findElement(signInButtonLocator).click(); //находим элемент и кликаем по нему
	}
	
	public GmailPage userLogInToYourAccount(String email, String passwd){ //объединяем все 3 метода в один, который возвращает нам уже новую страницу
		userTypeEmailInLoginForm(email); //вводим email
		userTypePasswdInLoginForm(passwd); //вводим проль
		userClickSignInButton(); //кликаем войти
		return new GmailPage(driver); //получаем страницу почты
	}
	public String getSloganText(){ //метод вкотором получаем текст слогана
		return driver.findElement(sloganLocator).getText(); //находим слоган на странице и получаем его текст
	}
	
}