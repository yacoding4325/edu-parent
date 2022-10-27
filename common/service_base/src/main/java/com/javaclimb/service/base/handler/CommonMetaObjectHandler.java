package com.javaclimb.service.base.handler;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * 自动填充类
 */
@Component
public class CommonMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "gmtCreate", () -> new Date(), Date.class); // 起始版本 3.3.3(推荐)
        this.strictInsertFill(metaObject, "gmtModified", () -> new Date(), Date.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "gmtModified", () -> new Date(), Date.class); // 起始版本 3.3.3(推荐)
    }
}
