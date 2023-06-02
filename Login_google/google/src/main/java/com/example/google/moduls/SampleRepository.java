package com.example.google.moduls;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends JpaRepository<Sample, Integer> {

	// 모든 회원을 조회
	@Override
	List<Sample> findAll();
	// 회원 ID를 가지고 회원정보 조회
	Optional<Sample> findByMemberId(int memberId);
	// 이메일을 이용해서 회원정보 조회
	Optional<Sample> findByEmail(String email);

}
