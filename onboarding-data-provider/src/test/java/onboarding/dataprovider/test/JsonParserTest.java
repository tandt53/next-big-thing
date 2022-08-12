package onboarding.dataprovider.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import onboarding.dataprovider.json.JsonParser;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParserTest {

    private String filePath = System.getProperty("user.dir") + "/dev-data/configEnv.json";
    private String jsonPath = "env.qc.bill.tid";

    private String jsonString = "";

    @Test
    public void testReadFile() {

        String jsonFile = System.getProperty("user.dir") + "/input/data/leadTest.json";
        String jsonPath = "tests[0].input.general";
        LeadGeneral leadGeneral;
        try {
            leadGeneral = JsonParser.fromJsonFileToObject(jsonFile, jsonPath, LeadGeneral.class);
            JsonParser.fromObjectToJsonFile(leadGeneral, "test.json");
            new Gson().toJson(leadGeneral, Files.newBufferedWriter(Paths.get("test1.json")));

            Gson gson = new GsonBuilder().create();

            // create a writer
            Writer writer = Files.newBufferedWriter(Paths.get("user.json"));

            // convert user object to JSON file
            gson.toJson(leadGeneral, writer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Assert.fail();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void sampleTest() throws IOException {
    }
}

/**
 * "general": {
 * "name": "sample name",
 * "phone": "0117000532",
 * "email": "sample_mail@gmail.com",
 * "legalName": "sample legal name",
 * "representPhone": "01170000001"
 * },
 */

class LeadGeneral {
    private String name;
    private String phone;
    private String email;
    private String legalName;
    private String representPhone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getRepresentPhone() {
        return representPhone;
    }

    public void setRepresentPhone(String representPhone) {
        this.representPhone = representPhone;
    }

    @Override
    public String toString() {
        return "LeadGeneral{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", legalName='" + legalName + '\'' +
                ", representPhone='" + representPhone + '\'' +
                '}';
    }
}