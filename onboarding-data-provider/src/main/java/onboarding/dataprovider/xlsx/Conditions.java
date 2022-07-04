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
        ArrayList<String> keys = new ArrayList<String>();
        Set<String> keyset = this.conditions.keySet();
        for(String s: keyset){
            keys.add(s);
        }
        return keys;
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
