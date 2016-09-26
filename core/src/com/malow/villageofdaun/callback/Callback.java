package com.malow.villageofdaun.callback;

import java.util.ArrayDeque;
import java.util.Deque;

public abstract class Callback
{
	Deque<Message> msgs = new ArrayDeque<Message>();
	
	protected Message getNextMessage()
	{
		if(msgs.size() > 0)
			return msgs.pop();
		else
			return null;
	}
	
	public void addMessage(Message msg)
	{
		msgs.push(msg);
	}
}
