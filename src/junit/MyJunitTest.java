package junit;

import org.junit.Test;

import android.test.AndroidTestCase;

import android.test.AndroidTestCase;
import Help.TinySouClient;
import Help.TinySouHelp;
import junit.framework.Assert;
import junit.framework.TestCase;

public class MyJunitTest extends AndroidTestCase {
	
	private String EngineKey = "0b732cc0ea3c11874190";
    private String TinySouUrl = "http://api.tinysou.com/v1/public/search";
    private String Search_Content = null;
    
    @Test
	public void TestMethod() throws Exception{
		TinySouClient tiny = new TinySouClient(EngineKey);
		tiny.setMethod("get");
		Assert.assertEquals("get",tiny.getMethod());
	}
	
    
    @Test
	public void TestEngineKey() throws Exception{
		TinySouClient tiny = new TinySouClient(EngineKey);
		tiny.setEngine_key("111");
		Assert.assertEquals("111",tiny.getEngine_key());
	}
    
    @Test
    public void TestUrl() throws Exception{
		TinySouClient tiny = new TinySouClient(EngineKey);
		tiny.setUrl("111");
		Assert.assertEquals("111",tiny.getUrl());
	}
    
    @Test
    public void TestParams_q() throws Exception{
		TinySouClient tiny = new TinySouClient(EngineKey);
		tiny.setParams_q("111");
		Assert.assertEquals("111",tiny.getParams_q());
	}
    
    
	
}
