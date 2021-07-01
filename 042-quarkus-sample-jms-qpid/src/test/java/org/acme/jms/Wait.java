package org.acme.jms;

import java.util.concurrent.TimeUnit;

public class Wait {

    public interface Condition {
        boolean isSatisfied() throws Exception;
    }

    public static boolean waitFor(final Condition condition, final long duration, final long sleepMillis) throws Exception {
        final long expiry = System.currentTimeMillis() + duration;
        boolean conditionSatisfied = condition.isSatisfied();
        while (!conditionSatisfied && System.currentTimeMillis() < expiry) {
            TimeUnit.MILLISECONDS.sleep(sleepMillis);
            conditionSatisfied = condition.isSatisfied();
        }
        return conditionSatisfied;
    }
}
