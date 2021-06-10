package listener;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Even<E extends Robot> {
	private E e;

	public Even(E e){
		this.e = e;
	}

}
