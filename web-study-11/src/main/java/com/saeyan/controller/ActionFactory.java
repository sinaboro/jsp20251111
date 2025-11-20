package com.saeyan.controller;

import com.saeyan.controller.action.Action;
import com.saeyan.controller.action.BoardListAction;
import com.saeyan.controller.action.BoardWriteFormAction;

public class ActionFactory {
	
	private static ActionFactory instance  = new ActionFactory();
	
	private ActionFactory() {}
	
	public static ActionFactory getInstance() {
		return instance;
	}
	                           //board_list
	public Action getAction(String command) {
		
		Action action  = null;
		
		System.out.println("ActionFactory : " + command);
		
		//조건문
		if(command.equals("board_list")) {
			action =  new BoardListAction();
		}else if(command.equals("board_write_form")) {
			action = new BoardWriteFormAction();
		}
		
		return action;
	}
}
