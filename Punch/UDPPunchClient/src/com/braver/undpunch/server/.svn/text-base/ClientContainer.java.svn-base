/**
 * @(#) ClientContainer.java Created on 2013-10-12
 *
 *  Copyright (c) 2012 Braver. All Rights Reserved
 */
package com.braver.undpunch.server;

import java.util.LinkedList;
import java.util.List;

import com.braver.undpunch.common.net.PunchSocket;

/**
 * The class <code>ClientContainer</code>
 * 
 * @author Feng OuYang
 * @version 1.0
 */
public class ClientContainer {

	private LinkedList<ContainerListener> listeners = new LinkedList<ClientContainer.ContainerListener>();

	protected LinkedList<PunchSocket> tasks = new LinkedList<PunchSocket>();

	public synchronized void addTask(PunchSocket task) {
		this.tasks.add(task);
		for (ContainerListener listener : listeners) {
			listener.onAdd(task);
		}
	}

	public synchronized void addToFirstTask(PunchSocket task) {
		this.tasks.addFirst(task);
		for (ContainerListener listener : listeners) {
			listener.onAdd(task);
		}
	}

	public synchronized void removeTask(PunchSocket task) {
		this.tasks.remove(task);
		for (ContainerListener listener : listeners) {
			listener.onRemove(task);
		}
	}

	public synchronized List<PunchSocket> getAllTask() {
		return this.tasks;
	}

	public synchronized void clear() {
		tasks.clear();
		for (ContainerListener listener : listeners) {
			listener.onClear();
		}
	}

	public synchronized void addListener(ContainerListener listener) {
		this.listeners.add(listener);
	}

	public synchronized void removeListener(ContainerListener listener) {
		this.listeners.remove(listener);
	}

	public interface ContainerListener {

		void onAdd(PunchSocket punchSocket);

		void onRemove(PunchSocket punchSocket);

		void onClear();

	}

}
