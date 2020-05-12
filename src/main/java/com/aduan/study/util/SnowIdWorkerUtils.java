package com.aduan.study.util;

/**
 * 雪花算法ID生成
 *
 * @author dj
 * @since 2020-05-12 17:57
 */
public class SnowIdWorkerUtils {
    // 开始时间截 (2020-01-02)
    private final long timeToCut = 1577894400000L;
    // 机器ID所占的位数
    private final long workerIdBits = 2L;
    // 数据标识ID所占的位数
    private final long dataCenterIdBits = 8L;
    // 支持的最大机器ID，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);
    // 支持的最大数据标识ID，结果是31
    private final long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
    // 序列在ID中占的位数
    private final long sequenceBits = 12L;
    // 机器ID向左移12位
    private final long workerIdShift = sequenceBits;
    // 数据标识ID向左移17位(12+5)
    private final long dataCenterIdShift = sequenceBits + workerIdBits;
    // 时间截向左移22位(5+5+12)
    private final long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    // 生成序列的掩码
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);
    // 工作机器ID(0~31)
    private long workerId;
    // 数据中心ID(0~31)
    private long dataCenterId;
    // 毫秒内序列(0~4095)
    private long sequence = 0L;
    // 上次生成ID的时间截
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     *
     * @param workerId     工作ID (0~31)
     * @param dataCenterId 数据中心ID (0~31)
     */
    public SnowIdWorkerUtils(long workerId, long dataCenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException("workerId 不符合条件");
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException("dataCenterId 不符合条件");
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    public synchronized String nextIdVar() {
        return String.valueOf(nextId());
    }

    /**
     * 线程安全，获得下一个ID
     */
    private synchronized long nextId() {
        long timestamp = timeGen();
        // 如果当前时间小于上一次ID生成的时间戳，抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format(
                    "时间顺序异常,时间差(上次时间-现在)=%d",
                    lastTimestamp - timestamp));
        }
        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            // 时间戳改变，毫秒内序列重置
            sequence = 0L;
        }
        // 上次生成ID的时间截
        lastTimestamp = timestamp;
        // 移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - timeToCut) << timestampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift) | sequence;
    }

    /**
     * 阻塞，获得新的时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回当前时间节点
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        // 参数在实际业务下需要配置管理
        SnowIdWorkerUtils idWorker = new SnowIdWorkerUtils(1, 1);
        for (int i = 0; i < 100; i++) {
            String id = idWorker.nextIdVar();
            System.out.println(id + "  " + id.length() + "位");
        }
    }
}
