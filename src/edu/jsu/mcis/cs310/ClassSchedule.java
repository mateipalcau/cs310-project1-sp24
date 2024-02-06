package edu.jsu.mcis.cs310;
//Matei Palcau
import com.github.cliftonlabs.json_simple.*;
import com.opencsv.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class ClassSchedule {
    
    private final String CSV_FILENAME = "jsu_sp24_v1.csv";
    private final String JSON_FILENAME = "jsu_sp24_v1.json";
    
    private final String CRN_COL_HEADER = "crn";
    private final String SUBJECT_COL_HEADER = "subject";
    private final String NUM_COL_HEADER = "num";
    private final String DESCRIPTION_COL_HEADER = "description";
    private final String SECTION_COL_HEADER = "section";
    private final String TYPE_COL_HEADER = "type";
    private final String CREDITS_COL_HEADER = "credits";
    private final String START_COL_HEADER = "start";
    private final String END_COL_HEADER = "end";
    private final String DAYS_COL_HEADER = "days";
    private final String WHERE_COL_HEADER = "where";
    private final String SCHEDULE_COL_HEADER = "schedule";
    private final String INSTRUCTOR_COL_HEADER = "instructor";
    private final String SUBJECTID_COL_HEADER = "subjectid";
    
    public String convertCsvToJsonString(List<String[]> csv) {
        String s;
        s=getInputFileData("jsu_sp24_v1.csv");
        List<String[]> full= getCsv(s);
        Iterator<String[]> iterator =full.iterator();
        JsonArray records=new JsonArray();
        LinkedHashMap obj=new LinkedHashMap();
        LinkedHashMap obj1=new LinkedHashMap();
        LinkedHashMap obj2=new LinkedHashMap();
        LinkedHashMap obj3=new LinkedHashMap();
        JsonArray obj4=new JsonArray();
        obj.put("scheduletype",obj1);
        obj.put("subject",obj2);
        obj.put("course",obj3);
        obj.put("section", obj4);
        
        
        LinkedHashMap<String,String> jsonRecord = new LinkedHashMap<>();
        
        
        
        
        
        if(iterator.hasNext()){
            String[] headings=iterator.next();
            while(iterator.hasNext()){
                String[] csvRecord=iterator.next();
                
                for(int i=0;i<headings.length;++i){
                    jsonRecord.put(headings[i].toLowerCase(),csvRecord[i]);
                }
                obj1.put(jsonRecord.get("type"),jsonRecord.get("schedule"));
                
                obj2.put(jsonRecord.get("num").substring(0, 3).trim(),jsonRecord.get("subject"));
                
                LinkedHashMap obj3_1=new LinkedHashMap();
                obj3.put(jsonRecord.get("num"), obj3_1);
                obj3_1.put("subjectid",jsonRecord.get("num").substring(0, 3).trim());
                obj3_1.put("num",jsonRecord.get("num").substring(3).trim());
                obj3_1.put("description",jsonRecord.get("description"));
                obj3_1.put("credits",Integer.parseInt(jsonRecord.get("credits")));
                
                LinkedHashMap obj4_1=new LinkedHashMap();
                obj4_1.put("crn",Integer.parseInt(jsonRecord.get("crn")));
                obj4_1.put("subjectid",jsonRecord.get("num").substring(0, 3).trim());
                obj4_1.put("num",jsonRecord.get("num").substring(3).trim());
                obj4_1.put("section",jsonRecord.get("section"));
                obj4_1.put("type",jsonRecord.get("type"));
                obj4_1.put("start",jsonRecord.get("start"));
                obj4_1.put("end",jsonRecord.get("end"));
                obj4_1.put("days",jsonRecord.get("days"));
                obj4_1.put("where",jsonRecord.get("where"));
                
                JsonArray obj4_1_1=new JsonArray();
                obj4_1.put("instructor",obj4_1_1);
                String[] names=jsonRecord.get("instructor").split(",");
                for(int i=0;i<names.length;++i){
                    obj4_1_1.add(names[i]);
                }
                obj4.add(obj4_1);
                
                
                
                
                
            }
        }
        
        
        
        String jsonString=Jsoner.serialize(obj);
        
        
     
        return jsonString;
    }
    
    public String convertJsonToCsvString(JsonObject json) {
        
        return ""; // remove this!
        
    }
    
    public JsonObject getJson() {
        
        JsonObject json = getJson(getInputFileData(JSON_FILENAME));
        return json;
        
    }
    
    public JsonObject getJson(String input) {
        
        JsonObject json = null;
        
        try {
            json = (JsonObject)Jsoner.deserialize(input);
        }
        catch (Exception e) { e.printStackTrace(); }
        
        return json;
        
    }
    
    public List<String[]> getCsv() {
        
        List<String[]> csv = getCsv(getInputFileData(CSV_FILENAME));
        return csv;
        
    }
    
    public List<String[]> getCsv(String input) {
        
        List<String[]> csv = null;
        
        try {
            
            CSVReader reader = new CSVReaderBuilder(new StringReader(input)).withCSVParser(new CSVParserBuilder().withSeparator('\t').build()).build();
            csv = reader.readAll();
            
        }
        catch (Exception e) { e.printStackTrace(); }
        
        return csv;
        
    }
    
    public String getCsvString(List<String[]> csv) {
        
        StringWriter writer = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(writer, '\t', '"', '\\', "\n");
        
        csvWriter.writeAll(csv);
        
        return writer.toString();
        
    }
    
    private String getInputFileData(String filename) {
        
        StringBuilder buffer = new StringBuilder();
        String line;
        
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        
        try {
        
            BufferedReader reader = new BufferedReader(new InputStreamReader(loader.getResourceAsStream("resources" + File.separator + filename)));

            while((line = reader.readLine()) != null) {
                buffer.append(line).append('\n');
            }
            
        }
        catch (Exception e) { e.printStackTrace(); }
        
        return buffer.toString();
        
    }
    
}