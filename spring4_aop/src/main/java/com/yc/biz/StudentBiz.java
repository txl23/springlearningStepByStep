package com.yc.biz;

import com.yc.dao.StudentDao;
import org.springframework.stereotype.Service;

/**
 * @program: testspring
 * @description:
 * @author: 作者
 * @create: 2021-04-09 20:22
 */

public interface StudentBiz {
    int add(String name);

    void update(String name);

    void find(String name);


}
