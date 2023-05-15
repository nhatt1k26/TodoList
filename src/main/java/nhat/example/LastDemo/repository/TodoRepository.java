package nhat.example.LastDemo.repository;

import nhat.example.LastDemo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo,Long> {
}
