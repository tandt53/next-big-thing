package light.dataprovider.test.jackson;

import light.dataprovider.test.jackson.model.Address;
import light.dataprovider.test.jackson.model.Person;
import light.dataprovider.test.jackson.model.Properties;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;
import light.dataprovider.exceptions.JsonParserException;
import light.dataprovider.json.interfaces.JsonParser;
import light.dataprovider.json.jackson.JacksonParser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FromJsonFileToObject {

    private JsonParser parser;
    private Person person;
    private String jsonFile = "jackson.json";
    private Address address;
    private Properties properties;
    private List<String> phoneNumbers;
    private List<String> cities;
    private static final String jsonString = "{\n" +
            "  \"id\": 123,\n" +
            "  \"name\": \"Pankaj\",\n" +
            "  \"permanent\": true,\n" +
            "  \"address\": {\n" +
            "    \"street\": \"Albany Dr\",\n" +
            "    \"city\": \"San Jose\",\n" +
            "    \"zipcode\": 95129\n" +
            "  },\n" +
            "  \"phoneNumbers\": [\n" +
            "    123456,\n" +
            "    987654\n" +
            "  ],\n" +
            "  \"role\": \"Manager\",\n" +
            "  \"cities\": [\n" +
            "    \"Los Angeles\",\n" +
            "    \"New York\"\n" +
            "  ],\n" +
            "  \"properties\": {\n" +
            "    \"age\": \"29 years\",\n" +
            "    \"salary\": \"1000 USD\"\n" +
            "  }\n" +
            "}";

    @Before
    public void setup() {
        parser = new JacksonParser();
        init();
        parser.fromObjectToJsonFile(person, jsonFile);
        Assert.assertTrue(new File(jsonFile).exists());
    }

    private void init() {
        address = new Address();
        address.setCity("San Jose");
        address.setStreet("Albany Dr");
        address.setZipcode(95129);

        properties = new Properties();
        properties.setAge("29 years");
        properties.setSalary("1000 USD");

        phoneNumbers = new ArrayList<>();
        phoneNumbers.add("123456");
        phoneNumbers.add("987654");

        cities = new ArrayList<>();
        cities.add("Los Angeles");
        cities.add("New York");

        person = new Person();
        person.setId(123);
        person.setName("Pankaj");
        person.setPermanent(true);
        person.setAddress(address);
        person.setPhoneNumbers(phoneNumbers);
        person.setRole("Manager");
        person.setCities(cities);
        person.setProperties(properties);
    }

    @After
    public void teardown() {
        File file = new File(jsonFile);
        if (file.exists())
            file.delete();
    }

    @Test
    public void convertToNull() {
        try {
            parser.fromJsonFileToObject(jsonString, null);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Object type must not be null", e.getMessage());
        }
    }

    @Test
    public void filePathNull() {
        try {
            parser.fromJsonFileToObject(null, Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("File path must not be null or empty", e.getMessage());
        }
    }

    @Test
    public void convertEmptyString() {
        try {
            parser.fromJsonFileToObject("", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("File path must not be null or empty", e.getMessage());
        }
    }

    @Test
    public void convertNotJsonString() {
        try {
            String filePath = System.getProperty("user.dir") + "/input/data/invalid-json-formatted-file.json";
            parser.fromJsonFileToObject(filePath, Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Unable to read json file", e.getMessage());
        }
    }

    @Test
    public void convertToArray() {
        String jsonArrayPath = System.getProperty("user.dir") + "/input/data/json-array-integer.json";
        Integer[] array = parser.fromJsonFileToObject(jsonArrayPath, Integer[].class);
        Assert.assertEquals(2, array.length);
        Assert.assertEquals(Integer.valueOf(123456), array[0]);
    }

    @Test
    public void convertToList() {
        String jsonArrayPath = System.getProperty("user.dir") + "/input/data/json-array-integer.json";
        List list = parser.fromJsonFileToObject(jsonArrayPath, List.class);
        Assert.assertEquals(2, list.size());
        Assert.assertEquals(Integer.valueOf(123456), list.get(0));
    }

    @Test
    public void convertToMap() {
        String jsonObjectPath = System.getProperty("user.dir") + "/input/data/json-object.json";

        Map map = parser.fromJsonFileToObject(jsonObjectPath, Map.class);
        Assert.assertEquals(3, map.size());
        Assert.assertEquals(123, map.get("id"));
    }

    @Test
    public void convertToCustomObject() {
        Person convertedPerson = parser.fromJsonFileToObject(jsonFile, Person.class);
        Assert.assertEquals(person.getId(), convertedPerson.getId());
    }

}
