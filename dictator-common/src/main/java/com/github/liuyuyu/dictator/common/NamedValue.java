package com.github.liuyuyu.dictator.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.github.liuyuyu.dictator.common.exception.IgnoredFunctionException;
import com.github.liuyuyu.dictator.common.jackson.NamedValueSerializer;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author liuyuyu
 */
@JsonSerialize(using = NamedValueSerializer.class)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NamedValue<K,V> implements Map<K,V> {
    private K k;
    private V v;

    public static Map<String, String> from(String... pairs) {
        Map<String,String> map = new HashMap<>();
        if (pairs.length % 2 == 0) {
            for (int i = 0; i < pairs.length; i += 2) {
                String name = pairs[i];
                String value = pairs[i + 1];
                NamedValue<String,String> namedValue = new NamedValue<>();
                namedValue.k = name;
                namedValue.v = value;
                map.putAll(namedValue);
            }
        } else {
            throw new IllegalArgumentException("参数数量不能是奇数");
        }
        return map;
    }

    public static NamedValue<String, String> of(String... pairs){
        if (pairs.length == 2) {
                String name = pairs[0];
                String value = pairs[1];
                NamedValue<String,String> namedValue = new NamedValue<>();
                namedValue.k = name;
                namedValue.v = value;
                return namedValue;
        } else {
            throw new IllegalArgumentException("参数数量不能是奇数");
        }
    }

    @Override
    public int size() {
        return k != null ? 1:0;
    }

    @Override
    public boolean isEmpty() {
        return k != null && v != null;
    }

    @Override
    public boolean containsKey(Object key) {
        return this.k.equals(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return this.v.equals(value);
    }

    @Override
    public V get(Object key) {
        return this.v;
    }

    @Override
    public V put(K key, V value) {
        throw IgnoredFunctionException.of("put");
    }

    @Override
    public V remove(Object key) {
        throw IgnoredFunctionException.of("remove");
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        throw IgnoredFunctionException.of("putAll");
    }

    @Override
    public void clear() {
        throw IgnoredFunctionException.of("clear");
    }

    @Override
    public Set<K> keySet() {
        throw IgnoredFunctionException.of("keySet");
    }

    @Override
    public Collection<V> values() {
        throw IgnoredFunctionException.of("values");
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw IgnoredFunctionException.of("entrySet");
    }

    public V get(){
        return this.v;
    }

    public K getKey(){
        return this.k;
    }
}
