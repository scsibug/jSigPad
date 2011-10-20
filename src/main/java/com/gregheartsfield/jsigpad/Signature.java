package com.gregheartsfield.jsigpad;

import org.codehaus.jackson.map.*;
import org.codehaus.jackson.type.*;
import java.util.List;
import java.util.Map;
import java.io.IOException;

class Signature {
    String json;
    ObjectMapper mapper;
    public Signature(String json) {
        this.json = json;
        mapper = new ObjectMapper();
    }

    public void parse() throws IOException {
        List<Map<String,Integer>> lineObj = mapper.readValue(json, new TypeReference<List<Map<String,Integer>>>() { });
        for (Map m : lineObj) {
            System.out.println(m.get("lx"));
            System.out.println(m.get("ly"));
            System.out.println(m.get("mx"));
            System.out.println(m.get("my"));
        }
    }
}