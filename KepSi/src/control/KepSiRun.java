 package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.xml.stream.events.StartDocument;

import model.KepSiModel;
import model.KepsiModelListener;
import physic.KepSiKeplerObject;
import physic.KepSiVector;
import view.KepSiFrame;

public class KepSiRun implements KepsiModelListener, Runnable{
	private KepSiFrame frame;
	private Thread runThread;
	
	
	public KepSiRun(KepSiFrame frame) {
		this.frame = frame;
		runThread = new Thread(this);
		frame.getModel().addListener(this);
		
	}

	@Override
	public void modelChanged() {
		// TODO Auto-generated method stub
		
		if(frame.getModel().isSimulate()&&!runThread.isAlive()) {
			runThread = new Thread(this);
			runThread.start();
			
		}
	}

	@Override
	public void run() {
		int index = 0;
		double timeStep = 0;
		int timeZoom = 500;
		// TODO Auto-generated method stub
		double startTime = 0;
		double endTime = 0;
		double diff = 0;
		while(frame.getModel().isSimulate()) {
			startTime = System.nanoTime();
			
			
			
			for(int i = 0; i<100;i++) {
				frame.getModel().setTime(frame.getModel().getTime()+timeStep/100);
				
				
				
				for(KepSiKeplerObject keplerObject : frame.getModel().getKeplerObjects()) {
					keplerObject.update(frame.getModel().getTime());
					frame.getModel().getSat().gravity(timeStep/100, keplerObject);
			
			}
				frame.getModel().getSat().applyForce(timeStep/100);
				
			}
			if(index%50 == 0) {
				frame.getModel().getTrail().addPosition(frame.getModel().getSat().getPosition(), frame.getModel().getSat().getParent());
				// frame.getSimPanel().repaint();
			}

			index++;
			frame.getModel().getSat().setParent();
			frame.getModel().update();
			endTime = System.nanoTime();
			diff = endTime-startTime;
			
			timeStep = diff/1000000000;
			timeStep *= frame.getModel().getTimeFactor();
			frame.requestFocus();
			
		}
		
	}
	
	

	}
