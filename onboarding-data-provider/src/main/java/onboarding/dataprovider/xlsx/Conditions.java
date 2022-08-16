package onboarding.dataprovider.xlsx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Conditions {
    private HashMap<String, String> conditions;

    public Conditions() {
        conditions = new HashMap<>();
    }

    public void addCondition(String key, String value) {
        if (conditions != null) {
            this.conditions.put(key, value);
        }
    }

    public void removeCondition(String key) {
        if (conditions != null) {
            this.conditions.remove(key);
        }
    }

    public ArrayList<String> getKeys() {
        Set<String> keyset = this.conditions.keySet();
        return new ArrayList<String>(keyset);
    }

    public ArrayList<String> getValues() {
        ArrayList<String> values = new ArrayList<>();
        ArrayList<String> keys = getKeys();

        for (String k : keys){
            values.add(this.conditions.get(k));
        }
        return values;
    }

    public boolean isEmpty(){
        return conditions.isEmpty();
    }
}
