package com.ryan.java.dp.command;
/**
 * 命令模式
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        Receiver receiver = new Receiver();
        Command command = new MyCommand(receiver);
        Invoker invoker = new Invoker(command);
        
        invoker.action();
	}

}
