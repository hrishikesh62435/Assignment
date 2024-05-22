package com.assignment.model;


import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class ActiveLinkMenu {

	private String menuOpen;	
	private String dashboardLink;
	private String dashboardSublink;
	private String dashboard2Sublink;
	
	private String userLink;

	private String ListLink;

	public String getMenuOpen() {
		return menuOpen;
	}

	public void setMenuOpen(String menuOpen) {
		this.menuOpen = menuOpen;
	}

	public String getDashboardLink() {
		return dashboardLink;
	}

	public void setDashboardLink(String dashboardLink) {
		this.dashboardLink = dashboardLink;
	}

	public String getDashboardSublink() {
		return dashboardSublink;
	}

	public void setDashboardSublink(String dashboardSublink) {
		this.dashboardSublink = dashboardSublink;
	}

	public String getDashboard2Sublink() {
		return dashboard2Sublink;
	}

	public void setDashboard2Sublink(String dashboard2Sublink) {
		this.dashboard2Sublink = dashboard2Sublink;
	}

	public String getUserLink() {
		return userLink;
	}

	public void setUserLink(String userLink) {
		this.userLink = userLink;
	}

	public String getListLink() {
		return ListLink;
	}

	public void setListLink(String listLink) {
		ListLink = listLink;
	}
	
	
}
