package com.asiainfo.integration.o2p.international.bmo;

import java.util.Map;


public interface IResultObjHandler<T> {
    public T handler(T srcObj,Map<String,String> localeValue);
}
