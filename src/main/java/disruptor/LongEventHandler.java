package disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * 消费者，也就是事件处理器
 * @author wb-zyc501131
 *
 */
public class LongEventHandler implements EventHandler<LongEvent>{
	
	@Override
	public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
		System.out.println("消费者：" + event.getValue());
	}
}
