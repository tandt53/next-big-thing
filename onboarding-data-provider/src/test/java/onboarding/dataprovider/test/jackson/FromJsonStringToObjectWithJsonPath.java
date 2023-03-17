package onboarding.dataprovider.test.jackson;

import onboarding.dataprovider.exceptions.JsonParserException;
import onboarding.dataprovider.json.interfaces.JsonParser;
import onboarding.dataprovider.json.jackson.JacksonParser;
import onboarding.dataprovider.test.jackson.model.Address;
import onboarding.dataprovider.test.jackson.model.Person;
import onboarding.dataprovider.test.jackson.model.Properties;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FromJsonStringToObjectWithJsonPath {

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

    @BeforeClass
    public void setup() {
        parser = new JacksonParser();
        init();
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

    @AfterClass
    public void teardown() {
        File file = new File(jsonFile);
        if (file.exists())
            file.delete();
    }

    @Test
    public void stringEmpty() {
        try {
            parser.fromJsonStringToObject("", "id", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Json string must be not null or not empty", e.getMessage());
        }
    }

    @Test
    public void stringNull() {
        try {
            parser.fromJsonStringToObject(null, "id", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Json string must be not null or not empty", e.getMessage());
        }
    }

    @Test
    public void jsonPathNull(){
        try {
            parser.fromJsonStringToObject(jsonString, null, Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Json path must be not null or not empty", e.getMessage());
        }
    }

    @Test
    public void jsonPathEmpty(){
        try {
            parser.fromJsonStringToObject(jsonString, "", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Json path must be not null or not empty", e.getMessage());
        }
    }

    @Test
    public void invalidPath() {
        try {
            parser.fromJsonStringToObject(jsonString, "invalid.json.path", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Fail to parse json at node 'invalid'", e.getMessage());
        }
    }

    @Test
    public void jsonPathInvalidIndex(){
        try {
            parser.fromJsonStringToObject(jsonString, "phoneNumbers[3]", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Fail to parse json at node 'phoneNumbers[3]'", e.getMessage());
        }
    }

    @Test
    public void typeNull() {
        try {
            parser.fromJsonStringToObject(jsonString, "id", null);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Object type must not be null", e.getMessage());
        }
    }

    @Test
    public void invalidType(){
        try {
            parser.fromJsonStringToObject(jsonString, "id", Properties.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Unable to convert string to given type", e.getMessage());
        }
    }

    @Test
    public void convertNotJsonString() {
        try {
            parser.fromJsonStringToObject("id: 123", "id", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Unable to convert string to given type", e.getMessage());
        }
    }

    @Test
    public void convertToArray() {
        String jsonArray = "{\n" +
                "  \"phoneNumbers\": [\n" +
                "    123456,\n" +
                "    987654\n" +
                "  ]\n" +
                "}";
        Integer[] array = parser.fromJsonStringToObject(jsonArray, "phoneNumbers", Integer[].class);
        Assert.assertEquals(2, array.length);
        Assert.assertEquals(Integer.valueOf(123456), array[0]);

    }

    @Test
    public void convertToList() {
        String jsonArray = "{\n" +
                "  \"phoneNumbers\": [\n" +
                "    123456,\n" +
                "    987654\n" +
                "  ]\n" +
                "}";
        List list = parser.fromJsonStringToObject(jsonArray, "phoneNumbers", List.class);
        Assert.assertEquals(2, list.size());
        Assert.assertEquals(123456, list.get(0));
    }

    @Test
    public void convertToMap() {
        String jsonObject = "{\n" +
                "  \"address\": {\n" +
                "    \"street\": \"Albany Dr\",\n" +
                "    \"city\": \"San Jose\",\n" +
                "    \"zipcode\": 95129\n" +
                "  }\n" +
                "}";

        Map map = parser.fromJsonStringToObject(jsonObject, "address", Map.class);
        Assert.assertEquals(3, map.size());
        Assert.assertEquals("Albany Dr", map.get("street"));
    }

    @Test
    public void convertToCustomObject() {
        Address convertedAddress = parser.fromJsonStringToObject(jsonString, "address", Address.class);
        Assert.assertEquals(address.getCity(), convertedAddress.getCity());
    }

    @Test
    public void convertAFieldToInteger() {
        Integer convertedInt = parser.fromJsonStringToObject(jsonString, "id", Integer.class);
        Assert.assertEquals(Integer.valueOf(person.getId()), convertedInt);
    }

    @Test
    public void convertAFieldToString() {
        String convertedName = parser.fromJsonStringToObject(jsonString, "name", String.class);
        Assert.assertEquals(person.getName(), convertedName);
    }

}
