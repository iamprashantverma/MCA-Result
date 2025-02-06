package com.college.mca.result.repositories;

import com.college.mca.result.entities.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepo extends JpaRepository<ResultEntity,Long> {
}
