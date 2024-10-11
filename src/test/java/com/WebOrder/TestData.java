package com.WebOrder;

import org.testng.annotations.DataProvider;

public class TestData {
	@DataProvider(name = "Login")
	public Object[][] getDataforLogin() {
		// Multidimensional Object
		// 3X3 or 4X3 or 4X5 or 2X4
		return new Object[][] {

				{"Tester", "test" },
				{"Tester", "test" },
				{"Tester", "test" },
				{"Tester", "test" }
				};

	}
	
	@DataProvider(name = "WebOrder_LoginAll_TCs")
	public Object[][] getLogin_All_TCs_Scenarios() {
		// Multidimensional Object
		// 3X3 or 4X3 or 4X5
		return new Object[][] {

				{"Tester", "test","Logout" },
				{"Tester1", "test","Invalid Login or Password." },
				{"Tester", "test1","Invalid Login or Password." },
				{"", "test","Invalid Login or Password." },
				{"Tester", "","Invalid Login or Password." }
				};

	}
	@DataProvider(name = "WebOrder_All_Orders_TC")
	public Object[][] getOrders_All_TCs_Scenarios() {
		// Multidimensional Object
		// 3X3 or 4X3 or 4X5
		return new Object[][] {
				//quantity,  discount, name, street, city, state, zip, cardNo, , expiry, Expected Result
				//Correct order
				{"1", "5","Andrew V", "123 Main St", "Washington", "DC", "20010", "123456789", "12/24", "New order has been successfully added."},
				{"0", "0","Andrew V", "123 Main St", "Washington", "DC", "20010", "123456789","12/24", "Quantity must be greater than zero."},
				{"1", "5","", "123 Main St", "Washington", "DC", "20010", "123456789","12/24", "Field 'Customer name' cannot be empty."},
				{"1", "5","Andrew V", "123 Main St", "Washington", "DC", "", "123456789", "12/24", "Field 'Zip' cannot be empty."},
				{"1", "5","Andrew V", "123 Main St", "Washington", "DC", "ff", "123456789", "12/24", "Invalid format. Only digits allowed."},
				{"1", "5","Andrew V", "123 Main St", "Washington", "DC", "20010", "ff","12/24", "Invalid format. Only digits allowed."},
				{"1", "5","Andrew V", "123 Main St", "Washington", "DC", "20010", "123456789", "ff/ff", "Invalid format. Required format is mm/yy."},
				};

	}
	

	@DataProvider(name = "WebOrder_CreateOrder_All_TCs")
	public Object[][] createOrder_All_TCs() {
		return new Object[][] { 
			{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "75000", "Visa", "123456789" ,"12/23", "valid" }, 
			{ "MyMoney", "5", "Nguyen", "123 Main St", "Dallas", "75000", "MasterCard", "123456789" ,"12/23", "valid" }, 
			{ "MyMoney", "", "Minh", "123 Main St", "Dallas", "75000", "Visa", "123456789" ,"12/23", "empty_quantity" }, 
			{ "MyMoney", "0", "Minh", "123 Main St", "Dallas", "75000", "Visa", "123456789" ,"12/23", "invalid_quantity" }, 
			{ "MyMoney", "5", "", "123 Main St", "Dallas", "75000", "Visa", "123456789" ,"12/23", "empty_name" }, 
			{ "MyMoney", "5", "Minh", "", "Dallas", "75000", "Visa", "123456789" ,"12/23", "empty_street" }, 
			{ "MyMoney", "5", "Minh", "123 Main St", "", "75000", "Visa", "123456789" ,"12/23", "empty_city" }, 
			{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "", "Visa", "123456789" ,"12/23", "empty_zip" }, 
			{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "abcde", "Visa", "123456789" ,"12/23", "invalid_zip" },
			{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "75000", "", "123456789" ,"12/23", "empty_card" }, 
			{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "75000", "Visa", "" ,"12/23", "empty_cardnr" }, 
			{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "75000", "Visa", "abcdef", "12/23", "invalid_cardnr" },
			{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "75000", "Visa", "123456789" ,"", "empty_expdate" },
			{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "75000", "Visa", "123456789" ,"abcdef", "invalid_date" },
			{ "MyMoney", "5", "Minh", "123 Main St", "Dallas", "75000", "Visa", "123456789" ,"12-23", "invalid_date" },
			};
	}
	
	@DataProvider(name = "product")
	public Object[][] product() {
		return new Object[][] {

				{"MyMoney", "MyMoney" },
				{"FamilyAlbum", "FamilyAlbum" },
				{"ScreenSaver", "ScreenSaver" }
				};

	}
	
	@DataProvider(name = "LoginExcelData")
	public Object[][] ReadDataFromExcel() throws Exception{
		ReadExcel excel = new ReadExcel();
		String RelativePath = System.getProperty("user.dir");
		//Object[][] testObjArray = excel.getExcelData("C:\\Training_Scripts\\Selenium Training Data\\workspace\\Maven_Selenium_WebDriver_4\\TestDataFile\\WebOrder_Login_TestData.xls","AddUsers");
		Object[][] testObjArray = excel.getExcelData(RelativePath+"\\TestDataFile\\WebOrderTestData.xls","Login");
		System.out.println(testObjArray);
		return testObjArray;

	}
	
	

}
