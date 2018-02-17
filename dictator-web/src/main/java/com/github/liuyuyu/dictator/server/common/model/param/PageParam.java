package com.github.liuyuyu.dictator.server.common.model.param;

import com.github.pagehelper.PageHelper;
import lombok.Data;

/*
 * @author liuyuyu
 */
@Data
public class PageParam {
    /**
     * 页码
     */
    private Integer pageNum = 0;
    /**
     * 每页分页条数
     */
    private Integer pageSize = 20;

    public void startPage(){
        PageHelper.startPage(this.pageNum,this.pageSize);
    }
}
