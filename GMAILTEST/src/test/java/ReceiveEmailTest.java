package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import main.java.AccountPage;
import main.java.GmailPage;

import org.junit.Test;

public class ReceiveEmailTest extends TestSettings{ //��������� ����� TestSettings
	
	
	@Test
	public void userSidorovCanReceiveEmail() {
		AccountPage ap = new AccountPage(driver);
		GmailPage mailpage = ap.userLogInToYourAccount(sidorov_email, passw); //������������ ��� ���������
		mailpage.userOpenProfile();
		assertEquals("������ �������",mailpage.getUserNameFromProfile()); //��������� ��� �� ����� ���� �������
		assertEquals("azxs.cdr@gmail.com",mailpage.getUserEmailFromProfile());
		mailpage.userClickInboxLocator();
		assertTrue(mailpage.userMustSeeMailFromPetrov()); //�������� �� ��, ��� � ����� ���� ������ �� �������
		mailpage.userOpenMail(); //��������� ���������� ������
		assertEquals(text, mailpage.getMailText()); //��������� ��� ����� ������ ���������
		AccountPage ap1 = mailpage.userLogOut();
		assertEquals("���� �������. ���� ��� Google!", ap1.getSloganText());
	}
}
