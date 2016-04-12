package test.java;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestSettings {
	
	protected WebDriver driver; //��������� ���������� driver ������ WebDriver
	
	String petrov_email = "ssp.trew@gmail.com"; //e-mail �������
	String sidorov_email = "azxs.cdr@gmail.com"; //e-mail ��������
	String passw = "1qw23er45ty6"; //������ � ����� ���������
	String subject = "This is test email"; //���� ������
	String text = "Hi! How Are You?"; //����� ������	

	
	@Before //��������� ��, ��� ������ ����������� ����� ������ �������� ������ �����
	public void setUp(){
		driver = new FirefoxDriver(); //���������� ��� ����� ������������ Firefox
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS); //���������� ������� ��������
		driver.manage().window().maximize(); //��������� ��, ��� ���� �������� ������ ���������� �� ���� �����
		driver.get("https://mail.google.com"); //��������� ��������
	
	}
	@After //��� ��������� ��, ��� ������ ����������� ����� ���������� �����
	public void tearDown() {
		driver.close(); //��������� �������
	}
}

