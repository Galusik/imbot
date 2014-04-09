package org.hillel.it.qsm.model.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User extends BaseEntity {
	private String email;
	private String password;
	private Map<Integer, Message> messages;

	private Map<Integer, Message> trash;

	public User(String email, String password) {
		this.email = email;
		this.password = password;
		messages = new HashMap();

	}

	public Map<Integer, Message> getTrash() {
		return trash;
	}

	public String getPassword() {
		return password;
	}

	public Map<Integer, Message> getMessages() {
		return messages;

	}

}
