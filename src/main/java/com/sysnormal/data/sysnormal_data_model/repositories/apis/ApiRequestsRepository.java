package com.sysnormal.data.sysnormal_data_model.repositories.apis;

import com.sysnormal.data.sysnormal_data_model.entities.apis.ApiRequest;
import com.sysnormal.data.sysnormal_data_model.repositories.BaseSysnormalRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * As requisições configuradas de uma API — "Consulta CNPJ", "Exporta lista".
 *
 * <p>A busca é por API e nome porque é assim que o serviço a encontra sem
 * gravar id de cadastro dentro do código.</p>
 */
@Repository
public interface ApiRequestsRepository extends BaseSysnormalRepository<ApiRequest, Long> {

    @Query("""
            select r from ApiRequest r
            where r.apiId = :apiId and r.name = :name and r.deletedAt is null
            """)
    Optional<ApiRequest> findByApiAndName(@Param("apiId") Long apiId, @Param("name") String name);
}
