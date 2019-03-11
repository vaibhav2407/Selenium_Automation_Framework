package com.training.utility;

public interface Driver {
	// KEYS 
	String CHROME ="webdriver.chrome.driver"; 
	String FIREFOX ="webdriver.gecko.driver"; 
	String IE ="webdriver.ie.driver";
	String PHANTOM="phantomjs.binary.path";
	
	// PATH 
	String CHROME_PATH= System.getProperty("user.dir") + "//drivers//chromedriver_73.exe";
	String FIREFOX_PATH =System.getProperty("user.dir") + "//drivers//geckodriver_024.exe";
	String IE_PATH ="";
	String PHANTOM_PATH="";
	
}