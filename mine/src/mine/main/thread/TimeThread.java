package mine.main.thread;

import mine.main.panel.status.StatusPanel;

public class TimeThread extends Thread{
	private StatusPanel statusPanel;
	
	public TimeThread(StatusPanel statusPanel) {
		this.statusPanel=statusPanel;
		start();
	}
	
	@Override
	public void run() {
		super.run();
		while(true) {
			try {
				Thread.sleep(1000L);
				statusPanel.setTime();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
