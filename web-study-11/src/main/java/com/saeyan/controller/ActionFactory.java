package com.saeyan.controller;

import com.saeyan.controller.action.Action;

public class ActionFactory {
	
	private static ActionFactory instance  = new ActionFactory();
	
	private ActionFactory() {}
	
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action  = null;
		
		System.out.println("ActionFactory : " + command);
		
		//조건문
		if(command.equals("board_list")) {
			action = new BoardList();
		}
		
		return action;
	}
}
