package com.w2a.APITestingFramework.testcases;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.w2a.APITestingFramework.APIs.CreateCustomerAPI;
import com.w2a.APITestingFramework.APIs.DeleteCustomerAPI;
import com.w2a.APITestingFramework.listeners.ExtentListeners;
import com.w2a.APITestingFramework.setUp.BaseTest;
import com.w2a.APITestingFramework.utilities.DataUtil;
import com.w2a.APITestingFramework.utilities.TestUtil;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Hashtable;

public class DeleteCustomerTest extends BaseTest {




	@Test(dataProviderClass=DataUtil.class,dataProvider="data")
	public void deleteCustomer(Hashtable<String, String> data)
	{

		Response response =	DeleteCustomerAPI.sendDeleteRequestToDeleteCustomerAPIWithValidID(data);

		response.prettyPrint();

		ExtentListeners.testReport.get().info(data.toString());


		//		String actual_id = response.jsonPath().get("id").toString();
		//		System.out.println("Getting ID from JsonPath : "+actual_id);
		//		Assert.assertEquals(actual_id, data.get("id"),"ID not matching");

		//		JSONObject jsonObject = new JSONObject(response.asString());
		//		System.out.println(jsonObject.has("id"));
		//		Assert.assertTrue(jsonObject.has("id"), "ID Key is not present");

		System.out.println("Presence check for Object Key : "+TestUtil.jsonHasKey(response.asString(), "object"));
		System.out.println("Presence check for Deleted Key : "+TestUtil.jsonHasKey(response.asString(), "deleted"));
		Assert.assertTrue(TestUtil.jsonHasKey(response.asString(), "id"),"ID Key is not present");

		String actual_id = TestUtil.getJsonKeyValue(response.asString(), "id");
		System.out.println("Getting ID from JsonObject : "+actual_id);
		Assert.assertEquals(actual_id, data.get("id"),"ID not matching");

		System.out.println("Object key value is : " + TestUtil.getJsonKeyValue(response.asString(), "object"));
		System.out.println("Deleted key value is : " + TestUtil.getJsonKeyBooleanValue(response.asString(), "deleted"));

		System.out.println(response.statusCode());
		Assert.assertEquals(response.statusCode(), 200);
	}
}