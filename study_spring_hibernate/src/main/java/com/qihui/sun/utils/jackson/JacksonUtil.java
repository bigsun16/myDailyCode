package com.qihui.sun.utils.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;

public class JacksonUtil {
    private static final ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) throws JsonProcessingException {
        String arrayJson = "[\"aa\",\"bb\"]";
        String[] array = mapper.readValue(arrayJson,String[].class);
        System.out.println(array.length);
    }

    private static void testZoo() throws JsonProcessingException {

        Zoo zoo = new Zoo();
        zoo.setName("野生");
        CatAnimal cat = new CatAnimal();
        cat.setId("1");
        cat.setNum(1);
        cat.setName("Tom");
        zoo.setAnimal(Collections.singletonList(cat));
        String string = mapper.writeValueAsString(zoo);
        System.out.println(string);
        String json = "{\"name\":\"野生\",\"animal\":[{\"id\":\"1\",\"animalType\":\"Cat\"}]}";
        Zoo zoo1 = mapper.readValue(string, Zoo.class);
//        Animal object = GsonUtil.parserJson2Object(string,Zoo.class);
        System.out.println(zoo1);
    }
}
