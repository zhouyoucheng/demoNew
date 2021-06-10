package designModule;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Data;

@Data
public class Chain {
    List<ChainHandler> handlers;
    private int index = 0;

    public void process() {
        if (index < handlers.size()) {
            handlers.get(index++).process(this);
        }
    }

    public static void main(String[] args) {
        List<ChainHandler> handlers = Lists.newArrayList(new ChainHandlerA(), new ChainHandlerB(),
                new ChainHandlerA(), new ChainHandlerB(), new ChainHandlerA(), new ChainHandlerB());
        Chain chain = new Chain();
        chain.setHandlers(handlers);
        chain.process();
    }
}

abstract class ChainHandler {
    public void process(Chain chain) {
        execute();
        chain.process();
    }

    public abstract void execute();
}

class ChainHandlerA extends ChainHandler {
    @Override
    public void execute() {
        System.out.println("i am A");
    }
}

class ChainHandlerB extends ChainHandler {
    @Override
    public void execute() {
        System.out.println("i am B");
    }
}
