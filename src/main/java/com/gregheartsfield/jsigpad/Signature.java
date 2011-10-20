package com.gregheartsfield.jsigpad;

import org.codehaus.jackson.map.*;
import java.util.List;
import java.io.IOException;

class Signature {
    String json;
    ObjectMapper mapper;
    public Signature(String json) {
        this.json = json;
        mapper = new ObjectMapper();
    }
    public void parse() throws IOException {
        List<Object> lines = mapper.readValue(json, List.class);
        System.out.println(lines.size() + " lines");
        for (Object l : lines) {
            System.out.println(l);
        }
    }
}