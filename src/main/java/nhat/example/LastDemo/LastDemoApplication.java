package nhat.example.LastDemo;

import nhat.example.LastDemo.entity.Client;
import nhat.example.LastDemo.entity.Todo;
import nhat.example.LastDemo.repository.ClientRepository;
import nhat.example.LastDemo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class LastDemoApplication implements CommandLineRunner {

	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) { SpringApplication.run(LastDemoApplication.class, args);}

	@Override
	public void run(String... args) throws Exception {
		Client client = new Client();

		client.setUsername("John");
		client.setPassword("Nhat123");


		Todo todo = new Todo();
		todo.setId(1L);
		todo.setContent("Upload video to YT");
		//System.out.println(user.getTodoList());
		client.getTodoList().add(todo);
		todoRepository.save(todo);
		clientRepository.save(client);
		System.out.println("Den day roi");
	}
}
