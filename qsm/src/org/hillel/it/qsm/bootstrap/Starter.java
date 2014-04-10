package org.hillel.it.qsm.bootstrap;

import org.hillel.it.qsm.persistance.memory.UsersRepository;

import org.hillel.it.qsm.persistance.repository.UserRepository;
import org.hillel.it.qsm.service.MailService;
import org.hillel.it.qsm.service.impl.MailServiceImpl;

public class Starter {
	public static void main(String[] args) {
		UserRepository userRepository = new UsersRepository();

		MailService service = new MailServiceImpl("example@qsm.com", "123xxx",
				userRepository);
		MailService service1 = new MailServiceImpl("example1@qsm.com",
				"123xxx", userRepository);
		// �������� ������
		service1.sendMessage("123", "example@qsm.com", "Hello!!");
		service.sendMessage("hello world", "example1@qsm.com", "Hello!!");

		service.getInbox();
		service.getOutbox();
		service.getTrash();
		service.deleteMessage(4);
		service.getTrash();
		service.clearTrash();
		service.getTrash();
	

	}
}
