package telran.multithreading.consumer;

import java.util.concurrent.atomic.AtomicInteger;
import telran.multithreading.MessageBox;

public class Receiver extends Thread {
	private MessageBox messageBox;
	private static AtomicInteger messageCounter = new AtomicInteger(0);
	public Receiver(MessageBox messageBox) {
		this.messageBox = messageBox;
	}
	@Override
	public void run() {
		while(true) {
			try {
				String message = messageBox.get();
				System.out.println(message + getName());
				messageCounter.incrementAndGet();
			} catch (InterruptedException e) {
				if (messageBox.take() == null) {
					break;
					
				} else {
					System.out.printf(messageBox.take()+ getName());
					messageCounter.incrementAndGet();
				}
			}
		}
	}
	public static int getMessagesCounter() {
		return messageCounter.get();
	}
	
}
