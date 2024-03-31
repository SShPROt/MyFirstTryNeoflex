package com.example.SecondTry.repos;

import com.example.SecondTry.models.DaysInMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface DaysInMonthRepository extends JpaRepository<DaysInMonth, Long> {
}
