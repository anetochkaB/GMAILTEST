package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import main.java.AccountPage;
import main.java.GmailPage;

import org.junit.Test;

public class ReceiveEmailTest extends TestSettings{ //наследуем класс TestSettings
	
	
	@Test
	public void userSidorovCanReceiveEmail() {
		AccountPage ap = new AccountPage(driver);
		GmailPage mailpage = ap.userLogInToYourAccount(sidorov_email, passw); //авторизуемся под Сидоровым
		mailpage.userOpenProfile();
		assertEquals("Сергей Сидоров",mailpage.getUserNameFromProfile()); //проверяем что на самом деле Сидоров
		assertEquals("azxs.cdr@gmail.com",mailpage.getUserEmailFromProfile());
		mailpage.userClickInboxLocator();
		assertTrue(mailpage.userMustSeeMailFromPetrov()); //проверка на то, что в ящике есть письмо от Петрова
		mailpage.userOpenMail(); //открывает полученное письмо
		assertEquals(text, mailpage.getMailText()); //проверяем что текст письма корректен
		AccountPage ap1 = mailpage.userLogOut();
		assertEquals("Один аккаунт. Весь мир Google!", ap1.getSloganText());
	}
}
