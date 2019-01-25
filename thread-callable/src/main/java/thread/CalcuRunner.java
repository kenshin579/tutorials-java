package thread;

/**
 * http://ash84.tistory.com/984
 */
public class CalcuRunner implements Runnable {
    int num = 0;
    ThreadTestMain callbackInstance = null;

    private CalcuRunner() {
    }

    public CalcuRunner(int init, ThreadTestMain callback) {
        this.num = init;
        this.callbackInstance = callback;
    }

    public void run() {
        int sum = num;
        for (int i = 0; i < 1000000; i++)
            sum += i;

        // ThreadTestMain.callBack(sum);
        this.callbackInstance.callBack2(sum);
    }
}