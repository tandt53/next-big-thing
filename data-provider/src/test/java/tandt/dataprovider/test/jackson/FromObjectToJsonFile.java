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

public class FromObjectToJsonFile {
    private JsonParser parser;
    private Person person;

    private String jsonFile = "jackson.json";


    @Before
    public void setup() {
        parser = new JacksonParser();
        init();
    }

    private void init() {
        Address address = new Address();
        address.setCity("Los Angeles");
        address.setStreet("Albany Dr");
        address.setZipcode(95129);

        Properties properties = new Properties();
        properties.setAge("29 years");
        properties.setSalary("1000 USD");

        List<String> phoneNumbers = new ArrayList<>();
        phoneNumbers.add("123456");
        phoneNumbers.add("987654");

        List<String> cities = new ArrayList<>();
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
    public void objectNull() {
        try {
            parser.fromObjectToJsonFile(null, jsonFile);
            Assert.fail("Exception should be thrown!!!");
        } catch (JsonParserException e) {
            Assert.assertEquals("Object must be not null", e.getMessage());
        }
    }
    @Test
    public void filePathNull(){
        try {
            parser.fromObjectToJsonFile(person, null);
            Assert.fail("Exception should be thrown!!!");
        } catch (JsonParserException e) {
            Assert.assertEquals("File path must not be null or empty", e.getMessage());
        }
    }
    @Test
    public void filePathEmpty(){
        try {
            parser.fromObjectToJsonFile(person, "");
            Assert.fail("Exception should be thrown!!!");
        } catch (JsonParserException e) {
            Assert.assertEquals("File path must not be null or empty", e.getMessage());
        }
    }
    @Test
    public void happyCase() {
        File file = new File(jsonFile);
        // delete file if existed
        if (file.exists())
            file.delete();
        parser.fromObjectToJsonFile(person, jsonFile);
        Assert.assertTrue(file.exists());
    }
}
