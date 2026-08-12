package com.sysnormal.data.sysnormal_data_model.repositories.commissions;

import com.sysnormal.data.sysnormal_data_model.entities.commissions.CommissionContract;
import com.sysnormal.data.sysnormal_data_model.repositories.BaseSysnormalRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionContractsRepository extends BaseSysnormalRepository<CommissionContract, Long> {
}
