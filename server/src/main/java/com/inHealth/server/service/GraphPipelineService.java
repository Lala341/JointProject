package com.inHealth.server.service;

import com.inHealth.server.model.graph.*;
import com.inHealth.server.repository.graph.*;
import com.inHealth.server.utils.Decoder;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.spark.api.java.function.ForeachFunction;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.transaction.annotation.Transactional;
import scala.Function1;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.stream.Collectors;

import static com.inHealth.server.utils.Decoder.decodeAnswer;
import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.lit;

@Service
@Transactional
public class GraphPipelineService implements Serializable{

        @Autowired
        private PersonRepository personRepository;

    @Autowired
    private HabitQuestionRepository habitRepository;

    @Autowired
    private DietBehaviourQuestionRepository dietRepository;

    @Autowired
    private HealthQuestionRepository healthRepository;

    @Autowired
    private HealthConditionRepository healthConditionRepository;

    @Autowired
    private SymptomRepository symptomRepository;

    @Autowired
    private BodyMeasuresRepository bodyRepository;
    private final String uri = "hdfs://34.237.242.179:9000";



    public void loadData() {
        try {
            String path= "hdfs://34.237.242.179:9000/survey-datasets/";

            SparkSession spark = SparkSession.builder()
                    .appName("Graph Load")
                    .master("local[*]")
                    .getOrCreate();

            List<String> urls = new ArrayList<>();
            urls.add(path+"P_DEMO.csv");
            urls.add(path+"P_PAQ.csv");
            urls.add(path+"P_WHQ.csv");
            urls.add(path+"P_ALQ.csv");
            urls.add(path+"P_DBQ.csv");
            urls.add(path+"P_AUQ.csv");
            urls.add(path+"P_BPQ.csv");
            urls.add(path+"P_CDQ.csv");
            urls.add(path+"P_DIQ.csv");
            urls.add(path+"P_HEQ.csv");
            urls.add(path+"P_KIQ_U.csv");
            urls.add(path+"P_MCQ.csv");
            urls.add(path+"P_DPQ.csv");
            urls.add(path+"P_OSQ.csv");
            urls.add(path+"P_SLQ.csv");

            List<Dataset<Row>> csvDatas = new ArrayList<>();

            for(var i =0;i<urls.size();i++){
                csvDatas.add(spark.read().option("header", true).option("inferSchema", true).csv(urls.get(i)));
            }
            Dataset<Row> data_demo=csvDatas.get(0);

            Dataset<Row> temp_final = csvDatas.stream()
                    .reduce((data1, data2) -> data1.join(data2, "SEQN"))
                    .orElseThrow(() -> new IllegalArgumentException("The list of Datasets is empty."));

            //Questions diet
            List<DietBehaviorQuestion> questions_diet = new ArrayList<>();
            questions_diet.add(new DietBehaviorQuestion("DBQ700","How healthy is the diet",""));
            questions_diet.add(new DietBehaviorQuestion("DBQ197","Past 30 day milk product consumption",""));
            questions_diet.add(new DietBehaviorQuestion("DBQ223A","You drink whole or regular milk",""));
            //questions_diet.add(new DietBehaviorQuestion("DBD895","# of meals not home prepared",""));
            //questions_diet.add(new DietBehaviorQuestion("DBD900","# of meals from fast food or pizza place",""));
            //questions_diet.add(new DietBehaviorQuestion("DBD905","# of ready-to-eat foods in past 30 days",""));
            //questions_diet.add(new DietBehaviorQuestion("DBD910","# of frozen meals/pizza in past 30 days",""));
            questions_diet.add(new DietBehaviorQuestion("DBQ930","{Are you/Is SP} the person who does most of the planning or preparing of meals in {your/SPs} family?",""));
            questions_diet.add(new DietBehaviorQuestion("DBQ935","{Do you/Does SP} share in the planning or preparing of meals with someone else?",""));
            questions_diet.add(new DietBehaviorQuestion("WHD080P","Started to smoke or began to smoke again",""));
            questions_diet.add(new DietBehaviorQuestion("WHD080Q","Ate more fruits, vegetables, saladsEnglish Text:How did {you/SP} try to lose weight?",""));
            //questions_diet.add(new DietBehaviorQuestion("WHD140","Self-reported greatest weight (pounds)",""));

            List<HabitQuestion> questions_habit = new ArrayList<>();
            questions_habit.add(new HabitQuestion("ALQ111","Ever had a drink of any kind of alcohol",""));
            questions_habit.add(new HabitQuestion("ALQ121","During the past 12 months, about how often did {you/SP} drink any type of alcoholic beverage? PROBE: How many days per week, per month, or per year did {you/SP} drink?",""));


            List<HealthCondition> conditions_health = new ArrayList<>();
            conditions_health.add(new HealthCondition("AUQ420","Audiometry Issue"));
            conditions_health.add(new HealthCondition("BPQ020","Hypertension"));
            conditions_health.add(new HealthCondition("CDQ001","Cardiovascular Health Issue"));
            conditions_health.add(new HealthCondition("DIQ010","Diabetes"));
            conditions_health.add(new HealthCondition("HEQ010","Hepatitis B"));
            conditions_health.add(new HealthCondition("HEQ030","Hepatitis C"));
            conditions_health.add(new HealthCondition("KIQ022","Kidney Conditions - Urology Issue"));
            conditions_health.add(new HealthCondition("DPQ020","Mental Health - Depression Screener Issue"));
            conditions_health.add(new HealthCondition("OSQ060","Osteoporosis"));
            conditions_health.add(new HealthCondition("SLQ050","Sleep Disorders"));
            conditions_health.add(new HealthCondition("MCQ010","Asthma"));
            conditions_health.add(new HealthCondition("MCQ160a","arthritis"));
            conditions_health.add(new HealthCondition("MCQ160b","heart failure"));
            conditions_health.add(new HealthCondition("MCQ160e","heart attack"));
            conditions_health.add(new HealthCondition("MCQ160d","angina"));
            conditions_health.add(new HealthCondition("MCQ160f","stroke"));
            conditions_health.add(new HealthCondition("MCQ160m","hyroid problem"));
            conditions_health.add(new HealthCondition("MCQ160p","COPD, emphysema, ChB"));
            conditions_health.add(new HealthCondition("MCQ160l","liver condition"));
            conditions_health.add(new HealthCondition("MCQ510a","Fatty liver"));
            conditions_health.add(new HealthCondition("MCQ510b","Liver fibrosis"));
            conditions_health.add(new HealthCondition("MCQ510c","Liver cirrhosis"));
            conditions_health.add(new HealthCondition("MCQ510d","Viral hepatitis"));
            conditions_health.add(new HealthCondition("MCQ510e","Autoimmune hepatitis"));
            conditions_health.add(new HealthCondition("MCQ550","gallstones"));
            conditions_health.add(new HealthCondition("MCQ220","cancer"));


            List<HealthQuestion> questions_health = new ArrayList<>();
            questions_health.add(new HealthQuestion("AUQ054","These next questions are about {your/SP's} hearing. Which statement best describes {your/SP's} hearing (without a hearing aid, personal sound amplifier, or other listening devices)? Would you say {your/his/her} hearing is excellent, good, that {you have/s/he has} a little trouble, moderate trouble, a lot of trouble, or {are you/is s/he} deaf?","",conditions_health.get(0)));
            //questions_health.add(new HealthQuestion("BPD035","How old {were you/was SP} when {you were/he/she was} first told that {you/he/she} had hypertension or high blood pressure?","",conditions_health.get(1)));
            //questions_health.add(new HealthQuestion("DID040","Age when first told you had diabetes","",conditions_health.get(3)));
            //questions_health.add(new HealthQuestion("DID250","How many times {have you/has SP} seen this doctor or other health professional in the past 12 months?","",conditions_health.get(3)));
            questions_health.add(new HealthQuestion("KIQ005","How often have urinary leakage?","",conditions_health.get(6)));
            questions_health.add(new HealthQuestion("KIQ480","How many times urinate in night?","",conditions_health.get(6)));
            questions_health.add(new HealthQuestion("DPQ010","Have little interest in doing things","",conditions_health.get(7)));
            questions_health.add(new HealthQuestion("DPQ020","Feeling down, depressed, or hopeless","",conditions_health.get(7)));
            questions_health.add(new HealthQuestion("DPQ030","Trouble sleeping or sleeping too much","",conditions_health.get(7)));
            questions_health.add(new HealthQuestion("DPQ040","Feeling tired or having little energy","",conditions_health.get(7)));
            questions_health.add(new HealthQuestion("DPQ050","Poor appetite or overeating","",conditions_health.get(7)));
            questions_health.add(new HealthQuestion("DPQ060","Feeling bad about yourself","",conditions_health.get(7)));
            questions_health.add(new HealthQuestion("DPQ090","Thoughts you would be better off deadEnglish Text:","",conditions_health.get(7)));
            questions_health.add(new HealthQuestion("OSQ150","Parents ever told had osteoporosis?","",conditions_health.get(8)));
            //questions_health.add(new HealthQuestion("SLD012","Number of hours usually sleep on weekdays or workdays.","",conditions_health.get(9)));
            questions_health.add(new HealthQuestion("SLQ120","How often feel overly sleepy during day?","",conditions_health.get(9)));
            questions_health.add(new HealthQuestion("MCQ230a","1st cancer - what kind was it?","",conditions_health.get(25)));

            List<Symptom> symptoms = new ArrayList<>();
            symptoms.add(new Symptom("AUQ060","Hear a whisper from across a quiet room?",conditions_health.get(0)));
            symptoms.add(new Symptom("AUQ070","Hear normal voice across a quiet room?",conditions_health.get(0)));
            symptoms.add(new Symptom("AUQ400","When began to have hearing loss?",conditions_health.get(0)));
            symptoms.add(new Symptom("AUQ430","Ever had 3 or more ear infections?",conditions_health.get(0)));
            symptoms.add(new Symptom("BPQ030","{Were you/Was SP} told on 2 or more different visits that {you/s/he} had hypertension, also called high blood pressure?",conditions_health.get(1)));
            symptoms.add(new Symptom("BPQ080","{Have you/Has SP} ever been told by a doctor or other health professional that {your/his/her} blood cholesterol level was high?",conditions_health.get(1)));
            symptoms.add(new Symptom("CDQ001","{Have you/Has SP} ever had any pain or discomfort in {your/her/his} chest?",conditions_health.get(2)));
            symptoms.add(new Symptom("CDQ009A","Pain in right arm",conditions_health.get(2)));
            symptoms.add(new Symptom("CDQ009B","Pain in right chest",conditions_health.get(2)));
            symptoms.add(new Symptom("CDQ009C","Pain in neck",conditions_health.get(2)));
            symptoms.add(new Symptom("CDQ009D","Pain in upper sternum",conditions_health.get(2)));
            symptoms.add(new Symptom("DIQ160","Ever told you have prediabetes",conditions_health.get(3)));
            symptoms.add(new Symptom("DIQ080","Has a doctor ever told {you/SP} that diabetes has affected {your/his/her} eyes or that {you/s/he} had retinopathy (ret-in-op-ath-ee)?",conditions_health.get(3)));
            symptoms.add(new Symptom("KIQ026","Ever had kidney stones?",conditions_health.get(6)));
            symptoms.add(new Symptom("MCQ520","Abdominal pain during past 12 months?",conditions_health.get(24)));




            habitRepository.saveAll(questions_habit);

            List<HabitQuestion> savedQuestions = habitRepository.findAllById(
                    questions_habit.stream().map(HabitQuestion::getId).collect(Collectors.toList())
            );
            questions_habit.clear();
            questions_habit.addAll(savedQuestions);




            dietRepository.saveAll(questions_diet);

            List<DietBehaviorQuestion> savedQuestionsDiet = dietRepository.findAllById(
                    questions_diet.stream().map(DietBehaviorQuestion::getId).collect(Collectors.toList())
            );
            questions_diet.clear();
            questions_diet.addAll(savedQuestionsDiet);



            healthConditionRepository.saveAll(conditions_health);

            List<HealthCondition> savedCondtions = healthConditionRepository.findAllById(
                    conditions_health.stream().map(HealthCondition::getId).collect(Collectors.toList())
            );
            conditions_health.clear();
            conditions_health.addAll(savedCondtions);




            healthRepository.saveAll(questions_health);

            List<HealthQuestion> savedHealthQuestions = healthRepository.findAllById(
                    questions_health.stream().map(HealthQuestion::getId).collect(Collectors.toList())
            );
            questions_health.clear();
            questions_health.addAll(savedHealthQuestions);



            symptomRepository.saveAll(symptoms);

            List<Symptom> savedSumptoms = symptomRepository.findAllById(
                    symptoms.stream().map(Symptom::getId).collect(Collectors.toList())
            );
            symptoms.clear();
            symptoms.addAll(savedSumptoms);


            Dataset<Row> select_demo = temp_final.select(data_demo.col("SEQN").as("id"),
                    lit("NOT PROVIDED").as("name"),
                    col("RIAGENDR").as("gender"),
                    col("RIDAGEYR").as("age"),
                    col("RIDRETH3").as("race"),
                    col("DMDBORN4").as("country"),

                    col("PAD680").as("sedentaryMinutes"),
                    col("PAD675").as("moderateMinutes"),
                    col("PAD660").as("vigorousMinutes"),

                    col("WHD010").as("height"),
                    col("WHD020").as("weight"),
                    col("WHD050").as("weight1"),

                    col("ALQ111"),
                    col("ALQ121"),

                    col("DBQ700"),
                    col("DBQ197"),
                    col("DBQ223A"),
                    //col("DBD895"),
                    //col("DBD900"),
                    //col("DBD905"),
                    //col("DBD910"),
                    col("DBQ930"),
                    col("DBQ935"),
                    col("WHD080P"),
                    col("WHD080Q"),
                    //col("WHD140"),

                    col("AUQ054"),
                    //col("BPD035"),
                    //col("DID040"),
                    //col("DID250"),
                    col("KIQ005"),
                    col("KIQ480"),
                    col("DPQ010"),
                    col("DPQ020"),
                    col("DPQ030"),
                    col("DPQ040"),
                    col("DPQ050"),
                    col("DPQ060"),
                    col("DPQ090"),
                    col("OSQ150"),
                    //col("SLD012"),
                    col("SLQ120"),
                    col("MCQ230a"),

                    col("AUQ060"),
                    col("AUQ070"),
                    col("AUQ400"),
                    col("AUQ420"),
                    col("AUQ430"),
                    col("BPQ020"),
                    col("BPQ030"),
                    col("BPQ080"),
                    col("CDQ001"),
                    col("CDQ009A"),
                    col("CDQ009B"),
                    col("CDQ009C"),
                    col("CDQ009D"),
                    col("DIQ010"),
                    col("DIQ160"),
                    col("DIQ080"),
                    col("HEQ010"),
                    col("HEQ030"),
                    col("KIQ022"),
                    col("KIQ026"),
                    col("OSQ060"),
                    col("SLQ050"),
                    col("MCQ010"),
                    col("MCQ035"),
                    col("MCQ053"),
                    col("MCQ160a"),
                    col("MCQ160b"),
                    col("MCQ160e"),
                    col("MCQ160d"),
                    col("MCQ160f"),
                    col("MCQ160m"),
                    col("MCQ160p"),
                    col("MCQ160l"),
                    col("MCQ510a"),
                    col("MCQ510b"),
                    col("MCQ510c"),
                    col("MCQ510d"),
                    col("MCQ510e"),
                    col("MCQ520"),
                    col("MCQ550"),
                    col("MCQ220")




                    );

            String date = (LocalDate.now()).toString();
            String date1 = (LocalDate.now().minusYears(1)).toString();
            select_demo.show(1);
            List<Person> persons = select_demo
                    .toJavaRDD()
                    .map(row -> {
                        String id = row.getAs("id").toString();
                        String name = row.getAs("name");



                        String gender = row.getAs("gender").toString();
                        Double ageDouble = row.getAs("age");

                        Integer age = null;
                        if (ageDouble != null) {
                            age = ageDouble.intValue();
                        }

                        String race = row.getAs("race").toString();
                        String country = row.getAs("country").toString();

                        Demographics demo=new Demographics();
                        demo.setGender(gender);
                        demo.setAge(age);
                        demo.setCountry(country);
                        demo.setRace(race);


                        Double sedentaryMinutesd = row.getAs("sedentaryMinutes");
                        Double moderateMinutesd = row.getAs("moderateMinutes");
                        Double vigorousMinutesd = row.getAs("vigorousMinutes");

                        Integer sedentaryMinutes = (sedentaryMinutesd!=null)?sedentaryMinutesd.intValue():null;
                        Integer moderateMinutes = (moderateMinutesd!=null)?moderateMinutesd.intValue():null;
                        Integer vigorousMinutes = (vigorousMinutesd!=null)?vigorousMinutesd.intValue():null;

                        Person person = new Person();
                        person.setId(id);
                        person.setName(name);
                        person.setDemographics(demo);


                        PhysicalActivity phy= new PhysicalActivity();
                        phy.setSedentaryMinutes(sedentaryMinutes);
                        phy.setModerateMinutes(moderateMinutes);
                        phy.setVigorousMinutes(vigorousMinutes);
                        phy.setDate(date);

                        List<PhysicalActivity> phys= new ArrayList<>();
                        phys.add(phy);
                        person.setPhysicalActivity(phys);


                        Double height = row.getAs("height");
                        Double weight = row.getAs("weight");
                        Double weight1 = row.getAs("weight1");


                        person.addBodyMeasures(new BodyMeasures(id+date.toString(),height,weight,date));
                        person.addBodyMeasures(new BodyMeasures(id+date1.toString(),height,weight1,date1));



                        for (var i=0;i<questions_habit.size();i++){
                            String idt=questions_habit.get(i).getId();
                            Double tempr=row.getAs(idt);

                            String text = (tempr!=null)?Double.toString( tempr):null;
                            HabitQuestion habitQuestion =questions_habit.get(i);
                            String answerDecoded = decodeAnswer(text, habitQuestion.getId());
                            Answer answer = new Answer(idt+text, text, date, answerDecoded, habitQuestion);
                            person.addAnswer(answer);

                        }

                        for (var i=0;i<questions_diet.size();i++){
                            String idt=questions_diet.get(i).getId();
                            Double tempr=row.getAs(idt);

                            String text = (tempr!=null)?Double.toString( tempr):null;
                            DietBehaviorQuestion dietQuestion =questions_diet.get(i);
                            String answerDecoded = decodeAnswer(text, dietQuestion.getId());
                            Answer answer = new Answer(idt+text, text, date, answerDecoded, dietQuestion);
                            person.addAnswer(answer);

                        }

                        for (var i=0;i<questions_health.size();i++){
                            String idt=questions_health.get(i).getId();
                            Double tempr=row.getAs(idt);

                            String text = (tempr!=null)?Double.toString( tempr):null;
                            HealthQuestion healthQuestion =questions_health.get(i);
                            String answerDecoded = decodeAnswer(text, healthQuestion.getId());
                            Answer answer = new Answer(idt+text, text, date, answerDecoded, healthQuestion);
                            person.addAnswer(answer);

                        }

                        for (var i=0;i<symptoms.size();i++){
                            String idt=symptoms.get(i).getId();
                            Double tempr=row.getAs(idt);

                            if(tempr!=null && tempr>0){
                                ExperiencesSymptom sympto=new ExperiencesSymptom(symptoms.get(i),date);
                                person.addSymptom(sympto);
                            }

                        }

                        for (var i=0;i<conditions_health.size();i++){
                            String idt=conditions_health.get(i).getId();
                            Double tempr=row.getAs(idt);

                            if(tempr!=null && tempr>0){
                                 person.addhealthCondition(conditions_health.get(i));
                            }

                        }




                        return person;
                    })
                    .collect();


            System.out.println("Before save");
            personRepository.saveAll(persons.subList(0,10));
            System.out.println("After save");

            spark.stop();


        } catch (Exception e) {
            // Handle any errors that occur during the file loading process
            e.printStackTrace();
        }
    }



