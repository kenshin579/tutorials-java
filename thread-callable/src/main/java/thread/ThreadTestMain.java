package thread;

public class ThreadTestMain {
    public void startRunner() {
        for (int i = 100; i < 120; i++) {
            Thread th = new Thread(new CalcuRunner(i, this));
            th.start();
        }
    }

    public void callBack2(int sum) {
        System.out.println(sum);
    }

    public static void callBack(int sum) {
        System.out.println(sum);
    }

    public static void main(String[] args) {
        ThreadTestMain ttm = new ThreadTestMain();
        ttm.startRunner();
    }
}
