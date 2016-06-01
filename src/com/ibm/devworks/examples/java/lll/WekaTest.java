package com.ibm.devworks.examples.java.lll;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import weka.clusterers.SimpleKMeans;
import weka.clusterers.Clusterer;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.NominalPrediction;
import weka.classifiers.evaluation.NumericPrediction;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class WekaTest {
	private static SimpleKMeans kmeans; /* kmeans object. Static owned by class */
    private static int[] assignments;   /* array that will contain all of the
                                        assignments for the cluster*/
    private static Instances data;

    /* Constructor */
    public WekaTest() {
        kmeans = new SimpleKMeans();
    }
	
	public static BufferedReader readDataFile(String filename) {
    	BufferedReader inputReader = null;
    	InputStream in = WekaTest.class.getClassLoader().getResourceAsStream(filename);
    	if (in == null) System.err.println("File not found: " + filename);
    	inputReader = new BufferedReader(new InputStreamReader(in));
        return inputReader;
    }
    
    /* Takes the cluster number (int) and returns an array of the indices
    of all the instances in the cluster*/
    public static int[] getInstancesInCluster(int clusterNum) {
        double[] clusterSizes = kmeans.getClusterSizes();
        int[] instances = new int[(int)clusterSizes[clusterNum]];

        int j = 0;
        for (int i = 0; i < assignments.length; i++) {
            if (assignments[i] == clusterNum) {
                instances[j] = i;
                j++;
            }
        }
        int i = 0;
        System.out.println("Instances in cluster 0");
        for (int instance : instances) {
            System.out.printf("Instance %d" , instance);
            System.out.println();
            i++;
        }
        return instances;
    }
    
    public String getRecommendedDoctor(int[] instancesIndices) {
        Instance current;
        Map<String, Integer> doctorRatings = new HashMap<>();
        Map<Integer, String> doctorIndices =
                new Hashtable<Integer, String>() {
                    {
                        put(0, "Rey");
                        put(1, "Anderson");
                        put(2, "Blackwell");
                        put(3, "Cooley");
                        put(4, "Carson");
                        put(5, "Drew");
                        put(6, "Urbani");
                    }};
        for(int i = 0; i < instancesIndices.length; i++) {
            String tempDoc;
            int tempRating;
            current = data.get(instancesIndices[i]);
            tempDoc = doctorIndices.get((int)current.value(10));
            if (doctorRatings.containsKey(tempDoc)) {
                tempRating = (int)current.value(11);
                tempRating += doctorRatings.get(tempDoc);
                doctorRatings.put(tempDoc, tempRating);
            } else {
                tempRating = (int)current.value(11);
                doctorRatings.put(tempDoc, tempRating);
            }
        }
        String doctor = Collections.max(doctorRatings.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
        return doctor;
    }


    public static Instances filterInstances(Instances inst) throws Exception{
        // Mark the columns with non relevant attributes to be ignored.
        int[] ignoreAttributeArray = {1, 2, 5};

        Remove ignore = new Remove();

        // Prepare and use filters to ignore attributes
        ignore.setAttributeIndicesArray(ignoreAttributeArray);
        ignore.setInvertSelection(false);
        ignore.setInputFormat(inst);

        return Filter.useFilter(inst, ignore);
    }

    /* Generates the simplekmeans object and trains it */
    public static boolean generateModel() throws Exception {

        // Read datafile and convert to instance
        BufferedReader datafile = readDataFile("mock_health_data.txt");
        data = new Instances(datafile);

        data = filterInstances(data);

        // Set parameters to build clusterer
        kmeans.setSeed(10);
        // This is the important parameter to set
        kmeans.setPreserveInstancesOrder(true);
        kmeans.setNumClusters(50);
        kmeans.buildClusterer(data);

        // This array returns the cluster number (starting with 0) for each instance
        // The array has as many elements as the number of instances
        assignments = kmeans.getAssignments();

        /*int j=0;
        for(int clusterNum : assignments) {
            System.out.printf("Instance %d -> Cluster %d", j, clusterNum);
            System.out.println();
            j++;
        }*/
        return true;
    }

    public static int clusterInstance(Instance newInst) throws Exception{
        //System.out.println(newInst);
        return kmeans.clusterInstance(newInst);
    }

    public static void main(String[] args) throws Exception {
        WekaTest kmeansModel = new WekaTest();
        kmeansModel.generateModel();

    }
}