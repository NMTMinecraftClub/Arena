package com.SkyIsland.Arena.Team;

import java.util.Iterator;
import java.util.LinkedList;

import org.bukkit.Color;
import org.bukkit.entity.Player;

public class Team {

	private String teamName;
	private LinkedList<TeamPlayer> players;
	private String teamInfo;
	private Color teamColor;
	private int max;
	
	
	public Team(String teamName, Color color, int max){
		players = new LinkedList<TeamPlayer>();
		this.teamName = teamName;
		this.teamInfo = teamName;
		this.teamColor = color;
		this.max = max;
	}
	
	
	
	public Color getTeamColor() {
		return teamColor;
	}



	public void setTeamColor(Color teamColor) {
		this.teamColor = teamColor;
	}



	public String getName(){
		return teamName;
	}
	
	public String getInfo(){
		return teamInfo;
	}
	
	public int getNumberPlayers() {
		return this.players.size();
	}
	
	public int getLivePlayers() {
		int live;
		live = 0;
		for (TeamPlayer p : players) {
			if (p.isAlive()) {
				live+=1;
			}
		}
		return live;
	}
	
	public LinkedList<TeamPlayer> getPlayers(){
		return players;
	}
	
	public boolean isReady(){
		if (players.size() == 0){
			return false;
		}
		for (TeamPlayer p: players){
			if (! p.isReady()){
				return false;
			}
		}
		return true;
	}
	
	public boolean isAcknowledge() {

		if (players.size() == 0){
			return false;
		}
		for (TeamPlayer p: players){
			if (! p.isAcknowledge()){
				return false;
			}
		}
		return true;
	}
	
	public boolean contains(TeamPlayer p){
		return players.contains(p);
	}
	
	public boolean contains(Player p){
		for (TeamPlayer tp: players){
			if (tp.getPlayer().equals(p)){
				return true;
			}
		}
		return false;
	}
	
	public boolean addPlayer(Player p){
		return addPlayer(new TeamPlayer(p));
	}
	
	public boolean addPlayer(TeamPlayer p){
		if (this.getNumberPlayers() >= this.max) {
			//too many players
			p.getPlayer().sendMessage("This team is already full! (" + this.getNumberPlayers() + " / " + this.max + ")");
			return false;
		}
		players.add(p);
		return true;
	}
	
	public void removePlayer(Player p){
		Iterator<TeamPlayer> i = players.iterator();
		while (i.hasNext()){
			TeamPlayer tp = i.next();
			
			if (tp.getPlayer().equals(p)){
				i.remove();
			}
		}
	}
	
	public void removePlayer(TeamPlayer p){
		players.remove(p);
	}

	public void setReady(Player p) {
		for (TeamPlayer tp: players){
			if (tp.getPlayer().equals(p)){
				tp.setReady(true);
				return;
			}
		}
	}
	
	public void setAcknowledge(Player p) {
		for (TeamPlayer tp: players){
			if (tp.getPlayer().equals(p)){
				tp.setAcknowledge(true);
				return;
			}
		}
	}
	
	/**
	 * Sets the whole team as ready
	 */
	public void setTeamReady(boolean ready) {
		for (TeamPlayer p: players) {
			p.setReady(ready);
		}
	}
	
	public void setTeamAcknowledge(boolean acknowledge) {
		for (TeamPlayer p: players) {
			p.setAcknowledge(acknowledge);
		}
	}

	public void setDead(Player player) {
		for (TeamPlayer tp: players){
			if (tp.getPlayer().equals(player)){
				tp.setDead();
				return;
			}
		}
	}
	
	public void setAlive(Player player) {
		for (TeamPlayer tp: players){
			if (tp.getPlayer().equals(player)){
				tp.setAlive();
				return;
			}
		}
	}

	public boolean isAlive() {
		for (TeamPlayer tp: players){
			if (tp.isAlive()){
				return true;
			}
		}
		return false;
	}

	public void alertPlayers(String message) {
		for (TeamPlayer tp: players){
			tp.getPlayer().sendMessage(message);
		}
	}
	

	/**
	 * Asks the team if the given player is ready.
	 * @param player The player we are inquiring about
	 * @return true if the player is in the team and ready and false otherwise
	 */
	public boolean playerReady(Player player) {
		for (TeamPlayer p : players) {
			if (p.getPlayer().equals(player)) {
				//player is in the list
				return p.isReady();
			}
		}
		//player is not in the list
		return false;
	}
	
	/**
	 * Tries to get the TeamPlayer that matches the given player.
	 * @param player
	 * @return
	 */
	public TeamPlayer getTeamPlayer(Player player) {
		for (TeamPlayer p : players) {
			if (p.getPlayer().equals(player)) {
				return p;
			}
		}
		//none found
		return null;
	}

}
