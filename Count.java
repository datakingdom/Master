/**
 * Created by gaowenshuo on 3/14/16.
 */

public class Count{
    int countValue; Count(){ countValue=0;
    }

    public void increment() { countValue++;  }
    public void decrement() { countValue--;  }
    public void reset() { countValue=0;  }
    public int getCountValue(){ return countValue; }
    public static void main(String args[]){
        Count c = new Count(); c.increment();
        System.out.println(c.getCountValue());
        c.reset(); System.out.println(c.getCountValue());
    }
}