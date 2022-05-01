package light.restassured.test;

import light.common.Log;
import light.restassured.exceptions.JsonCreationException;
import light.restassured.rest.RestBody;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import light.restassured.exceptions.JsonElementNotFoundException;

public class RestBodyTest {
    private Log log= new Log(RestBodyTest.class);

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

//    add("level0.level1.level2", "key", "value");
    //update ("level0.level1.level2", "key", "value");

    String json = "{\"array\":[1,2,3],\"boolean\":true,\"object\":{\"a\":\"b\",\"c\":\"d\",\"e\":\"f\"},\"mapsArray\":[{\"key1\":\"value1\",\"key2\":\"value1\"},{\"key3\":\"value1\",\"key4\":\"value1\",\"parent\":{\"key5\":\"value5\"}}],\"level0\":{\"level1\":{\"level2\":[{\"key1\":\"value1\",\"key2\":\"value1\"},{\"key3\":\"value1\",\"key4\":\"value1\"}]}}}";

    RestBody body;

    @BeforeMethod
    public void setupClass() {
        body = new RestBody();
    }

    @Test
    public void testAdd() {
        try {
            body.add("a", true); // key could be "a.b.c"
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        log.info("Started %s", "body1: " + body.print());

        try {
            body.add("b.c", true);
        } catch (JsonElementNotFoundException e) {
        }

        log.info("Started %s", "body2: " + body.print());

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
        log.info("Started %s", "body1: " + body.print());

        try {
            body.addMap("a.b", "key", "value");
        } catch (JsonElementNotFoundException e) {
            Assert.assertEquals("{\"parentKey\":{\"key\":\"value\"}}", body.print());
        }
        log.info("Started %s", "body2: " + body.print());

        try {
            body.addMap("parentKey", "a.b", "value");
        } catch (JsonCreationException e) {
            Assert.assertEquals("{\"parentKey\":{\"key\":\"value\"},\"a.b\":{\"key\":\"value\"}}", body.print());
        }
        log.info("Started %s", "body3: " + body.print());

        try {
            body.addMap("parentKey", "key2", "value2");
            Assert.assertEquals("{\"parentKey\":{\"key\":\"value\",\"key2\":\"value2\"},\"a.b\":{\"key\":\"value\"}}", body.print());
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        log.info("Started %s", "body4: " + body.print());

        try {
            body.addMap("parentKey2", "key1", "value1");
            Assert.assertEquals("{\"parentKey\":{\"key\":\"value\",\"key2\":\"value2\"},\"a.b\":{\"key\":\"value\"},\"parentKey2\":{\"key1\":\"value1\"}}", body.print());
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        log.info("Started %s", "body5: " + body.print());

        try {
            body.addMap("parentKey", "key", "valueChange");
            Assert.assertEquals("{\"parentKey\":{\"key\":\"valueChange\",\"key2\":\"value2\"},\"a.b\":{\"key\":\"value\"},\"parentKey2\":{\"key1\":\"value1\"}}", body.print());
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        log.info("Started %s", "body5: " + body.print());


    }

    @Test
    public void testAddValueArray() {
        try {
            body.addValueToArray("Numbers", 1);
            Assert.assertEquals("{\"Numbers\":[1]}", body.print());
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        log.info("Started %s", "body1: " + body.print());

        try {
            body.addValueToArray("Numbers", 2);
            Assert.assertEquals("{\"Numbers\":[1,2]}", body.print());
        } catch (JsonElementNotFoundException e) {
            Assert.assertTrue(false);
        }
        log.info("Started %s", "body2: " + body.print());

        try {
            body.addValueToArray("Numbers", "3");
            Assert.assertEquals("{\"Numbers\":[1,2,\"3\"]}", body.print());
        } catch (JsonElementNotFoundException e) {
            Assert.assertTrue(false);
        }
        log.info("Started %s", "body3: " + body.print());

        try {
            body.addValueToArray("Number", "1");
            Assert.assertEquals("{\"Numbers\":[1,2,\"3\"],\"Number\":[\"1\"]}", body.print());
        } catch (JsonElementNotFoundException e) {
            Assert.assertTrue(false);
        }
        log.info("Started %s", "body4: " + body.print());
    }

    @Test
    public void testAddMapToArray() {
        try {
            body.addMapToArray("Name", "Firstname", "Jason");
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        log.info("Started %s", "body1: " + body.print());

        try {
            body.addMapToArray("Name[0]", "Lastname", "Kai");
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        log.info("Started %s", "body2: " + body.print());

        try {
            body.addMapToArray("Name[1]", "FirstName", "Kai");
        } catch (JsonElementNotFoundException e) {
            e.printStackTrace();
            Assert.assertTrue(false);
        }
        log.info("Started %s", "body3: " + body.print());

        try {
            body.addMapToArray("Name[-2]", "FirstName", "Kai");
        } catch (JsonCreationException e) {
            Assert.assertTrue(true);
        }
        log.info("Started %s", "body3: " + body.print());

        try {
            body.addMapToArray("Name[-1]", "FirstName", "Kai");
        } catch (JsonCreationException e) {
            Assert.assertTrue(true);
        }
        log.info("Started %s", "body4: " + body.print());
    }

    @Test
    public void testGenerateJson() throws JsonElementNotFoundException {
        body.addMapWithJsonPath("level0[0].level1.level2[0]", "key1", "value1");
        log.info("Started %s", body.print());
        body.addMapWithJsonPath("level0[0].level1.level2[0].level3[1]", "key2", "value2");
        log.info("Started %s", body.print());
        body.addMapWithJsonPath("level0[0].level1.level2[0].level3[2]", "key3", "value3");
        log.info("Started %s", body.print());
    }

    @Test
    public void testaddMapWithJsonPath() throws JsonElementNotFoundException {
        body.addMapWithJsonPath("mapsArray[0]", "key1", "value1");
        System.out.println(body.print());
        body.addMapWithJsonPath("mapsArray[0]", "key2", "value2");
        body.addMapWithJsonPath("mapsArray[1]", "key3", "value3");
        body.addMapWithJsonPath("mapsArray[1]", "key4", "value4");
        body.addMapWithJsonPath("mapsArray[1].parent[0]", "key5", "value5");
        body.addMapWithJsonPath("mapsArray[1].parent[0]", "key5", "value51");
        body.addMapWithJsonPath("mapsArray[1].parent[1]", "key5", "value5");
        body.addMapWithJsonPath("object", "a", "b");
        body.addMapWithJsonPath("object", "c", "d");
        System.out.println(body.print());

    }

    @Test
    public void testaddMapWithJsonPath2() throws JsonElementNotFoundException {
        body.addMapWithJsonPath("mapsArray[0]", "key1", "value1");
        Assert.assertEquals(body.print(), "{\"mapsArray\":[{\"key1\":\"value1\"}]}");
        body.addMapWithJsonPath("mapsArray[0]", "key2", "value2");
        body.addMapWithJsonPath("mapsArray[1]", "key3", "value3");
        body.addMapWithJsonPath("mapsArray[1]", "key4", "value4");
        body.addMapWithJsonPath("mapsArray[1].parent[0]", "key5", "value5");
        body.addMapWithJsonPath("mapsArray[1].parent[0]", "key5", "value51");
        body.addMapWithJsonPath("mapsArray[1].parent[1]", "key5", "value5");
        body.addMapWithJsonPath("object", "a", "b");
        body.addMapWithJsonPath("object", "c", "d");
        Assert.assertEquals("{\"mapsArray\":[{\"key1\":\"value1\",\"key2\":\"value2\"},{\"key3\":\"value3\",\"key4\":\"value4\",\"parent\":[{\"key5\":\"value51\"},{\"key5\":\"value5\"}]}],\"object\":{\"a\":\"b\",\"c\":\"d\"}}", body.print());
    }

    @Test
    public void testSample() throws JsonElementNotFoundException {
        body.addValueToArray("parentKey", "value1");
        body.addValueToArray("parentKey", 2);
        body.addValueToArray("parentKey", true);
        body.addValueToArray("parentKey", 'c');
        log.info("Started %s", body.print());
    }

    @Test
    public void testGenerateJsonValue() throws JsonElementNotFoundException {
        body.addValueWithJsonPath("level0[0].level1.level2[0]", true);
        log.info("Started %s", body.print());
        body.addValueWithJsonPath("level0[0].level1.level2[1].level3[0]", "value1");
        log.info("Started %s", body.print());
        body.addValueWithJsonPath("level0[0].level1.level2[1].level3[1]", 1);
        log.info("Started %s", body.print());
        body.addValueWithJsonPath("level0[0].level1.level2[1].level3[2]", 'c');
        log.info("Started %s", body.print());
    }


}