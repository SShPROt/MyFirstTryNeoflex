package com.example.SecondTry.repos;

import com.example.SecondTry.models.Holidays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface HolidaysRepository extends JpaRepository<Holidays, Short> {
}
