package com.ailk.eaap.op2.dao;

import java.util.List;

import com.ailk.eaap.op2.bo.ObjectInfoLog;

public interface ObjectIntfLogDAO {

    public void insertObjectInftLog(ObjectInfoLog objectInfoLog);
    public void updateObjectInftLog(ObjectInfoLog objectInfoLog);
    public List<ObjectInfoLog> qryselectObjectInftLog(ObjectInfoLog objectInfoLog);
}