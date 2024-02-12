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
        
        Iterator<String[]> iterator =csv.iterator();
        
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
                    obj4_1_1.add(names[i].trim());
                }
                obj4.add(obj4_1);
                
                
                
                
                
            }
        }
        
        
        
        String jsonString=Jsoner.serialize(obj);
        
        
     
        return jsonString;
    }
    
    public String convertJsonToCsvString(JsonObject json) {
        Object j=new JsonObject();
        JsonObject j1=new JsonObject();
        JsonObject j2=new JsonObject();
        List list=new ArrayList();
        String s;
        list=(ArrayList)json.get("section");
        j=list.get(0);
        String jsonString=Jsoner.serialize(j);
        j2=getJson(jsonString);
        String jsonString1=Jsoner.serialize(j2);
        
        
        
        
        ArrayList<String> type = new ArrayList<String>();
        ArrayList<String> schedule = new ArrayList<String>();
        ArrayList<String> schedule1 = new ArrayList<String>();
        LinkedHashMap<String,String> scheduletype=new LinkedHashMap();
        LinkedHashMap <String,String> subject=new LinkedHashMap();
        LinkedHashMap <String,Object>  course=new LinkedHashMap();
        
        
        List<String[]> addresses = new ArrayList<String[]>();
        String[] names={"crn","subject","num","description","section","type","credits","start","end","days","where","schedule","instructor"};
        addresses.add(names);
        
        
        
        for (HashMap.Entry<String, Object> entry : json.entrySet()){
            String key = entry.getKey();
            if(key.equalsIgnoreCase("scheduletype")){
                Object obj=new JsonObject();
                obj=entry.getValue();
                String jsonString_1=Jsoner.serialize(obj);
                JsonObject j_1=new JsonObject();
                j_1=getJson(jsonString_1);
                for(HashMap.Entry<String, Object> entry1 : j_1.entrySet()){
                    String key1=entry1.getKey();
                    Object obj1=new JsonObject();
                    obj1=entry1.getValue();
                    String jString=Jsoner.serialize(obj1);
                    
                    
                    scheduletype.put(key1, jString);
                    
                    
                }
                
            }
            
            if(key.equalsIgnoreCase("subject")){
                Object obj=new JsonObject();
                obj=entry.getValue();
                String jsonString_1=Jsoner.serialize(obj);
                JsonObject j_1=new JsonObject();
                j_1=getJson(jsonString_1);
                for(HashMap.Entry<String, Object> entry1 : j_1.entrySet()){
                    String key1=entry1.getKey();
                    Object obj1=new JsonObject();
                    obj1=entry1.getValue();
                    String jString=Jsoner.serialize(obj1);
                    
                    
                    subject.put(key1, jString);
                }
                 
            }
            
            if(key.equalsIgnoreCase("course")){
                Object obj=new JsonObject();
                obj=entry.getValue();
                String jsonString_1=Jsoner.serialize(obj);
                JsonObject j_1=new JsonObject();
                j_1=getJson(jsonString_1);
                String t1=entry.getValue().toString();
                
                for(HashMap.Entry<String, Object> entry1 : j_1.entrySet()){
                    LinkedHashMap <String,String> course_object=new LinkedHashMap();
                    String key1=entry1.getKey();
                    Object obj1=new JsonObject();
                    obj1=entry1.getValue();
                    String jsonString_2=Jsoner.serialize(obj1);
                    JsonObject j_2=new JsonObject();
                    j_2=getJson(jsonString_2);
                    String t=entry1.getValue().toString();
                    course.put(key1,course_object);
                                        
                    
                    course.put(key1, course_object);
                    for(HashMap.Entry<String,Object> entry2: j_2.entrySet()){
                        String key2=entry2.getKey();
                        String jString=entry2.getValue().toString();
                        
                        course_object.put(key2, jString);
                    }
                }
                 
            }
        }
        for(HashMap.Entry<String, Object> entry:json.entrySet()){
            
            String key=entry.getKey();
            if(key.equalsIgnoreCase("section")){
                
                List list1=new ArrayList(); 
                
                list1=(ArrayList)entry.getValue();
                
                for(Object entry1 : list1){
                    
                    Object obj1=new JsonObject();
                    obj1=entry1;
                    String jsonString_1=Jsoner.serialize(obj1);
                    JsonObject j_1=new JsonObject();
                    j_1=getJson(jsonString_1);
                    String v=null;
                    String n = null;
                    String[] addressesArr  = new String[13];
                    
                    
                    Object o=new JsonObject();
                    Object o1=new JsonObject();
                    Object o2=new JsonObject();
                    JsonObject oJ=new JsonObject();
                    
                    for(HashMap.Entry<String, Object> entry2 : j_1.entrySet()){
                        String key2=entry2.getKey();
                        
                        
                        if(key2.equalsIgnoreCase("crn")){
                            Object obj2=new JsonObject();
                            obj2=entry2.getValue();
                            String x=Jsoner.serialize(obj2);
                            addressesArr[0]=x;  
                        }
                        if(key2.equalsIgnoreCase("subjectid")){
                            Object obj2=new JsonObject();
                            obj2=entry2.getValue();
                            String x=Jsoner.serialize(obj2);
                            String ad;
                            
                            v=Jsoner.serialize(obj2);
                            v=v.substring(1, v.length()-1);
                            
                            ad=subject.get(x.substring(1, x.length()-1));
                            addressesArr[1]=ad.substring(1, ad.length()-1);
                            
                        }
                        if(key2.equalsIgnoreCase("num")){
                            Object obj2=new JsonObject();
                            obj2=entry2.getValue();
                            n=Jsoner.serialize(obj2);
                            n=n.substring(1, n.length()-1);
                             //?????????
                            
                        }
                        if(key2.equalsIgnoreCase("section")){
                            Object obj2=new JsonObject();
                            obj2=entry2.getValue();
                            String x=Jsoner.serialize(obj2);
                            addressesArr[4]=x.substring(1, x.length()-1);  
                            
                        }
                        if(key2.equalsIgnoreCase("type")){
                            Object obj2=new JsonObject();
                            obj2=entry2.getValue();
                            String x=Jsoner.serialize(obj2);
                            addressesArr[5]=x.substring(1, x.length()-1);
                            String ad1;
                            ad1=scheduletype.get(x.substring(1, x.length()-1)); 
                            addressesArr[11]=ad1.substring(1, ad1.length()-1);
                            
                            
                            
                        }
                        if(key2.equalsIgnoreCase("start")){
                            Object obj2=new JsonObject();
                            obj2=entry2.getValue();
                            String x=Jsoner.serialize(obj2);
                            addressesArr[7]=x.substring(1, x.length()-1);
                            
                        }
                        if(key2.equalsIgnoreCase("end")){
                            Object obj2=new JsonObject();
                            obj2=entry2.getValue();
                            String x=Jsoner.serialize(obj2);
                            addressesArr[8]=x.substring(1, x.length()-1);
                            
                        }
                        if(key2.equalsIgnoreCase("days")){
                            Object obj2=new JsonObject();
                            obj2=entry2.getValue();
                            String x=Jsoner.serialize(obj2);
                            addressesArr[9]=x.substring(1, x.length()-1);
                            
                        }
                        if(key2.equalsIgnoreCase("where")){
                            Object obj2=new JsonObject();
                            obj2=entry2.getValue();
                            String x=Jsoner.serialize(obj2);
                            addressesArr[10]=x.substring(1, x.length()-1);
                            
                        }
                        if(key2.equalsIgnoreCase("instructor")){
                            
                            List list2=new ArrayList();
                            list1=(ArrayList)entry2.getValue();
                            addressesArr[12]="";
                            for(Object entry3:list1){
                                Object obj3=new JsonObject();
                                obj3=entry3;
                                String jsonString_3=Jsoner.serialize(obj3);
                                addressesArr[12]=addressesArr[12]+jsonString_3.substring(1, jsonString_3.length()-1)+", ";
                                
                            }
                            addressesArr[12]=addressesArr[12].substring(0, addressesArr[12].length()-2);
                            
                            
                            
                        }
                        
                        
                        
                    }
                    addressesArr[2]=v+" "+n;
                    o=course.get(addressesArr[2]);
                    
                    String js1=Jsoner.serialize(o);
                    oJ=getJson(js1);
                    String js2=oJ.get("description").toString();
                    addressesArr[3]=js2;
                    String js3=oJ.get("credits").toString();
                    addressesArr[6]=js3;
                    type.add(js3);
                    
                    
                    
                    


                    
                    
                    
                    
                    addresses.add(addressesArr);
                    
                    
                    
                }
                
                
                 
            }

        } 

            
        
        

        String t=getCsvString(addresses);
        
        return t;
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