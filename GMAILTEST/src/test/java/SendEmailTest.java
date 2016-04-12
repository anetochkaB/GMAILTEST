package test.java;

import static org.junit.Assert.assertEquals;
import main.java.AccountPage;
import main.java.GmailPage;

import org.junit.Test;

public class SendEmailTest extends TestSettings{ //��������� ��� ����� �������� ����������� ������ TestSettings
		
	@Test //��������� �����
	public void userPetrovCanSendEmail(){
		AccountPage ap = new AccountPage(driver); //������� ����� ��������� ap ������ AccountPage 
		//��������� ����� userLogInToYourAccount ������� ������ ��� ��������� mailpage ������ GmailPage, �� ���� �������� �������� petrov_email � passw ����� ������������ � TestSettings,
		GmailPage mailpage = ap.userLogInToYourAccount(petrov_email, passw);  
		mailpage.userOpenProfile(); //����� userOpenProfile() �� ������ GmailPage ������� ��������� ������� ������������
		assertEquals("���� ������",mailpage.getUserNameFromProfile()); //��������� ��� ��� ������������ ������� ���� ���� ������
		assertEquals("ssp.trew@gmail.com",mailpage.getUserEmailFromProfile()); //��������� ������������ ����������� email 
		mailpage.userClickNewEmailButton(); //�������� ������ �������� ������ ���������
		mailpage.userWriteAndSendEmail(sidorov_email, subject, text); //����� � ���������� ���������, sidorov_email, subject, text ���� ����� ���������� � TestSettings
		assertEquals("����������� ���������", mailpage.getTextFromConfirmMessage()); //���������� ��� ��������� ���������� (���� ��������� �������������)
		AccountPage ap1 = mailpage.userLogOut(); //������� �� ������� �� �������� ���������
		assertEquals("���� �������. ���� ��� Google!", ap1.getSloganText()); //����� ������ � ���������� ��� �� �� �������� ���������
	}
}
