package com.ibm.devworks.examples.java.lll;

import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

import java.util.Hashtable;
import java.util.Map;

/**
 * Created by Darthfrazier on 5/31/16.
 */
public class ClustererClient {
    /* This is a hashtable that maps nominal classes to their values. This is workaround
    to a bug I was dealing with. see Readme
     */
    private static final Map<String,Integer> nominalValues =
            new Hashtable<String,Integer>() {{
        put("Female",0);put("Male",1);put("Mandarin",0);put("Spanish",1);
        put("English",2);put("Japanese",3);put("French",4);put("Klingon",5);
        put("Money",6);put("Black",0);put("Asian",1);put("Latino",2);
        put("White",3);put("Native_American",4);put("Ugly",5);put("Depression",0);
        put("Anxiety",1);put("Psychosis",2);put("Headache",0);put("Nausea",1);
        put("Aching",2);put("Overheating",3);put("Pain",4);put("Bleeding",0);
        put("Rash",1);put("Bruise",2);put("Rey",0);put("Anderson",1);put("Blackwell",2);
        put("Cooley",3);put("Carson",4);put("Drew",5);put("Urbani",6);
    }};

    /* Take an array of strings containing the values of the attributes of the
    instance. Numerical values will be casted to int */
    public static Instance createInstance(String[] user) throws Exception {
        /* Instance containing data */
        Instances data = new Instances(WekaTest.readDataFile("mock_health_data.txt"));
        data = WekaTest.filterInstances(data);
        /* New array containing all the attributes in this data file */
        Attribute[] attribute = new Attribute[12];
        /* Set containing the indices of the attributes that we know are numerical.
         Hardcoded bad style. */

        /* Get the attributes from the data and put them all in an array */
        int j = 0;
        for(int i = 0; i < 12; i++){
            attribute[i] = data.attribute(i);
        }

        /* Create new Instances */
        Instance newInst = new DenseInstance(12);
        /* set the values of the attributes */
        for (int i = 0; i < 12; i++){
            double test;

            if (attribute[i].isNumeric()){
                test = Double.parseDouble(user[i]);
                newInst.setValue(attribute[i], test);
            }
            else if (attribute[i].isNominal()) {
            	newInst.setValue(attribute[i], nominalValues.get(user[i]));
            }
            else newInst.setValue(attribute[i], user[0]);
        }
        return newInst;
    }


    /* Main class to test the client */
    public static void main(String[] args) throws Exception {
        int cluster;
        String[] testData = {"1","Female","Mandarin",
                            "-7.147","111.6679","Asian",
                            "146927.69","Depression","Pain","Bruise","Carson","4"};

        WekaTest kmeans =  new WekaTest();
        kmeans.generateModel();
        Instance newInst = createInstance(testData);
        cluster = kmeans.clusterInstance(newInst);
        kmeans.getInstancesInCluster(cluster);
        System.out.println();
        System.out.println();
        System.out.println(cluster);
    }
}

