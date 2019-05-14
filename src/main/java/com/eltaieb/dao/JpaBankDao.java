package com.eltaieb.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eltaieb.entity.BankEntity;

@Repository
public interface JpaBankDao extends CrudRepository<BankEntity, Long> {

	@Query("select b from " + BankEntity.ENTITY_NAME + " b where upper(b.bankIdentifier) like upper( :#{#bankIdentifier}) ")
	Optional<BankEntity> findBankByIdentifier(@Param("bankIdentifier") String bankIdentifier);

}
