package edu.iis.mto.similarity;

import edu.iis.mto.search.SearchResult;
import edu.iis.mto.search.SequenceSearcher;

import java.util.HashMap;

public class SequenceSearcherDoubler implements SequenceSearcher {

    private int countResults;
    private HashMap<Integer, SearchResult> searchResultHashMap;

    public SequenceSearcherDoubler(HashMap<Integer, SearchResult> searchResultHashMap) {
        this.searchResultHashMap = searchResultHashMap;
        countResults = 0;
    }

    public SequenceSearcherDoubler() {
        searchResultHashMap = null;
        countResults = 0;
    }

    @Override
    public SearchResult search(int key, int[] seq) {
    if(searchResultHashMap.get(key).isFound()){
        countResults++;
    }
    return searchResultHashMap.get(key);
    }

    public int getCountResults() {
        return countResults;
    }
}
