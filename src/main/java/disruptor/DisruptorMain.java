package disruptor;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

/**
 * 测试类
 * @author wb-zyc501131
 *
 */
public class DisruptorMain {
	
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date);
		ExecutorService executorService = Executors.newCachedThreadPool();
		EventFactory<LongEvent> eventFactory = new LongEventFactory();
		int ringBufferSize = 1024 * 1024;
		@SuppressWarnings("deprecation")
		Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, ringBufferSize, executorService); 
		disruptor.handleEventsWith(new LongEventHandler());
		disruptor.start();
		RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
		LongEventProducer longEventProducer = new LongEventProducer(ringBuffer);
		ByteBuffer buffer = ByteBuffer.allocate(8);
		for (int i = 0; i < 100; i++) {
			buffer.putLong(0, i);
			longEventProducer.onDate(buffer);
		}
		disruptor.shutdown();
		executorService.shutdown();
	}
}
