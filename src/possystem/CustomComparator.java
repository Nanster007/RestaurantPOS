package possystem;

import java.util.Comparator;

/**
 *
 * @author tylar
 */
//customComparator allows for easy sorting of shifts by startDate
public class CustomComparator implements Comparator<Shift>{
    
    @Override
    public int compare(Shift shift1, Shift shift2){
        return shift1.getSetStart().compareTo(shift2.getSetStart());
    }
    
}
