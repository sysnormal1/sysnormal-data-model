package com.sysnormal.data.sysnormal_data_model.repositories.apis;

import com.sysnormal.data.sysnormal_data_model.entities.apis.Api;
import com.sysnormal.data.sysnormal_data_model.repositories.BaseSysnormalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/** As APIs externas catalogadas. O nome é a chave de uso — ver `Api`. */
@Repository
public interface ApisRepository extends BaseSysnormalRepository<Api, Long> {

    @Query("select a from Api a where a.name = :name and a.deletedAt is null")
    Optional<Api> findByName(@Param("name") String name);
}
