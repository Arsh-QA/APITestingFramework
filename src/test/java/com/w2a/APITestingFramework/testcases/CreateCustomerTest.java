package com.w2a.APITestingFramework.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.APITestingFramework.APIs.CreateCustomerAPI;
import com.w2a.APITestingFramework.listeners.ExtentListeners;
import com.w2a.APITestingFramework.setUp.BaseTest;
import com.w2a.APITestingFramework.utilities.DataUtil;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Hashtable;

public class CreateCustomerTest extends BaseTest {




	@Test(dataProviderClass=DataUtil.class,dataProvider="data")
	public void validateCreateCustomerAPIWithValidSecretKey(Hashtable<String, String> data)
	{
		//Sample Comment 123

		Response response =	CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithValidAuthKey(data);
		ExtentListeners.testReport.get().info(data.toString());
		response.prettyPrint();

		System.out.println(response.statusCode());
		AssertJUnit.assertEquals(response.statusCode(), 200);
	}

//	@Test(dataProviderClass=DataUtil.class,dataProvider="data")
//	public void validateCreateCustomerAPIWithInValidSecretKey(Hashtable<String, String> data)
//	{
//
//		Response response =	CreateCustomerAPI.sendPostRequestToCreateCustomerAPIWithInValidAuthKey(data);
//		ExtentListeners.testReport.get().info(data.toString());
//		response.prettyPrint();
//
//		System.out.println(response.statusCode());
//		AssertJUnit.assertEquals(response.statusCode(), 200);
//	}

}