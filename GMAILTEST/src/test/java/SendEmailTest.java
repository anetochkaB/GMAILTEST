package test.java;

import static org.junit.Assert.assertEquals;
import main.java.AccountPage;
import main.java.GmailPage;

import org.junit.Test;

public class SendEmailTest extends TestSettings{ //указываем что класс является наследником класса TestSettings
		
	@Test //аннотация теста
	public void userPetrovCanSendEmail(){
		AccountPage ap = new AccountPage(driver); //создаем новый экземпляр ap класса AccountPage 
		//выполняем метод userLogInToYourAccount который вернет нам экземпляр mailpage класса GmailPage, на вход которого передаем petrov_email и passw ранее определенные в TestSettings,
		GmailPage mailpage = ap.userLogInToYourAccount(petrov_email, passw);  
		mailpage.userOpenProfile(); //метод userOpenProfile() из класса GmailPage который открывает профиль пользователя
		assertEquals("Петр Петров",mailpage.getUserNameFromProfile()); //проверяем что имя пользователя насамом деле Петр Петров
		assertEquals("ssp.trew@gmail.com",mailpage.getUserEmailFromProfile()); //проверяем корректность отображения email 
		mailpage.userClickNewEmailButton(); //нажимаем кнопку создания нового сообщения
		mailpage.userWriteAndSendEmail(sidorov_email, subject, text); //пишем и отправляем сообщение, sidorov_email, subject, text были ранее определены в TestSettings
		assertEquals("Просмотреть сообщение", mailpage.getTextFromConfirmMessage()); //убеждаемся что сообщение отправлено (есть сообщение подтверждение)
		AccountPage ap1 = mailpage.userLogOut(); //выходим из системы на страницу аккаунтов
		assertEquals("Один аккаунт. Весь мир Google!", ap1.getSloganText()); //видим слоган и убеждаемся что мы на странице аккаунтов
	}
}
