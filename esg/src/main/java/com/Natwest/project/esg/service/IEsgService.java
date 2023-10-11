package com.Natwest.project.esg.service;

import com.Natwest.project.esg.entity.Esg;

import java.util.List;

public interface IEsgService {

    String saveEsg (Esg esg);
    List<Esg> getAllEsg();
//    List<Esg> getEsgByDataType(String dataType);


}
