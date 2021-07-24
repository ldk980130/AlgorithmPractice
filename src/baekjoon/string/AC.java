//백준 5340번 골드5 AC

package baekjoon.string;

import java.util.*;

class TestCase{
    int arrayLength;
    String array;

    Deque<String> deque = new ArrayDeque<>();
    boolean direction = false;
    boolean isError = false;

    public TestCase(int arrayLength, String array) {
        this.arrayLength = arrayLength;
        this.array = array;

        String[] intStr = array.replace("[", "").replace("]", "")
                .split(",");

        for (String s : intStr) {
            if(!s.equals("")) deque.push(s);
        }
    }

    public void reverse(){
        direction = direction ? false : true;
    }

    public void delete(){
        if(deque.isEmpty()) isError = true;

        if(!deque.isEmpty() && direction) deque.removeFirst();
        else if(!deque.isEmpty()) deque.removeLast();
    }

    public String getArray(){

        Iterator<String> iter;
        if(direction) iter = deque.iterator();
        else iter = deque.descendingIterator();

        String[] arr = new String[deque.size()];
        int index = 0;
        while (iter.hasNext()) {
            arr[index++] = iter.next();
        }

        return "[" + String.join(",", arr) + "]";
    }
}

public class AC {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        TestCase[] testCase = new TestCase[t];
        String[] methods = new String[t];
        for (int i = 0; i < t; i++) {
            methods[i] = sc.next();

            int arrayLength = sc.nextInt();
            String array = sc.next();
            testCase[i] = new TestCase(arrayLength, array);
        }

        for (int i = 0; i < t; i++) {

            while(methods[i].contains("RR")){
                methods[i] = methods[i].replaceAll("RR", "");
            }

            for (int j = 0; j < methods[i].length(); j++) {

                if(methods[i].charAt(j)=='R') testCase[i].reverse();
                else if(methods[i].charAt(j)=='D') testCase[i].delete();
            }
            System.out.println(
                    (testCase[i].isError) ? "error" : testCase[i].getArray()
            );
        }


    }
}
