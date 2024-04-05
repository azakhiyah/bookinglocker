package demo.bookinglocker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.bookinglocker.model.History;

public interface HistoryRepository extends JpaRepository<History, Integer>{
    
}
