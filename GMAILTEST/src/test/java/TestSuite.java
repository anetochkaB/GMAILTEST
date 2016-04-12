package test.java;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({SendEmailTest.class, ReceiveEmailTest.class}) //указываем порядок запуска классов с тестами


public class TestSuite {
	public static void main(String[] args) {		
	}
}
