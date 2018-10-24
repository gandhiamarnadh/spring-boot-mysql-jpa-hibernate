package netgloo.repository;

import netgloo.models.Account;

import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Account, String> {
}
