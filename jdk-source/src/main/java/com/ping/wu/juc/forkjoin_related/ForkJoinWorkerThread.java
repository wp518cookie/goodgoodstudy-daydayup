//package com.ping.wu.juc.forkjoin_related;
//
//import com.ping.wu.util.UnsafeGenerator;
//import sun.misc.Unsafe;
//
//import java.security.AccessControlContext;
//import java.security.ProtectionDomain;
//
///**
// * @author wuping
// * @date 2020-03-31
// */
//
//public class ForkJoinWorkerThread extends Thread {
//    final ForkJoinPool pool;
//    final ForkJoinPool.WorkQueue workQueue;
//
//    protected ForkJoinWorkerThread(ForkJoinPool pool) {
//        super("aForkJoinWorkerThread");
//        this.pool = pool;
//        this.workQueue = pool.registerWorker(this);
//    }
//
//    ForkJoinWorkerThread(ForkJoinPool pool, ThreadGroup threadGroup, AccessControlContext acc) {
//        super(threadGroup, null, "aForkJoinWorkerThread");
//
//        this.pool = pool;
//
//    }
//
//    // ---------------------- field start----------------------
//    private static final sun.misc.Unsafe U;
//    private static final long THREADLOCALS;
//    private static final long INHERITABLETHREADLOCALS;
//    private static final long INHERITEDACCESSCONTROLCONTEXT;
//
//    static {
//        try {
//            U = UnsafeGenerator.getUnsafe();
//            Class<?> tk = Thread.class;
//            THREADLOCALS = U.objectFieldOffset(tk.getDeclaredField("threadLocals"));
//            INHERITABLETHREADLOCALS = U.objectFieldOffset(tk.getDeclaredField("inheritableThreadLocals"));
//            INHERITEDACCESSCONTROLCONTEXT = U.objectFieldOffset(tk.getDeclaredField("inheritedAccessControlContext"));
//        } catch (Exception e) {
//            throw new Error(e);
//        }
//    }
//
//    // ---------------------- field end----------------------
//
//    // ---------------------- method start ----------------------
//
//    void afterTopLevelExec() {
//
//    }
//
//    final void eraseThreadLocals() {
//        U.putObject(this, THREADLOCALS, null);
//        U.putObject(this, INHERITABLETHREADLOCALS, null);
//    }
//
//    public ForkJoinPool getPool() {
//        return pool;
//    }
//
//    public int getPoolIndex() {
//        return workQueue.getPoolIndex();
//    }
//
//    protected void onStart() {
//    }
//
//    protected void onTermination(Throwable exception) {
//    }
//
//    @Override
//    public void run() {
//        if (workQueue.array == null) {
//            Throwable exception = null;
//            try {
//                onStart();
//                pool.runWorker(workQueue);
//            } catch (Throwable ex) {
//                exception = ex;
//            } finally {
//                try {
//                    onTermination(exception);
//                } catch (Throwable ex) {
//                    if (exception == null) {
//                        exception = ex;
//                    }
//                } finally {
//                    pool.deregisterWorker(this, exception);
//                }
//            }
//        }
//    }
//
//    // ---------------------- method end ------------------------
//
//    // ---------------------- static class start----------------------
//    static final class InnocuousForkJoinWorkerThread extends ForkJoinWorkerThread {
//        private static final ThreadGroup innocuousThreadGroup = createThreadGroup();
//
//        private static final AccessControlContext INNOCUOUS_ACC =
//                new AccessControlContext(new ProtectionDomain[]{
//                        new ProtectionDomain(null, null)
//                });
//
//        InnocuousForkJoinWorkerThread(ForkJoinPool pool) {
//            super(pool, innocuousThreadGroup, INNOCUOUS_ACC);
//        }
//
//        @Override
//            // to erase ThreadLocals
//        void afterTopLevelExec() {
//            eraseThreadLocals();
//        }
//
//        @Override // to always report system loader
//        public ClassLoader getContextClassLoader() {
//            return ClassLoader.getSystemClassLoader();
//        }
//
//        @Override // to silently fail
//        public void setUncaughtExceptionHandler(UncaughtExceptionHandler x) {
//        }
//
//        @Override // paranoically
//        public void setContextClassLoader(ClassLoader cl) {
//            throw new SecurityException("setContextClassLoader");
//        }
//
//        private static ThreadGroup createThreadGroup() {
//            try {
//                Unsafe u = UnsafeGenerator.getUnsafe();
//                Class<?> tk = Thread.class;
//                Class<?> gk = ThreadGroup.class;
//                long tg = u.objectFieldOffset(tk.getDeclaredField("group"));
//                long gp = u.objectFieldOffset(gk.getDeclaredField("parent"));
//                ThreadGroup group = (ThreadGroup)
//                        u.getObject(Thread.currentThread(), tg);
//                while (group != null) {
//                    ThreadGroup parent = (ThreadGroup) u.getObject(group, gp);
//                    if (parent == null) {
//                        return new ThreadGroup(group, "InnocuousForkJoinWorkerThreadGroup");
//                    }
//                    group = parent;
//                }
//            } catch (Exception e) {
//                throw new Error(e);
//            }
//            throw new Error("Cannot create ThreadGroup");
//        }
//    }
//    // ---------------------- static class end----------------------
//}
