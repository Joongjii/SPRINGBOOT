package com.mc.boot.rent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mc.boot.rent.Rent;

public interface RentRepository extends JpaRepository<Rent, Long>, RentRepositoryExtension{
	
	@EntityGraph(value = "Rent.rentBooks", type = EntityGraphType.FETCH)
	List<Rent> findByTitleStartsWithOrMemberUserIdEquals(String title, String userId);
	
	
	
}
