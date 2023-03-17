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

public class FromJsonStringToObject {

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
    // happy case
    // to Array
    // to List
    // to Map
    // to custom object: Person, Address, Properties

    // corner case:
    // json string is null
    // json string is empty
    // json string contains {} and [] only
    // json string is not json-formatted
    // object data type is null
    // object data is String.class


    @Test
    public void convertToString() {
        try {
            parser.fromJsonStringToObject(jsonString, String.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Object type must not be null or String.class", e.getMessage());
        }
    }

    @Test
    public void convertToNull() {
        try {
            parser.fromJsonStringToObject(jsonString, null);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Object type must not be null or String.class", e.getMessage());
        }
    }

    @Test
    public void convertNullString() {
        try {
            parser.fromJsonStringToObject(null, Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Json string must be not null or not empty", e.getMessage());
        }
    }

    @Test
    public void convertEmptyString() {
        try {
            parser.fromJsonStringToObject("", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Json string must be not null or not empty", e.getMessage());
        }
    }

    // this case should be reviewed again when using gson
    @Test
    public void convertNotJsonString() {
        try {
            parser.fromJsonStringToObject("123123", Person.class);
            Assert.fail("Exception should be thrown");
        } catch (JsonParserException e) {
            Assert.assertEquals("Unable to convert string to object", e.getMessage());
        }
    }

    @Test
    public void convertToArray() {
        String jsonArray = "[\n" +
                "    123456,\n" +
                "    987654\n" +
                "  ]\n";
        Integer[] array = parser.fromJsonStringToObject(jsonArray, Integer[].class);
        Assert.assertEquals(2, array.length);
        Assert.assertEquals(Integer.valueOf(123456), array[0]);

    }

    @Test
    public void convertToList(){
        String jsonArray = "[\n" +
                "    123456,\n" +
                "    987654\n" +
                "  ]\n";
        List list = parser.fromJsonStringToObject(jsonArray, List.class);
        Assert.assertEquals(2, list.size());
        Assert.assertEquals(Integer.valueOf(123456), list.get(0));
    }

    @Test
    public void convertToMap(){
        String jsonObject = "{\n" +
                "  \"id\": 123,\n" +
                "  \"name\": \"Pankaj\",\n" +
                "  \"permanent\": true\n" +
                "}";

        Map map = parser.fromJsonStringToObject(jsonObject, Map.class);
        Assert.assertEquals(3, map.size());
        Assert.assertEquals(123, map.get("id"));
    }

    @Test
    public void convertToCustomObject(){
        Person converted = parser.fromJsonStringToObject(jsonString, Person.class);
        Assert.assertEquals(person.getId(), converted.getId());
    }


}
