package de.uniluebeck.itm.tr.snaa.shiro.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PermissionId implements java.io.Serializable {

	@Column(name = "ROLE_NAME", nullable = false, length = 150)
	private String roleName;

	@Column(name = "ACTION_NAME", nullable = false, length = 30)
	private String actionName;

	@Column(name = "RESOURCEGROUP_NAME", length = 40)
	private String resourcegroupName;

	public PermissionId() {

	}

	public PermissionId(String roleName, String actionName) {
		this.roleName = roleName;
		this.actionName = actionName;
	}

	public PermissionId(String roleName, String actionName, String resourcegroupName) {
		this.roleName = roleName;
		this.actionName = actionName;
		this.resourcegroupName = resourcegroupName;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getActionName() {
		return this.actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getResourcegroupName() {
		return this.resourcegroupName;
	}

	public void setResourcegroupName(String resourcegroupName) {
		this.resourcegroupName = resourcegroupName;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if ((other == null)) {
			return false;
		}
		if (!(other instanceof PermissionId)) {
			return false;
		}
		PermissionId castOther = (PermissionId) other;

		return ((this.getRoleName() == castOther.getRoleName()) || (this.getRoleName() != null && castOther
				.getRoleName() != null && this.getRoleName().equals(castOther.getRoleName())))
				&& ((this.getActionName() == castOther.getActionName()) || (this.getActionName() != null && castOther
				.getActionName() != null && this.getActionName().equals(castOther.getActionName())))
				&& ((this.getResourcegroupName() == castOther.getResourcegroupName()) || (this
				.getResourcegroupName() != null && castOther.getResourcegroupName() != null && this
				.getResourcegroupName().equals(castOther.getResourcegroupName())));
	}

	public int hashCode() {
		int result = 17;
		result = 37 * result + (getRoleName() == null ? 0 : this.getRoleName().hashCode());
		result = 37 * result + (getActionName() == null ? 0 : this.getActionName().hashCode());
		result = 37 * result + (getResourcegroupName() == null ? 0 : this.getResourcegroupName().hashCode());
		return result;
	}
}
