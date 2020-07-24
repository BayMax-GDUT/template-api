package com.cvaiedu.template.event;

import com.cvaiedu.template.business.system.entity.Log;
import org.springframework.context.ApplicationEvent;

/**
 * 日志事件
 *
 * @author tangyi
 * @date 2019/3/12 23:58
 */
public class LogEvent extends ApplicationEvent {
    public LogEvent(Log source) {
        super(source);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>log发送");
    }
}
