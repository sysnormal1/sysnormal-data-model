package com.sysnormal.data.sysnormal_data_model.repositories;

import com.sysnormal.data.basic_data_model.repositories.BaseBasicRepository;
import com.sysnormal.data.sysnormal_data_model.entities.BaseSysnormalEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseSysnormalRepository<E extends BaseSysnormalEntity<E>, ID> extends BaseBasicRepository<E, ID> {

}
