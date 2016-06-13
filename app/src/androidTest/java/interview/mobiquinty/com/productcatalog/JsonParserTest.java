package interview.mobiquinty.com.productcatalog;

import android.test.AndroidTestCase;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import interview.mobiquinty.com.productcatalog.utils.JsonParser;
import interview.mobiquinty.com.productcatalog.utils.Product;

/**
 * Created by Veaceslav Munteanu on 6/13/16.
 *
 * @email veaceslav.munteanu90@gmail.com
 */
public class JsonParserTest  extends AndroidTestCase{


    public void testGetItem() throws JSONException {

        String json = "{\"products\": [{ \"id\": \"1\","      +
                    "\"categoryId\": \"36802\"," +
                    "\"name\": \"Bread\"," +
                    "\"url\": \"/Bread.jpg\"," +
                    "\"description\": \"\"," +
                    "\"salePrice\": {" +
                    "\"amount\": \"0.81\"," +
                    "\"currency\": \"EUR\"" +
                    "}}]}";

        ArrayList<Product> products = JsonParser.parseProducts(new JSONObject(json));

        assertEquals(1, products.size());
        assertEquals(1, products.get(0).id);
        assertEquals("Bread", products.get(0).name);
        assertEquals("/Bread.jpg", products.get(0).url);
        assertEquals(0.81, products.get(0).price);
        assertEquals("EUR", products.get(0).currency);

    }

    public void testItemWithDescription() throws JSONException {
        String json = "{\"products\": [{ \"id\": \"1\","      +
                "\"categoryId\": \"36802\"," +
                "\"name\": \"Bread\"," +
                "\"url\": \"/Bread.jpg\"," +
                "\"description\": \"White Bread\"," +
                "\"salePrice\": {" +
                "\"amount\": \"0.81\"," +
                "\"currency\": \"EUR\"" +
                "}}]}";

        ArrayList<Product> products = JsonParser.parseProducts(new JSONObject(json));

        assertEquals(1, products.size());
        assertEquals(1, products.get(0).id);
        assertEquals("Bread", products.get(0).name);
        assertEquals("/Bread.jpg", products.get(0).url);
        assertEquals(0.81, products.get(0).price);
        assertEquals("EUR", products.get(0).currency);
        assertEquals("White Bread", products.get(0).description);
    }

    public void testWrongJson() {
        String json = "{{\"products\": [{ \"id\": \"1\","      +
                "\"categoryId\": \"36802\"," +
                "\"name\": \"Bread\"," +
                "\"url\": \"/Bread.jpg\"," +
                "\"description\": \"White Bread\"," +
                "\"salePrice\": {" +
                "\"amount\": \"0.81\"," +
                "\"currency\": \"EUR\"" +
                "}}]}";

        try {
            ArrayList<Product> products = JsonParser.parseProducts(new JSONObject(json));
            Assert.fail("Expected to throw Json Exception");
        } catch (JSONException e){

        }
    }

    public void testWrongJson2() {
        String json = "{\"products\": [{ \"id\": \"1\","      +
                "\"categoryId\": \"36802\"," +
                "\"name\": \"Bread\"," +
                "\"url\": \"/Bread.jpg\"," +
                "\"description\": \"White Bread\"," +
                "\"salePrice\": {" +
                "\"amount\": \"0.81\"," +
                "}}]}";

        try {
            ArrayList<Product> products = JsonParser.parseProducts(new JSONObject(json));
            Assert.fail("Expected to throw Json Exception");
        } catch (JSONException e){

        }
    }
}
