package com.free.platform.base.utils;

import java.util.UUID;

public class UUIDUtil {

	public static String getUUID_32(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

}
