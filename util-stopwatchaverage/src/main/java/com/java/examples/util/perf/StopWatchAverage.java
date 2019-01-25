package com.java.examples.util.perf;

/**
 * "자바 성능을 결정짓는 코디 습관과 튜닝 이야기" 책에서
 *
 * @author 이상민
 */
public class StopWatchAverage {
    long startTime;
    long elapsedTime = 0;
    double totalElapsedTime;
    long runCount = 0;
    String currentName;
    boolean threadFlag = false;


    /**
     * 특별히 이름을 지정하지 않은 StopWatchAverage 객체를
     * 생성하고, timer를 시작한다.
     */
    public StopWatchAverage() {
        currentName = "";
        startTime = System.nanoTime();
    }

    /**
     * Thread를 사용하는지 여부를 지정하는 생성
     *
     * @param threadFlag Thread 사용 여부
     */
    public StopWatchAverage(boolean threadFlag) {
        changeMessage("", true, true);
    }

    /**
     * 메시지만을 지정하는 생성자
     *
     * @param message 추가로 명시할 메시지
     */
    public StopWatchAverage(String message) {
        changeMessage(message, false, true);
    }

    /**
     * 메시지와 Thread 사용 여부를 함께 지정하는 생성
     *
     * @param message    추가로 명시할 메시지
     * @param threadFlag Thread 사용 여부
     */
    public StopWatchAverage(String message, boolean threadFlag) {
        changeMessage(message, threadFlag, true);
    }

    /**
     * StopWatch의 시간 데이터를 초기화한다.
     */
    public void reset() {
        startTime = System.nanoTime();
        elapsedTime = 0;
        totalElapsedTime = 0;
        runCount = 0;
    }

    /**
     * 시간 측정을 시작한다.
     */
    public void start() {
        startTime = System.nanoTime();
        elapsedTime = 0;
    }

    /**
     * StopWatch를 멈추고 응답 시간 결과를
     * ArrayList에 담는다.
     */
    public void stop() {
        elapsedTime = System.nanoTime() - startTime;
        totalElapsedTime += elapsedTime;
        runCount++;
    }

    /**
     * 메시지를 지정한다.
     *
     * @param message    추가로 명시할 메시지
     * @param threadFlag Thread 사용여부
     * @param resetFlag  객체 생성시 StopWatch 리켓여부
     */
    private void changeMessage(String message, boolean threadFlag, boolean resetFlag) {
        String threadName = "";
        this.threadFlag = threadFlag;

        if (threadFlag) {
            threadName = " ThreadName= " + Thread.currentThread().getName();
        }

        currentName = "[" + message + threadName + "]";
        if (resetFlag) {
            reset();
        }
    }

    /**
     * StopWatch를 멈추고 마지막에(혹은 현재까지)
     * 수행된 시간을 리턴한다.
     *
     * @return 마지막에 수행된 밀리초
     */
    public double getElapsedMS() {
        if (elapsedTime == 0) {
            stop();
        }
        return elapsedTime / 1000000.0;
    }

    /**
     * StopWatch를 멈추고 마지막에(혹은 현재까지)
     * 수행된 시간을 리턴한다.
     *
     * @return 마지막에 수행된 나노초
     */
    public double getElapsedNano() {
        if (elapsedTime == 0) stop();

        return elapsedTime;
    }


    /**
     * 현재까지 수집된 횟수, 전체 수행 시간의 합,
     * 평균 수행 시간을 밀리초 단위로 리턴해준다.
     * (non-Javadoc)
     *
     * @return 수행 횟ㅅ, 전체 수행시간, 평균 수행시간
     * @see java.lang.Object#toString()
     */
    public String toString() {
        if (elapsedTime == 0) stop();
        double elapsedAverage = totalElapsedTime / runCount;

        return currentName + "Run Count : " + runCount
                + " , Total : " + totalElapsedTime / 1000000.0
                + " ms, Average : " + elapsedAverage / 1000000.0
                + " ms";
    }
}
