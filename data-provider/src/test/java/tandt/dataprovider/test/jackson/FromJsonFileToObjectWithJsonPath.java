package tandt.dataprovider.test.jackson;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;
import tandt.dataprovider.exceptions.JsonParserException;
import tandt.dataprovider.json.interfaces.JsonParser;
import tandt.dataprovider.json.jackson.JacksonParser;
import tandt.dataprovider.test.jackson.model.Address;
import tandt.dataprovider.test.jackson.model.Person;
import tandt.dataprovider.test.jackson.model.Properties;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FromJsonFileToObjectWithJsonPath {
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
    public void filePathNull() {
        try {
            parser.fromJsonFileToObject(null, "id", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("File path must not be null or empty", e.getMessage());
        }
    }

    @Test
    public void filePathEmpty() {
        try {
            parser.fromJsonFileToObject("", "id", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("File path must not be null or empty", e.getMessage());
        }
    }

    @Test
    public void filePathInvalid(){
        try {
            parser.fromJsonFileToObject("invalidPath.json", "id", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Unable to find the file at given path 'invalidPath.json'", e.getMessage());
        }
    }

    @Test
    public void jsonPathNull() {
        try {
            parser.fromJsonFileToObject(jsonFile, null, Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Json path must be not null or not empty", e.getMessage());
        }
    }

    @Test
    public void jsonPathEmpty() {
        try {
            parser.fromJsonFileToObject(jsonFile, "", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Json path must be not null or not empty", e.getMessage());
        }
    }

    @Test
    public void invalidPath(){
        try {
            parser.fromJsonFileToObject(jsonFile, "invalid.json.path", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Fail to parse json at node 'invalid'", e.getMessage());
        }
    }

    @Test
    public void jsonPathInvalidIndex(){
        try {
            parser.fromJsonFileToObject(jsonFile, "phoneNumbers[3]", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Fail to parse json at node 'phoneNumbers[3]'", e.getMessage());
        }
    }

    @Test
    public void typeNull() {
        try {
            parser.fromJsonFileToObject(jsonFile, "id", null);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Object type must not be null", e.getMessage());
        }
    }

    @Test
    public void invalidType() {
        try {
            parser.fromJsonFileToObject(jsonFile, "id", Properties.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Unable to parse json file to given type", e.getMessage());
        }
    }

    @Test
    public void convertNotJsonString() {
        try {
            String filePath = System.getProperty("user.dir") + "/input/data/invalid-json-formatted-file.json";
            parser.fromJsonFileToObject(filePath, "id", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Unable to parse json file to given type", e.getMessage());
        }
    }

    @Test
    public void convertToArray() {
        Integer[] array = parser.fromJsonFileToObject(jsonFile, "phoneNumbers", Integer[].class);
        Assert.assertEquals(2, array.length);
        Assert.assertEquals(Integer.valueOf(123456), array[0]);
    }

    @Test
    public void convertToList() {
        List list = parser.fromJsonFileToObject(jsonFile, "phoneNumbers", List.class);
        Assert.assertEquals(2, list.size());
        Assert.assertEquals("123456", list.get(0));
    }

    @Test
    public void convertToMap() {
        Map map = parser.fromJsonFileToObject(jsonFile, "address", Map.class);
        Assert.assertEquals(3, map.size());
        Assert.assertEquals("San Jose", map.get("city"));
    }

    @Test
    public void convertToCustomObject() {
        Person convertedPerson = parser.fromJsonFileToObject(jsonFile, Person.class);
        Assert.assertEquals(person.getId(), convertedPerson.getId());
    }

    @Test
    public void convertAFieldToInteger() {
        Integer convertedInt = parser.fromJsonFileToObject(jsonFile, "id", Integer.class);
        Assert.assertEquals(Integer.valueOf(person.getId()), convertedInt);
    }

    @Test
    public void convertAFieldToString() {
        String convertedName = parser.fromJsonFileToObject(jsonFile, "name", String.class);
        Assert.assertEquals(person.getName(), convertedName);
    }
}
