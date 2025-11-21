package com.saeyan.contoller;

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
		
		if(command.equals("board_list")) {
			action = new ProductListAction();
		}else if(command.equals("product_write")) {
			action = new ProductWriteFormAction();
		}else if(command.equals("product_write_action")) {
			action = new ProductWriteAction();
		}
		
		return action;
	}
}
