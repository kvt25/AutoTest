package com.autotest.driver;

import org.junit.Ignore;
import org.junit.runner.Description;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

/**
 * @author DatNguyen
 */
public class ExtendedRunner extends BlockJUnit4ClassRunner {

    /**
     * @param klass : This is
     * @throws InitializationError : InitializationError
     */
    public ExtendedRunner(final Class<?> klass) throws InitializationError {
        super(klass);
    }

    /**
     * In case the method is annotated with Repeat, do hierarchic description.
     * Otherwise, pass the handle to the super.
     * @param method : method
     * @return Description : Description
     */
    @Override
    protected final Description describeChild(final FrameworkMethod method) {
        if (method.getAnnotation(Repeat.class) != null && method.getAnnotation(Ignore.class) == null) {
            return describeRepeatTest(method);
        }
        return super.describeChild(method);
    }

    /**
     * @author DatNguyen
     * @param method : method
     * @return Description : Description
     */
    private Description describeRepeatTest(final FrameworkMethod method) {
        int times = method.getAnnotation(Repeat.class).value();
        Description description = Description.createSuiteDescription(testName(method) + " [" + times + " times]", method.getAnnotations());
        for (int i = 1; i <= times; i++) {
            description.addChild(Description.createTestDescription(getTestClass().getJavaClass(), "[" + i + "] " + testName(method)));
        }
        return description;
    }

    /**
     * In case the method is annotated with {@link Repeat} do specific handle.
     * Otherwise, pass the handle to the super.
     * @param method : method
     * @param notifier : notifier
     */
    @Override
    protected final void runChild(final FrameworkMethod method, final RunNotifier notifier) {
        Description description = describeChild(method);
        if (method.getAnnotation(Repeat.class) != null && method.getAnnotation(Ignore.class) == null) {
            runRepeatedly(methodBlock(method), description, notifier);
        }
        super.runChild(method, notifier);
    }

    /**
     * @author DatNguyen
     * @param statement : statement
     * @param description : description
     * @param notifier : notifier
     */
    private void runRepeatedly(final Statement statement, final Description description, final RunNotifier notifier) {
        for (Description desc : description.getChildren()) {
            runLeaf(statement, desc, notifier);
        }
    }
}
