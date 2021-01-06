package com.redrabbit.cloud.core.log;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

public class P6SpyLogger implements MessageFormattingStrategy {

    /**
     * @Desc: 重写日志格式方法
     * now:当前时间
     * elapsed:执行耗时
     * category：执行分组
     * prepared：预编译sql语句
     * sql:执行的真实SQL语句，已替换占位
     */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? createRes(now, elapsed, sql) : "";
    }

    private String createRes(String now, long elapsed, String sql) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Consume Time: " + now);
        buffer.append("   " + elapsed + "ms");
        buffer.append("\nExecute SQL: ");
        buffer.append(sql.replaceAll("\n", ""));
        return buffer.toString();
    }
}