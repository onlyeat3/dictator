package com.github.liuyuyu.dictator.server.web.model.request;

import lombok.Data;

/*
 * @author liuyuyu
 */
@Data
public class PageRequest {
    /**
     * 页码
     */
    private Long pageNum = 0L;
    /**
     * 每页分页条数
     */
    private Long pageSize = 20L;
}