    public void dowloadFilestoHdfs(){

        String path ="https://testfinal1234567.s3.amazonaws.com/data/";
        List<String> urls = new ArrayList<>();
        urls.add(path+"P_DEMO.csv");
        urls.add(path+"P_ALQ.csv");
        urls.add(path+"P_AUQ.csv");
        urls.add(path+"P_BPQ.csv");
        urls.add(path+"P_CDQ.csv");
        urls.add(path+"P_DIQ.csv");
        urls.add(path+"P_DBQ.csv");
        urls.add(path+"P_HEQ.csv");
        urls.add(path+"P_KIQ_U.csv");
        urls.add(path+"P_MCQ.csv");
        urls.add(path+"P_DPQ.csv");
        urls.add(path+"P_OSQ.csv");
        urls.add(path+"P_PAQ.csv");
        urls.add(path+"P_SLQ.csv");
        urls.add(path+"P_WHQ.csv");

        String directoryPath = "/survey-datasets";

        for (String url : urls) {
            uploadToHdfs(url,directoryPath );
        }



    }


    public void uploadToHdfs(String url, String hdfsPath)  {
        try {
        // Initialize HDFS configuration and file system object
        Configuration conf = new Configuration();
        System.setProperty("HADOOP_USER_NAME", "root");
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        Path hdfswritepathuser = new Path(hdfsPath );

        System.out.println("Before create");
        if (!fs.exists(hdfswritepathuser)) {
            System.out.println("Before created");

            fs.mkdirs(hdfswritepathuser);
            System.out.println(" create");

        }
        System.out.println("Create folder");

        downloadFile( url, hdfsPath,  fs, conf);

        System.out.println("Create file");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void downloadFile(String url, String directoryPath, FileSystem fs, Configuration conf) {
        try {


            // Extract the file name from the URL
            String fileName = url.substring(url.lastIndexOf('/') + 1);

            // Create the output path in HDFS
            Path outputPath = new Path(directoryPath + "/" + fileName);

            // Download the file to a local temporary location
            URL fileUrl = new URL(url);
            InputStream inputStream = fileUrl.openStream();
            java.nio.file.Path tempFilePath = Files.createTempFile("temp", fileName);
            Files.copy(inputStream, tempFilePath, StandardCopyOption.REPLACE_EXISTING);
            inputStream.close();

            // Copy the file from local file system to HDFS
            Path localPath = new Path(tempFilePath.toString());
            fs.copyFromLocalFile(localPath, outputPath);

            // Delete the temporary local file
            Files.delete(tempFilePath);

            System.out.println("File downloaded and saved successfully: " + outputPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

