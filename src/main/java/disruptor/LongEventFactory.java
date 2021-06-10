package disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 
 * @author wb-zyc501131
 *
 */
public class LongEventFactory implements EventFactory<LongEvent>{

	 public LongEvent newInstance() {
		 return new LongEvent();
	 }
}
