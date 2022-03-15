package com.cub2.org;

public class Auto extends Baseclass {
	public static void main(String[] args) throws InterruptedException {
		
		//Web driver code:
	
		
		//To get test data from excel
		Excelmethod reader = new Excelmethod("C:/Users/AR20326647/Downloads/adactin.xlsx");
		int rowCount = reader.getRowCount("login");
		System.out.println(rowCount);
		
		
		//parameterized
		for (int i = 2; i <= rowCount; i++) {
			String user = reader.getCellData("login", "username", i);
			System.out.println(user);
			
			String pass = reader.getCellData("login", "password", i);
			System.out.println(pass);
			
			browser("edge");
			url("http://automationpractice.com/index.php?controller=authentication&back=my-account");
			
		    findelement("xpath", "//*[@id=\"email\"]").sendKeys(user);
		    
		    findelement("xpath","//*[@id=\"passwd\"]").sendKeys(pass);
		    
		    Thread.sleep(3000);
		    findelement("xpath", "//*[@id=\"SubmitLogin\"]").click();
		} 
		
		
		
		
	}

}
