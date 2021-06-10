package disruptor;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;

import lombok.AllArgsConstructor;
@AllArgsConstructor
public class LongEventProducer {
	public final RingBuffer<LongEvent> ringBuffer;
	
	public void onDate(ByteBuffer byteBuffer) {
		// 1.获取ringBuffer的下标位置
		long sequence = ringBuffer.next();
		Long data = null;
		
		// 2.去除ringBuffer中的空位置
		LongEvent longEvent = ringBuffer.get(sequence);
		
		// 3.然后赋值
		data = byteBuffer.getLong(0);
		longEvent.setValue(data);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("生产者准备发送数据");
			
			// 4.发送数据
			ringBuffer.publish(sequence);
		}
	}
}
