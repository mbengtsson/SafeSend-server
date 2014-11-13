package se.teamgejm.safesendserver.rest.model;

import java.util.List;

/**
 * Created by Marcus Bengtsson on 2014-11-13.
 */
public class GetAllUsersResponse {

	private List<UserItem> users;

	public GetAllUsersResponse(List<UserItem> users) {
		this.users = users;
	}

	public List<UserItem> getUsers() {
		return users;
	}

	public void setUsers(List<UserItem> users) {
		this.users = users;
	}

	public static class UserItem {

		private long id;

		private String username;

		public UserItem(long id, String username) {
			this.id = id;
			this.username = username;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
	}
}
