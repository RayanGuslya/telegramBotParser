package org.example.modelSearch;

import com.google.gson.Gson;

import java.io.FileReader;

public class GsonParser {
    Gson gson = new Gson();
    public Root parse(){
        try(FileReader reader = new FileReader("searchImage.json")){
            Root root = gson.fromJson(reader, Root.class);
            return root;
        }catch (Exception e){
            System.out.println("parsing error " + e.toString());
        }
        return null;
    }
}
