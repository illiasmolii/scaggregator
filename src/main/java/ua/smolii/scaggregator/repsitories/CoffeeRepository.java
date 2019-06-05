package ua.smolii.scaggregator.repsitories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import ua.smolii.scaggregator.domain.Coffee;

@Repository
public interface CoffeeRepository extends PagingAndSortingRepository<Coffee, Long> {
}
