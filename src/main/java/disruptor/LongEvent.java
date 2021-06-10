package disruptor;

import lombok.Data;

/**
 * 生产者与消费者传递的数据
 * @author wb-zyc501131
 *
 */
@Data
public class LongEvent {
	
	private Long value;
		
}
