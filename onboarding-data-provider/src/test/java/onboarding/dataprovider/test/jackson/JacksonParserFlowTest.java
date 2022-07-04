package onboarding.dataprovider.test.jackson;

import onboarding.dataprovider.json.interfaces.JsonParser;
import onboarding.dataprovider.json.jackson.JacksonParser;
import onboarding.dataprovider.test.jackson.model.Address;
import onboarding.dataprovider.test.jackson.model.Person;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class JacksonParserFlowTest {
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

    /**
     * methods under test
     * - fromJsonStringToObject
     * - fromObjectToJsonFile
     * - fromJsonFileToObject
     */
    @Test
    public void testObjectToFile() {
        String filepath = "jackson.json";
        JsonParser parser = new JacksonParser();

        // method: <T> T fromJsonStringToObject(String jsonString, Class<T> T);
        Person person = parser.fromJsonStringToObject(jsonString, Person.class);
        Address address = person.getAddress();
        String age = person.getProperties().getAge();

        // method: <T> void fromObjectToJsonFile(T object, String filePath);
        parser.fromObjectToJsonFile(person, filepath);
        File file = new File(filepath);
        Assert.assertTrue(file.exists());

        // method: T fromJsonFileToObject(String jsonFile, Class<T> T);
        Person deserialized = parser.fromJsonFileToObject(filepath, Person.class);
        Assert.assertEquals(person, deserialized);

        // method: <T> T fromJsonFileToObject(String jsonFile, String jsonPath, Class<T> T);
        Address deserializedAddress = parser.fromJsonFileToObject(filepath, "address", Address.class);
        Assert.assertEquals(address, deserializedAddress);

        String deserializedAge = parser.fromJsonFileToObject(filepath, "properties.age", String.class);
        Assert.assertEquals(age, deserializedAge);

        // method: <T> T fromJsonStringToObject(String jsonString, String jsonPath, Class<T> T);
        Address desStringAddress = parser.fromJsonStringToObject(jsonString, "address", Address.class);
        Assert.assertEquals(address, desStringAddress);

        // method: <T> T fromJsonStringToObject(String jsonString, String jsonPath, Class<T> T);
        String desStringAge = parser.fromJsonStringToObject(jsonString, "properties.age", String.class);
        Assert.assertEquals(age, desStringAge);

    }

}

