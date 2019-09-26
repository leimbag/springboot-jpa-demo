package com.leimbag.jpa.demo.enums;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EnableDisableStatus {
    ALL(-1, "全部"),
    DISABLE(0, "禁用"),
    ENABLE(1, "启用");

    private static Logger logger = LoggerFactory.getLogger(EnableDisableStatus.class);

    private static final Object _LOCK = new Object();

    private static Map<Integer, EnableDisableStatus> _MAP;
    private static List<EnableDisableStatus> _LIST;
    private static List<EnableDisableStatus> _ALL_LIST;

    static {
        synchronized (_LOCK) {
            Map<Integer, EnableDisableStatus> map = new HashMap<>();
            List<EnableDisableStatus> list = new ArrayList<>();
            List<EnableDisableStatus> listAll = new ArrayList<>();
            for (EnableDisableStatus enableDisableStatus : EnableDisableStatus.values()) {
                map.put(enableDisableStatus.getValue(), enableDisableStatus);
                listAll.add(enableDisableStatus);
                if (!enableDisableStatus.equals(ALL)) {
                    list.add(enableDisableStatus);
                }
            }

            _MAP = ImmutableMap.copyOf(map);
            _LIST = ImmutableList.copyOf(list);
            _ALL_LIST = ImmutableList.copyOf(listAll);
        }
    }

    private int value;
    private String name;

    EnableDisableStatus(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public static EnableDisableStatus get(int value) {
        try {
            return _MAP.get(value);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static List<EnableDisableStatus> list() {
        return _LIST;
    }

    public static List<EnableDisableStatus> listAll() {
        return _ALL_LIST;
    }
}
