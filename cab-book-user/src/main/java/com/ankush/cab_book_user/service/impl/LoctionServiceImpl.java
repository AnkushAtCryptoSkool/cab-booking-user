package com.ankush.cab_book_user.service.impl;

import com.ankush.cab_book_user.constants.AppConstants;
import com.ankush.cab_book_user.service.CabLocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CabLoctionServiceImpl implements CabLocationService {

    @Autowired
    KafkaTemplate kafkaTemplate;
    @Override
    public Boolean updateLocation(String location) {
        try {
            kafkaTemplate.send(AppConstants.CAB_LOCATION, location);
            return true;
        }catch (Exception ex){
          log.error("Exception occuered while publishing data to kafka topic : {} , : {}",AppConstants.CAB_LOCATION,ex);
        }
        return false;
    }
}
