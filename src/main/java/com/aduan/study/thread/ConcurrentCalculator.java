package com.aduan.study.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 多线程并行计算求和
 */
public class ConcurrentCalculator {

    /**
     * 任务执行器（线程池）
     */
    private ExecutorService exec;
    /**
     * cpu 核心数
     */
    private int cpuCoreNumber;
    // 并发执行返回结果列表
    private List<Future<Long>> tasks = new ArrayList<>();

    /**
     * 内部类 == 求和计算器
     */
    class SumCalculatorCallable implements Callable<Long> {
        // 待求和的数组
        private int[] numbers;
        private int start;
        private int end;

        public SumCalculatorCallable(final int[] numbers, int start, int end) {
            this.numbers = numbers;
            this.start = start;
            this.end = end;
        }

        @Override
        public Long call() throws Exception {
            Long sum = 0L;
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
            return sum;
        }
    }

    public ConcurrentCalculator() {
        //获取CPU数量
        cpuCoreNumber = Runtime.getRuntime().availableProcessors();
        // 新建一个固定大小为核心CPU数的线程池
        exec = Executors.newFixedThreadPool(cpuCoreNumber);
    }

    public Long sum(final int[] numbers) {
        // 根据CPU核心个数拆分任务，创建FutureTask并提交到Executor
        for (int i = 0; i < cpuCoreNumber; i++) {
            int increment = numbers.length / cpuCoreNumber + 1;
            int start = increment * i;
            int end = increment * i + increment;
            if (end > numbers.length) {
                end = numbers.length;
            }
            SumCalculatorCallable subCalc = new SumCalculatorCallable(numbers, start, end);
            FutureTask<Long> task = new FutureTask<>(subCalc);
            tasks.add(task);
            if (!exec.isShutdown()) {
                exec.submit(task);
            }
        }
        return getResult();
    }

    /**
     * 迭代每个只任务，获得部分和，相加返回
     *
     * @return
     */
    public Long getResult() {
        Long result = 0L;
        for (Future<Long> task : tasks) {
            try {
                // 如果计算未完成则阻塞
                Long subSum = task.get();
                result += subSum;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void close() {
        exec.shutdown();
    }

    public static void main(String[] args) {
        int[] numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 10, 11};
        ConcurrentCalculator calc = new ConcurrentCalculator();
        Long sum = calc.sum(numbers);
        System.out.println(sum);
        calc.close();
    }
}
