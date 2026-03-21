package com.sysnormal.data.sysnormal_data_model.entities;

import com.sysnormal.data.basic_data_model.entities.BaseBasicEntity;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseSysnormalEntity<E extends BaseSysnormalEntity<E>> extends BaseBasicEntity<E> {

}
