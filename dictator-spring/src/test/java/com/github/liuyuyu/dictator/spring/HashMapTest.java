package com.github.liuyuyu.dictator.spring;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/*
 * @author liuyuyu
 */
public class HashMapTest {

    @Test
    public void testPutAll(){
        Map<String, String> mapA = new HashMap<>();
        mapA.put("a","1");
        Map<String, String> mapB = new HashMap<>();
        mapB.put("a","2");
        mapB.put("b","3");
        mapA.putAll(mapB);
        for (Map.Entry<String, String> codeMap : mapA.entrySet()) {
            System.out.println(codeMap.getKey()+":"+codeMap.getValue());
        }
    }
}
