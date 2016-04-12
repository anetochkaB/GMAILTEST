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
	By profileArrowsLocator = By.className("gb_aa"); //������� ��� ��������� ������� ������������
	By profileNameLocator = By.className("gb_pa"); //��� ������������ �� ������� 
	By profileEmailLocator = By.className("gb_qa"); //e-mail �� �������
	By newEmailLinkLocator = By.xpath("//div[text()=\"��������\"]"); //������ �������� ������
	By toFieldLocator = By.name("to"); //���� ���� � ���� �������� ������ ���������
	By subjectFieldLocator = By.name("subjectbox"); //���� ���� � ���� �������� ������ ���������
	By textMailLocator = By.cssSelector("div[aria-label=\"���� ������\"]"); //����� ������ � ���� �������� ������ ���������
	By sendEmailLinkLocator = By.xpath("//div[text()=\"���������\"]"); //������ ��������� ���������
	By sendConfirmMessageLocator = By.id("link_vsm"); //��������� ������������� "������ ����������. ����������� ���������"
	By exitButtonLocator = By.linkText("�����"); //������ ����� �� �����
	By inboxButtonLocator = By.cssSelector("a[title*=\"��������\"]"); //������ "��������"
	By fromPetrovMailLocator = By.xpath("//div[2]/span[text()=\"���� ������\"]"); //����� ������ �� �������� ���� ������
	By inboxMailTextLocator = By.cssSelector("div>div[dir=ltr]"); //����� ������, ��������� �� ����� ��������
	
	public void userOpenProfile(){ //����� ��� �������� ������� ������������
		driver.findElement(profileArrowsLocator).click(); //������� ��������� � ������ ������� ���� ������ � �������
	}
	
	public String getUserNameFromProfile(){
		return driver.findElement(profileNameLocator).getText(); //�������� ��� ������������ �� �������
	}
	
	public String getUserEmailFromProfile(){
		return driver.findElement(profileEmailLocator).getText(); //�������� email ������������ �� ������� 
	}
	
		
	public static Boolean isElementPresent(WebDriver driver, By element, int i) { //������� �����, ������� ����� ��������� ������� ������������� �������� element �� ��������.
		try{
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); //���������� ������� ��������
			(new WebDriverWait(driver, i)) //������������� ����� �������� ������������� i (�������� �� ������� ����������)
			          .until(ExpectedConditions
			                    .visibilityOfElementLocated(element)); //��������� ������� � ��������� �������� �� ��������
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); //������������� ������� ��������
			return true; //���� ������� ���� �� �������� ���������� true
		} catch (TimeoutException e) {
			return false; //���� �������� ��� - ���������� false
		}
	}

	public void userClickNewEmailButton(){ //����� ��� ����� ������ �������� ���������
		driver.findElement(newEmailLinkLocator).click();
	}
	public void userTypeMailRecepient(String recepient){ //���� ��� ��������� ���������� ���������
		driver.findElement(toFieldLocator).click(); //������� ������� ���� � �������
		driver.findElement(toFieldLocator).clear(); //������� �������
		driver.findElement(toFieldLocator).sendKeys(recepient); //�������� � ������� e-mail ����������
	}
	public void userTypeMailSubject(String subject){ //����� ��� ��������� ���� ���������
		driver.findElement(subjectFieldLocator).click();
		driver.findElement(subjectFieldLocator).clear();
		driver.findElement(subjectFieldLocator).sendKeys(subject);
	}
	public void userTypeTextMail(String text){ //����� ��� ��������� ������ ������
		driver.findElement(textMailLocator).click();
		driver.findElement(textMailLocator).clear();
		driver.findElement(textMailLocator).sendKeys(text);
	}
	public void userClickSendButton(){ //����� ��� ����� �� ������ ���������
		driver.findElement(sendEmailLinkLocator).click();
	}
	
	public void userWriteAndSendEmail(String recepient, String subject, String text){ //����� ����� ��� ��������� � �������� ���������
		userTypeMailRecepient(recepient); //�������� ����
		userTypeMailSubject(subject); //�������� ����
		userTypeTextMail(text); //�������� �����
		userClickSendButton(); //��������� ���������
	}
	public String getTextFromConfirmMessage(){ //����� ��� ��������� ������ �� ��������� ������������� �� ��������
		return driver.findElement(sendConfirmMessageLocator).getText();
	}
	public void userClickExitButton(){ //����� ��� ������� ������ ����� �� ��������
		driver.findElement(exitButtonLocator).click();
	}
	
	public AccountPage userLogOut(){ //����� ������ �� ��������
		userOpenProfile(); //������� �������
		userClickExitButton(); //������ ������ �����
		return new AccountPage(driver); //�������� �������� ���������
	}
	public void userClickInboxLocator(){ //����� ��� ������� ������ ��������
		driver.findElement(inboxButtonLocator).click();
	}
	public Boolean userMustSeeMailFromPetrov(){ //����� � ������� ������������ ������� ���� ��������� ������ �� �������
		int i=0; //������ �������
		while (isElementPresent(driver, fromPetrovMailLocator, 5)==false && i<10) { //������ 5 ������ ��������� ������� � ����� �������� ��������� �� ������� � ������ ��� 11 ��� (= ���� ���� ������)
			userClickInboxLocator(); // ��������� ��������
			i++; //����������� �������, ����� 
			}
		return isElementPresent(driver, fromPetrovMailLocator, 5); //���������� true ��� false � ����������� �� ���� ���� ������ ��� ���
	}
	public void userOpenMail(){ //����� ��� �������� ������
		driver.findElement(fromPetrovMailLocator).click(); //������� ������ �� ������� � ��������� ��� (������ ����)
	}
	
	public String getMailText(){ //����� ��� ��������� ������ ������
		return driver.findElement(inboxMailTextLocator).getText();
	}

}
