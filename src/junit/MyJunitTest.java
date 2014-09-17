package junit;

import android.test.AndroidTestCase;

import android.test.AndroidTestCase;
import Help.TinySouClient;
import Help.TinySouHelp;
import junit.framework.Assert;
import junit.framework.TestCase;

public class MyJunitTest extends AndroidTestCase {
	private String EngineKey = "0b732cc0ea3c11874190";
    private String TinySou_Url = "http://api.tinysou.com/v1/public/search";
    private String Search_Content = null;
	public void TestsetMethod() throws Exception{
		TinySouClient tiny = new TinySouClient(EngineKey);
		tiny.setMethod("get");
		Assert.assertEquals("get",tiny.getMethod());
	}

}
