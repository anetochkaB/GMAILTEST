package main.java;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GmailPage {
private WebDriver driver;
	
	public GmailPage(WebDriver driver) {
			this.driver = driver;
		}
	By profileArrowsLocator = By.className("gb_aa"); //стрелка для раскрытия профиля пользователя
	By profileNameLocator = By.className("gb_pa"); //имя пользователя из профиля 
	By profileEmailLocator = By.className("gb_qa"); //e-mail из профиля
	By newEmailLinkLocator = By.xpath("//div[text()=\"НАПИСАТЬ\"]"); //кнопка написать письмо
	By toFieldLocator = By.name("to"); //поле кому в окне создания нового сообщения
	By subjectFieldLocator = By.name("subjectbox"); //поле тема в окне создания нового сообщения
	By textMailLocator = By.cssSelector("div[aria-label=\"Тело письма\"]"); //текст письма в окне создания нового сообщения
	By sendEmailLinkLocator = By.xpath("//div[text()=\"Отправить\"]"); //кнопка Отправить сообщение
	By sendConfirmMessageLocator = By.id("link_vsm"); //сообщение подтверждение "Письмо отправлено. Просмотреть сообщение"
	By exitButtonLocator = By.linkText("Выйти"); //кнопка Выйти из почты
	By inboxButtonLocator = By.cssSelector("a[title*=\"Входящие\"]"); //ссылка "Входящие"
	By fromPetrovMailLocator = By.xpath("//div[2]/span[text()=\"Петр Петров\"]"); //новое письмо от адресата Петр Петров
	By inboxMailTextLocator = By.cssSelector("div>div[dir=ltr]"); //текст письма, открытого из папки входящие
	
	public void userOpenProfile(){ //метод для открытия профиля пользователя
		driver.findElement(profileArrowsLocator).click(); //находим стрелочку в правом верхнем углу экрана и кликаем
	}
	
	public String getUserNameFromProfile(){
		return driver.findElement(profileNameLocator).getText(); //получаем имя пользователя из профиля
	}
	
	public String getUserEmailFromProfile(){
		return driver.findElement(profileEmailLocator).getText(); //получаем email пользователя из профиля 
	}
	
		
	public static Boolean isElementPresent(WebDriver driver, By element, int i) { //создаем метод, который будет проверять наличие определенного элемента element на странице.
		try{
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //сбрасываем неявные ожидания
			(new WebDriverWait(driver, i)) //устанавливаем явные ожидания длительностью i (передаем во входных параметрах)
			          .until(ExpectedConditions
			                    .visibilityOfElementLocated(element)); //проверяем наличие и видимость элемента на странице
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); //устанавливаем неявные ожидания
			return true; //если элемент есть на странице возвращаем true
		} catch (TimeoutException e) {
			return false; //если элемента нет - возвращаем false
		}
	}

	public void userClickNewEmailButton(){ //метод для клика кнопки написать сообщение
		driver.findElement(newEmailLinkLocator).click();
	}
	public void userTypeMailRecepient(String recepient){ //меод для написания получателя сообщения
		driver.findElement(toFieldLocator).click(); //находим элемент кому и кликаем
		driver.findElement(toFieldLocator).clear(); //очищаем элемент
		driver.findElement(toFieldLocator).sendKeys(recepient); //передаем в элемент e-mail получателя
	}
	public void userTypeMailSubject(String subject){ //метод для написания темы сообщения
		driver.findElement(subjectFieldLocator).click();
		driver.findElement(subjectFieldLocator).clear();
		driver.findElement(subjectFieldLocator).sendKeys(subject);
	}
	public void userTypeTextMail(String text){ //метод для написания текста письма
		driver.findElement(textMailLocator).click();
		driver.findElement(textMailLocator).clear();
		driver.findElement(textMailLocator).sendKeys(text);
	}
	public void userClickSendButton(){ //метод для клика по кнопке отправить
		driver.findElement(sendEmailLinkLocator).click();
	}
	
	public void userWriteAndSendEmail(String recepient, String subject, String text){ //общий метод для написания и отправки сообщений
		userTypeMailRecepient(recepient); //еаписали кому
		userTypeMailSubject(subject); //написали тему
		userTypeTextMail(text); //написали текст
		userClickSendButton(); //отправили сообщение
	}
	public String getTextFromConfirmMessage(){ //метод для получения текста из сообщения подтверждения об отправке
		return driver.findElement(sendConfirmMessageLocator).getText();
	}
	public void userClickExitButton(){ //метод для нажатия кнопки Выйти из аккаунта
		driver.findElement(exitButtonLocator).click();
	}
	
	public AccountPage userLogOut(){ //метод выхода из аккаунта
		userOpenProfile(); //открыли профиль
		userClickExitButton(); //нажали кнопку выйти
		return new AccountPage(driver); //получили страницу аккаунтов
	}
	public void userClickInboxLocator(){ //метод для нажатия ссылки входящие
		driver.findElement(inboxButtonLocator).click();
	}
	public Boolean userMustSeeMailFromPetrov(){ //метод в котором пользователь Сидоров ждет появления письма от Петрова
		int i=0; //задаем счетчик
		while (isElementPresent(driver, fromPetrovMailLocator, 5)==false && i<10) { //каждые 5 секунд проверяем наличие в папке входящие сообщения от петрова и делаем так 11 раз (= ждем одну минуту)
			userClickInboxLocator(); // обновляем входящие
			i++; //увеличиваем счетчик, когда 
			}
		return isElementPresent(driver, fromPetrovMailLocator, 5); //возвращаем true или false в зависимости от того есть письмо или нет
	}
	public void userOpenMail(){ //метод для открытия письма
		driver.findElement(fromPetrovMailLocator).click(); //находим письмо от Петрова и открываем его (делаем клик)
	}
	
	public String getMailText(){ //метод для получения текста письма
		return driver.findElement(inboxMailTextLocator).getText();
	}

}
