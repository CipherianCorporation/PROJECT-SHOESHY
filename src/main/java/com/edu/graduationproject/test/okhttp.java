package com.edu.graduationproject.test;



import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.Test;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class okhttp {
	 OkHttpClient client = new OkHttpClient().newBuilder().build();
	@Test
	public void whenSendPostRequest_thenCorrect() 
	  throws IOException {
	    RequestBody formBody = new FormBody.Builder()
	      .add("username", "test")
	      .add("password", "test")
	      .build();
		String url = "http://localhost:8080/security/login/form";
	    Request request = new Request.Builder()
	      .url(url)
	      .post(formBody)
	      .build();

	    Call call = client.newCall(request);
	    Response response = call.execute();
	    assertEquals(response.code(), 200);
	    boolean result = true;
	    if(response.code() == 200) {
	    	System.out.println("true");
	    }
//	    assertThat(response.code(), equalTo(200));
	}
}
