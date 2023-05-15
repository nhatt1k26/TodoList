package nhat.example.LastDemo.repository;

import nhat.example.LastDemo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
