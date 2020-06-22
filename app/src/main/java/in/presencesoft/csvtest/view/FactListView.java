package in.presencesoft.csvtest.view;

import java.util.ArrayList;

import in.presencesoft.csvtest.pojos.FactListPojo;

public interface FactListView {
    void displayFacts(ArrayList<FactListPojo> pojos,String abTitle);
    void failedToGet(String errorCode);
}
