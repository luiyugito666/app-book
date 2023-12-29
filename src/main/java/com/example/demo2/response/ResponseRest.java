package com.example.demo2.response;

import java.util.ArrayList;
import java.util.HashMap;

public class ResponseRest {

    private ArrayList<HashMap<String,String>> metadata=new ArrayList<>();

    public ArrayList<HashMap<String, String>> getMetadata() {
        return metadata;
    }

    public void setMetadata(String tipo,String codigo,String date) {
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("tipo",tipo);
        map.put("codigo",codigo);
        map.put("dato",date);
        metadata.add(map);
    }
}
