package de.uniluebeck.itm.tr.snaa.shiro.entity;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "ACTIONS")
public class Action implements java.io.Serializable {

	@Id
	@Column(name = "NAME", unique = true, nullable = false, length = 30)
	private String name;

	@OneToMany(fetch = LAZY, mappedBy = "action", cascade = ALL)
	private Set<Permission> permissions;

	public Action() {

	}

	public Action(String name) {
		this.name = name;
	}

	public Action(String name, Set<Permission> permissions) {
		this.name = name;
		this.permissions = permissions;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Permission> getPermissions() {
		return this.permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		final Action action = (Action) o;
		return name.equals(action.name);
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}


