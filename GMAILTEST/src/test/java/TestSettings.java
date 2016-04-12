package test.java;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestSettings {
	
	protected WebDriver driver; //объявляем переменную driver класса WebDriver
	
	String petrov_email = "ssp.trew@gmail.com"; //e-mail Петрова
	String sidorov_email = "azxs.cdr@gmail.com"; //e-mail Сидорова
	String passw = "1qw23er45ty6"; //пароль к обоим аккаунтам
	String subject = "This is test email"; //тема письма
	String text = "Hi! How Are You?"; //текст письма	

	
	@Before //объявляем то, что должно выполниться перед каждым запуском нового теста
	public void setUp(){
		driver = new FirefoxDriver(); //определяем что будем использовать Firefox
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); //определяем неявные ожидания
		driver.manage().window().maximize(); //указываем то, что окно браузера должно раскрыться во весь экран
		driver.get("https://mail.google.com"); //открываем страницу
	
	}
	@After //тут укажываем то, что должно выполниться после завершения теста
	public void tearDown() {
		driver.close(); //закрываем браузер
	}
}

