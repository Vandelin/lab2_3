package edu.iis.mto.similarity;

import com.sun.deploy.util.ArrayUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.*;

public class SimilarityFinderTest {

    private SequenceSearcherDoubler searcherDoubler;
    private SimilarityFinder similarityFinder;
    private HashMap<Integer, SearchResult> searchResult;

    @Before
    public void initialize(){
        searcherDoubler = new SequenceSearcherDoubler();
        similarityFinder = new SimilarityFinder(searcherDoubler);
        searchResult = new HashMap<>();
    }

    @Test
    public void jackardSimiliarityBothOfSeqAreEmpty(){
        int[] seq = {};
        int[] seq1 = {};
        Assert.assertEquals(1, similarityFinder.calculateJackardSimilarity(seq, seq1), 0.01);
    }

    @Test
    public void jackardSimiliarityBothOfSeqAreSame() {
        int[] seq = {1, 2, 3, 4};
        int[] seq1 = {1, 2, 3, 4};
        searchResult = expectedResults(seq, seq1);
        searcherDoubler = new SequenceSearcherDoubler(searchResult);
        similarityFinder = new SimilarityFinder(searcherDoubler);
        Assert.assertEquals(1, similarityFinder.calculateJackardSimilarity(seq, seq1), 0.001);
    }

    @Test
    public void jackardSimiliarityWithDiffrentSeqLenghts() {
        int[] seq = {1, 2, 3, 6, 7, 8};
        int[] seq1 = {1, 2, 3, 7, 4};
        searchResult = expectedResults(seq, seq1);
        searcherDoubler = new SequenceSearcherDoubler(searchResult);
        similarityFinder = new SimilarityFinder(searcherDoubler);
        Assert.assertEquals(0.57, similarityFinder.calculateJackardSimilarity(seq, seq1), 0.01);
    }



    private HashMap<Integer, SearchResult> expectedResults(int[] seq, int[] seq1){
        HashMap<Integer, SearchResult> temp = new HashMap<>();
        Boolean bool;
        if(seq1.length> seq.length){
            int[] tempSeq = seq1;
            seq1=seq;
            seq=tempSeq;
        }

        for (int key:
             seq) {
            bool = false;

            for (int key1:
                 seq1) {
                bool = key==key1 || bool? true: false;
            }
            temp.put(Integer.valueOf(key), SearchResult.builder().withFound(bool).build());
        }

        /*for(Object key: temp.keySet())
            System.out.println(key + " " +temp.get(key).isFound());*/

        return temp;
    }

    /*private double expectedResult(HashMap<Integer, SearchResult> key, int[] seq2){
        double temp = 0;
        for (int keySeq:
             seq2) {
            temp += key.containsKey(Integer.valueOf(keySeq))?1:0;
        }
        return temp/(key.size()+seq2.length-temp);
    }*/


}