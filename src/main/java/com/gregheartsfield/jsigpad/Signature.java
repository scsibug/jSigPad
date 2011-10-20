package com.gregheartsfield.jsigpad;

import org.codehaus.jackson.map.*;
import org.codehaus.jackson.type.*;
import java.util.List;
import java.util.Map;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.net.URL;
import javax.imageio.ImageIO;

class Signature {
    String json;
    ObjectMapper mapper;
    BufferedImage img;
    public Signature(String json) {
        this.json = json;
        mapper = new ObjectMapper();
    }

    public void parse() throws IOException {
        img = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        List<Map<String,Integer>> lineObj = mapper.readValue(json, new TypeReference<List<Map<String,Integer>>>() { });
        for (Map<String,Integer> m : lineObj) {
            System.out.println(m.get("lx"));
            System.out.println(m.get("ly"));
            System.out.println(m.get("mx"));
            System.out.println(m.get("my"));
            g.drawLine(m.get("lx"), m.get("ly"), m.get("mx"), m.get("my"));
        }
    }

    public void saveToFile(String filename) throws IOException {
        File outputfile = new File(filename);
        ImageIO.write(img, "png", outputfile);
    }

}