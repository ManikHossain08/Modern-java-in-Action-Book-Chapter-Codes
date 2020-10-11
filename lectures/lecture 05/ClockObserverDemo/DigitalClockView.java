// A specific Observer to observe ClockTimerModel: DigitalClockView
//

import java.util.Observable;
import java.util.Observer;

class DigitalClockView implements Observer { 
    public 
    void update(Observable obs, Object x) {
    	//redraw my clock’s reading after I was notified
    	int hour   = ((ClockTimerModel) obs).GetHour();
    	int minute = ((ClockTimerModel) obs).GetMinute();
    	int second = ((ClockTimerModel) obs).GetSecond(); 
    	System.out.println(hour+":"+minute+":"+second); 
    };
};

