package CollectionExample.chapter8;

import java.util.Comparator;

public class DescendingComparator implements Comparator<Car> {

    @Override
    public int compare(Car o1, Car o2) {
        if(o1.getRpm() < o2.getRpm()) { return -1; }
        else if( o1.getRpm() == o2.getRpm() ) { return 0; }
        else return 1;
    }
}
