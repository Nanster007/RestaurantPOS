/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package possystem;

import java.util.Comparator;

/**
 *
 * @author tylar
 */
public class CustomComparator implements Comparator<Shift>{
    
    @Override
    public int compare(Shift shift1, Shift shift2){
        return shift1.getSetStart().compareTo(shift2.getSetStart());
    }
    
}
