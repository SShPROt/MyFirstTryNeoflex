package com.example.SecondTry.repos;

import com.example.SecondTry.models.DaysInYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DaysInYearRepository extends JpaRepository<DaysInYear, Long> {
}
