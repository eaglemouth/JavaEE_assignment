package edu.whu.demo.aspect;

import lombok.Data;

@Data
public class ApiStatistics {
    private Long callCount = 0L;
    private Long errorCount = 0L;
    private Long totalResponseTime = 0L;
    private Long minResponseTime = Long.MAX_VALUE;
    private Long maxResponseTime = Long.MIN_VALUE;
}
