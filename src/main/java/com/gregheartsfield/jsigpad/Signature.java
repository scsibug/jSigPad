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
import java.io.OutputStream;
import java.net.URL;
import javax.imageio.ImageIO;

class Signature {
    String json;
    ObjectMapper mapper;
    BufferedImage img;
    Color penColor = Color.BLACK;
    int width = 0;
    int height = 0;

    public Signature(String json, int width, int height) {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.json = json;
        this.width = width;
        this.height = height;
        mapper = new ObjectMapper();
    }

    public void setPenColor(java.awt.Color c) {
        this.penColor = c;
    }

    private void parse() throws IOException {
        Graphics g = img.getGraphics();
        g.setColor(penColor);
        List<Map<String,Integer>> lineObj = mapper.readValue(json, new TypeReference<List<Map<String,Integer>>>() { });
        for (Map<String,Integer> m : lineObj) {
            g.drawLine(m.get("lx"), m.get("ly"), m.get("mx"), m.get("my"));
        }
    }

    public void write(OutputStream os) throws IOException {
        parse();
        ImageIO.write(img, "png", os);
    }

    public void saveToFile(String filename) throws IOException {
        parse();
        File outputfile = new File(filename);
        ImageIO.write(img, "png", outputfile);
    }

}