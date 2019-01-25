import java.util.PriorityQueue;

public class PriorityQueueTestDrive {

    public static void main(String[] args) {
        PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>();

        pQueue.add(6);
        pQueue.add(11);
        pQueue.add(3);
        pQueue.add(12);

        System.out.println("peek ->  " + pQueue.peek());

        int count = pQueue.size();

        for (int i = 0; i < count; i++) {
            System.out.println(pQueue.poll());
        }
    }

}
