package com.inHealth.server.service;

import com.inHealth.server.model.graph.*;
import com.inHealth.server.repository.graph.HabitQuestionRepository;
import com.inHealth.server.repository.graph.PersonRepository;
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

import static org.apache.spark.sql.functions.col;
import static org.apache.spark.sql.functions.lit;

@Service
@Transactional
public class GraphPipelineService implements Serializable{

        @Autowired
        private PersonRepository personRepository;

    @Autowired
    private HabitQuestionRepository habitRepository;

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
            //    urls.add(path+"P_ALQ.csv");
            //    urls.add(path+"P_AUQ.csv");
            //    urls.add(path+"P_BPQ.csv");
            //     urls.add(path+"P_CDQ.csv");
            //    urls.add(path+"P_DIQ.csv");
            //    urls.add(path+"P_HEQ.csv");
            //    urls.add(path+"P_KIQ_U.csv");
            //     urls.add(path+"P_MCQ.csv");
            //   urls.add(path+"P_DPQ.csv");
            //     urls.add(path+"P_OSQ.csv");

            //   urls.add(path+"P_SLQ.csv");

            List<Dataset<Row>> csvDatas = new ArrayList<>();

            for(var i =0;i<urls.size();i++){
                csvDatas.add(spark.read().option("header", true).option("inferSchema", true).csv(urls.get(i)));
            }

            // Load the CSV file with headers and infer the schema
            Dataset<Row> data_demo = csvDatas.get(0);

            Dataset<Row> data_phy = csvDatas.get(1);

            Dataset<Row> data_w = csvDatas.get(2);
            Dataset<Row> data_h = csvDatas.get(3);

            Dataset<Row> temp_dp = data_demo.join(data_phy, data_demo.col("SEQN").equalTo(data_phy.col("SEQN")));
            Dataset<Row> temp_dpw = temp_dp.join(data_w, data_demo.col("SEQN").equalTo(data_w.col("SEQN")));
            Dataset<Row> temp_final = temp_dpw.join(data_h, data_demo.col("SEQN").equalTo(data_h.col("SEQN")));

            //Questions diet
            List<DietBehaviorQuestion> questions_diet = new ArrayList<>();
            questions_diet.add(new DietBehaviorQuestion("DBQ700","How healthy is the diet",""));
            questions_diet.add(new DietBehaviorQuestion("DBQ197","Past 30 day milk product consumption",""));
            questions_diet.add(new DietBehaviorQuestion("DBQ223A","You drink whole or regular milk",""));
            questions_diet.add(new DietBehaviorQuestion("DBD895","# of meals not home prepared",""));
            questions_diet.add(new DietBehaviorQuestion("DBD900","# of meals from fast food or pizza place",""));
            questions_diet.add(new DietBehaviorQuestion("DBD905","# of ready-to-eat foods in past 30 days",""));
            questions_diet.add(new DietBehaviorQuestion("DBD910","# of frozen meals/pizza in past 30 days",""));
            questions_diet.add(new DietBehaviorQuestion("DBQ930","{Are you/Is SP} the person who does most of the planning or preparing of meals in {your/SPs} family?",""));
            questions_diet.add(new DietBehaviorQuestion("DBQ935","{Do you/Does SP} share in the planning or preparing of meals with someone else?",""));


            List<HabitQuestion> questions_habit = new ArrayList<>();
            questions_habit.add(new HabitQuestion("ALQ111","Ever had a drink of any kind of alcohol",""));
            questions_habit.add(new HabitQuestion("ALQ121","During the past 12 months, about how often did {you/SP} drink any type of alcoholic beverage? PROBE: How many days per week, per month, or per year did {you/SP} drink?",""));

            System.out.println("Before save questions");
            System.out.println(questions_habit);
            habitRepository.saveAll(questions_habit);
            System.out.println("After save questions");
            List<HealthQuestion> questions_health = new ArrayList<>();


            List<HealthCondition> conditions_health = new ArrayList<>();

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

                    col("ALQ111").as("ALQ111"),
                    col("ALQ121").as("ALQ121")

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


                        List<BodyMeasures> bs= new ArrayList<>();
                        bs.add(new BodyMeasures(height,weight,date));
                        bs.add(new BodyMeasures(height,weight1,date1));


                        List<Answer> answers= new ArrayList<>();

                        for (var i=0;i<questions_habit.size();i++){
                            String idt=questions_habit.get(i).getId();
                            Double tempr=row.getAs(idt);

                            String text = (tempr!=null)?Double.toString( tempr):null;
                            HabitQuestion habitQuestion = questions_habit.get(i);

                            answers.add(new Answer(null,text,date,habitQuestion ));
                        }
                        person.setAnswers(answers);

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

