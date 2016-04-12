package main.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage {
	
	private WebDriver driver; //���������� ���������� driver
	
	public AccountPage (WebDriver driver) { //����������� ������
			this.driver = driver;
		}

	By EmailLoginFieldLocator = By.id("Email"); //���� ��� ����� e-mail
	By PasswdLoginFieldLocator = By.id("Passwd"); //���� ��� ����� ������
	By signInButtonLocator = By.id("signIn"); //������ �����
	By sloganLocator = By.cssSelector("h1"); //������ "���� �������. ���� ��� Google!"
	
	
	public void userTypeEmailInLoginForm(String email){ //����� ��� ����� e-mail, ��������� �� ���� ��������� �������� email
		driver.findElement(EmailLoginFieldLocator).click(); //������� ���� ��� ����� ������ � ������� �� ����
		driver.findElement(EmailLoginFieldLocator).clear(); //������� ����
		driver.findElement(EmailLoginFieldLocator).sendKeys(email); //�������� � ���� email
	}
	
	public void userTypePasswdInLoginForm(String passwd){ //����� ��� ����� ������, ���������� ������ ��� ����� e-mail
		driver.findElement(PasswdLoginFieldLocator).click();
		driver.findElement(PasswdLoginFieldLocator).clear();
		driver.findElement(PasswdLoginFieldLocator).sendKeys(passwd);
	}
	
	public void userClickSignInButton(){ //����� ��� ����� �� ������ �����
		driver.findElement(signInButtonLocator).click(); //������� ������� � ������� �� ����
	}
	
	public GmailPage userLogInToYourAccount(String email, String passwd){ //���������� ��� 3 ������ � ����, ������� ���������� ��� ��� ����� ��������
		userTypeEmailInLoginForm(email); //������ email
		userTypePasswdInLoginForm(passwd); //������ �����
		userClickSignInButton(); //������� �����
		return new GmailPage(driver); //�������� �������� �����
	}
	public String getSloganText(){ //����� �������� �������� ����� �������
		return driver.findElement(sloganLocator).getText(); //������� ������ �� �������� � �������� ��� �����
	}
	
}