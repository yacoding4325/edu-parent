package com.javaclimb.service.statistics.task;

/**
 * @Author yaCoding
 * @create 2022-11-06 下午 11:35
 */

/**
 * # 添加定时任务
 *
 * ## 1、创建定时任务类，使用cron表达式
 */

import com.javaclimb.service.statistics.service.DailyService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务，主要用于每日生成统计数据
 */

@Component
@Slf4j
public class ScheduledTask {

    @Autowired
    private DailyService dailyService;

    /**
     * 每天凌晨1点定时执行一次，生成前一天的数据
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void genStatisticsDate(){
        log.info("自动生成数据");
        String day = new DateTime().minusDays(1).toString("yyyy-MM-dd");
        dailyService.createStatisticsByDay(day);
    }

}
