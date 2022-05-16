package light.dataprovider.test.json;

import light.dataprovider.exceptions.JsonElementNotFoundException;
import light.dataprovider.json.JsonBuilder;
import light.dataprovider.json.JsonBuilder2;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JsonBuilderTest {
    /**
     * String json = "{" +
     * "  \"array\": [" +
     * "    1," +
     * "    2," +
     * "    3" +
     * "  ]," +
     * "  \"boolean\": true," +
     * "  \"object\": {" +
     * "    \"a\": \"b\"," +
     * "    \"c\": \"d\"," +
     * "    \"e\": \"f\"" +
     * "  }," +
     * "  \"mapsArray\": [" +
     * "    {" +
     * "      \"key1\": \"value1\"," +
     * "      \"key2\": \"value1\"" +
     * "    }," +
     * "    {" +
     * "      \"key3\": \"value1\"," +
     * "      \"key4\": \"value1\"," +
     * "      \"parent\": {" +
     * "        \"key5\": \"value5\"" +
     * "      }" +
     * "    }" +
     * "  ]," +
     * "  \"level0\": {" +
     * "    \"level1\": {" +
     * "      \"level2\": [" +
     * "          {" +
     * "            \"key1\": \"value1\"," +
     * "            \"key2\": \"value1\"" +
     * "          }," +
     * "          {" +
     * "            \"key3\": \"value1\"," +
     * "            \"key4\": \"value1\"" +
     * "          }" +
     * "      ]" +
     * "    }" +
     * "  }" +
     * "}";
     */


    String json = "{\"array\":[1,2,3],\"boolean\":true,\"object\":{\"a\":\"b\",\"c\":\"d\",\"e\":\"f\"},\"mapsArray\":[{\"key1\":\"value1\",\"key2\":\"value1\"},{\"key3\":\"value1\",\"key4\":\"value1\",\"parent\":{\"key5\":\"value5\"}}],\"level0\":{\"level1\":{\"level2\":[{\"key1\":\"value1\",\"key2\":\"value1\"},{\"key3\":\"value1\",\"key4\":\"value1\"}]}}}";

    JsonBuilder body;
    JsonBuilder2 body2;

    @BeforeMethod
    public void setupClass() {
        body = new JsonBuilder();
        body2 = new JsonBuilder2();
    }

    @Test
    public void testAdd() {
        try {
            body.add("a", true); // key could be "a.b.c"
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        System.out.println("body1: " + body.print());

        try {
            body.add("b.c", true);
        } catch (JsonElementNotFoundException e) {
        }

        System.out.println("body2: " + body.print());

    }

    @Test
    public void testAddMap() {

        try {
            body.addMap("parentKey", "key", "value");
            Assert.assertEquals("{\"parentKey\":{\"key\":\"value\"}}", body.print());
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        System.out.println("body1: " + body.print());

        try {
            body.addMap("a.b", "key", "value");
        } catch (JsonElementNotFoundException e) {
            Assert.assertEquals("{\"parentKey\":{\"key\":\"value\"}}", body.print());
        }
        System.out.println("body2: " + body.print());

        try {
            body.addMap("parentKey", "a.b", "value");
        } catch (JsonElementNotFoundException e) {
            Assert.assertEquals("{\"parentKey\":{\"key\":\"value\"}}", body.print());
        }
        System.out.println("body3: " + body.print());

        try {
            body.addMap("parentKey", "key2", "value2");
            Assert.assertEquals("{\"parentKey\":{\"key\":\"value\",\"key2\":\"value2\"}}", body.print());
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        System.out.println("body4: " + body.print());

        try {
            body.addMap("parentKey2", "key1", "value1");
            Assert.assertEquals("{\"parentKey\":{\"key\":\"value\",\"key2\":\"value2\"},\"parentKey2\":{\"key1\":\"value1\"}}", body.print());
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        System.out.println("body5: " + body.print());

        try {
            body.addMap("parentKey", "key", "valueChange");
            Assert.assertEquals("{\"parentKey\":{\"key\":\"valueChange\",\"key2\":\"value2\"},\"parentKey2\":{\"key1\":\"value1\"}}", body.print());
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        System.out.println("body5: " + body.print());
    }

    @Test
    public void testAddValueArray() {
        try {
            body.addArrayValue("Numbers", 1);
            Assert.assertEquals("{\"Numbers\":[1]}", body.print());
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        System.out.println("body1: " + body.print());

        try {
            body.addArrayValue("Numbers", 2);
            Assert.assertEquals("{\"Numbers\":[1,2]}", body.print());
        } catch (JsonElementNotFoundException e) {
            Assert.assertTrue(false);
        }
        System.out.println("body2: " + body.print());

        try {
            body.addArrayValue("Numbers", "3");
            Assert.assertEquals("{\"Numbers\":[1,2,\"3\"]}", body.print());
        } catch (JsonElementNotFoundException e) {
            Assert.assertTrue(false);
        }
        System.out.println("body3: " + body.print());

        try {
            body.addArrayValue("Number", "1");
            Assert.assertEquals("{\"Numbers\":[1,2,\"3\"],\"Number\":[\"1\"]}", body.print());
        } catch (JsonElementNotFoundException e) {
            Assert.assertTrue(false);
        }
        System.out.println("body4: " + body.print());
    }

    @Test
    public void testAddMapArray() {
//        try {
//            body.addArrayMap("Name", "Firstname", "Jason");
//            body2.addMap("Name[0]", "Firstname", "Jason");
//            Assert.assertEquals(body.print(), body2.print());
//        } catch (JsonElementNotFoundException e) {
//            e.printStackTrace();
//            Assert.assertTrue(false);
//        }
//        System.out.println("body1: " + body.print());

        try {
            body.addArrayMap("Name[0]", "Lastname", "Kai");
            body2.addMap("Name[0]", "Lastname", "Kai");
            Assert.assertEquals(body.print(), body2.print());
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        System.out.println("body2: " + body.print());

        try {
            body.addArrayMap("Name[1]", "FirstName", "Kai");
            body2.addMap("Name[1]", "FirstName", "Kai");
            Assert.assertEquals(body.print(), body2.print());
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        System.out.println("body3: " + body.print());

        try {
            body.addArrayMap("Name[-2]", "FirstName", "Kai");
            body2.addMap("Name[-2]", "FirstName", "Kai");
            Assert.assertEquals(body.print(), body2.print());
        } catch (JsonElementNotFoundException e) {
            Assert.assertTrue(true);
        }
        System.out.println("body3: " + body.print());

        try {
            body.addArrayMap("Name[-1]", "FirstName", "Kai");
            body2.addMap("Name[-1]", "FirstName", "Kai");
            Assert.assertEquals(body.print(), body2.print());
        } catch (JsonElementNotFoundException e) {
            Assert.assertTrue(true);
        }
        System.out.println("body4: " + body.print());
    }

    @Test
    public void testAddMapArray2() {
        try {
            body2.addMap("Name", "Firstname", "Jason");
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        System.out.println("body1: " + body2.print());

        try {
            body2.addMap("Name[0]", "Lastname", "Kai");
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        System.out.println("body2: " + body2.print());

        try {
            body2.addMap("Name[1]", "FirstName", "Kai");
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        System.out.println("body3: " + body2.print());

        try {
            body2.addMap("Name[-2]", "FirstName", "Kai");
        } catch (JsonElementNotFoundException e) {
            Assert.assertTrue(true);
        }
        System.out.println("body3: " + body2.print());

        try {
            body2.addMap("Name[-1]", "FirstName", "Kai");
        } catch (JsonElementNotFoundException e) {
            Assert.assertTrue(true);
        }
        System.out.println("body4: " + body2.print());
    }

    @Test
    public void testGenerateJson() throws JsonElementNotFoundException {
        body.createJson("level0[0].level1.level2[0]", "key1", "value1");
        body2.addMap("level0[0].level1.level2[0]", "key1", "value1");
        Assert.assertEquals(body.print(), body2.print());
        System.out.println(body.print());
        body.createJson("level0[0].level1.level2[0].level3[1]", "key2", "value2");
        body2.addMap("level0[0].level1.level2[0].level3[1]", "key2", "value2");
        Assert.assertEquals(body.print(), body2.print());
        System.out.println(body.print());
        body.createJson("level0[0].level1.level2[0].level3[2]", "key3", "value3");
        body2.addMap("level0[0].level1.level2[0].level3[2]", "key3", "value3");
        Assert.assertEquals(body.print(), body2.print());
        System.out.println(body.print());
    }

    @Test
    public void testCreateJson() throws JsonElementNotFoundException {

        body.createJson("mapsArray[0]", "key1", "value1");
        body2.addMap("mapsArray[0]", "key1", "value1");
        Assert.assertEquals(body.print(), body2.print());

        body.createJson("mapsArray[0]", "key2", "value2");
        body2.addMap("mapsArray[0]", "key2", "value2");
        Assert.assertEquals(body.print(), body2.print());

        body.createJson("mapsArray[1]", "key3", "value3");
        body2.addMap("mapsArray[1]", "key3", "value3");
        Assert.assertEquals(body.print(), body2.print());

        body.createJson("mapsArray[1]", "key4", "value4");
        body2.addMap("mapsArray[1]", "key4", "value4");
        Assert.assertEquals(body.print(), body2.print());

        body.createJson("mapsArray[1].parent[0]", "key5", "value5");
        body2.addMap("mapsArray[1].parent[0]", "key5", "value5");
        Assert.assertEquals(body.print(), body2.print());

        body.createJson("mapsArray[1].parent[0]", "key5", "value51");
        body2.addMap("mapsArray[1].parent[0]", "key5", "value51");
        Assert.assertEquals(body.print(), body2.print());

        body.createJson("mapsArray[1].parent[1]", "key5", "value5");
        body2.addMap("mapsArray[1].parent[1]", "key5", "value5");
        Assert.assertEquals(body.print(), body2.print());

        body.createJson("object", "a", "b");
        body2.addMap("object", "a", "b");
        Assert.assertEquals(body.print(), body2.print());

        body.createJson("object", "c", "d");
        body2.addMap("object", "c", "d");
        Assert.assertEquals(body.print(), body2.print());
    }

    @Test
    public void testSample() throws JsonElementNotFoundException {
        body.addArrayValue("parentKey", "value1");
        body2.addValue("parentKey[0]", "value1");
        Assert.assertEquals(body.print(), body2.print());

        body.addArrayValue("parentKey", 2);
        body2.addValue("parentKey[1]", 2);
        Assert.assertEquals(body.print(), body2.print());

        body.addArrayValue("parentKey", true);
        body2.addValue("parentKey[2]", true);
        Assert.assertEquals(body.print(), body2.print());

        body.addArrayValue("parentKey", 'c');
        body2.addValue("parentKey[3]", 'c');
        Assert.assertEquals(body.print(), body2.print());
    }

    @Test
    public void testGenerateJsonValue() throws JsonElementNotFoundException {
        body.createJson("level0[0].level1.level2[0]", true);
        body2.addValue("level0[0].level1.level2[0]", true);
        Assert.assertEquals(body.print(), body2.print());

        body.createJson("level0[0].level1.level2[1].level3[0]", "value1");
        body2.addValue("level0[0].level1.level2[1].level3[0]", "value1");
        Assert.assertEquals(body.print(), body2.print());

        body.createJson("level0[0].level1.level2[1].level3[1]", 1);
        body2.addValue("level0[0].level1.level2[1].level3[1]", 1);
        Assert.assertEquals(body.print(), body2.print());

        body.createJson("level0[0].level1.level2[1].level3[2]", 'c');
        body2.addValue("level0[0].level1.level2[1].level3[2]", 'c');
        Assert.assertEquals(body.print(), body2.print());
    }

}
