package butterknife.internal;

import android.view.View;

/**
 * A {@linkplain View.OnClickListener click listener} that debounces multiple clicks posted in the
 * same frame. A click on one button disables all buttons for that frame.
 */
public abstract class DebouncingOnClickListener implements View.OnClickListener {
    static boolean enabled = true;

    private static final Runnable ENABLE_AGAIN = new Runnable() {
        @Override
        public void run() {
            enabled = true;
        }
    };


    /**
     * Runnable 并不一定是新开一个线程，比如下面的调用方法是运行在UI线程中的
     *
     * Handler mHanlder = new Handler();
     * mHandler.post(new Runnable(){
     *     @Override
     *     public void run(){
     *
     *     }
     * })
     *我们可以通过调用 handle 的 post 方法，把 Runnable
     * 对象（一般是Runnable 的子类）传过去；
     * handler 会在 looper 中调用这个 Runnable 的 Run 方法执行
     *
     * Runnable 是一个接口，不是一个线程，一般线程会实现 Runnable。所以如果我们使用匿名
     * 内部类是运行在 UI 线程的，如果我们使用实现这个 Runnable 接口的线程类，则是运行在对应
     * 线程的
     *
     *
     * View.post（Runnable）方法的原理。在 post（Runnable）方法里，View获取当前线程（即UI线程）的
     * Handler，然后将 action对象 post 到 Handler里。在Handler 里，它将传递过来的 action对象包装
     * 成一个 Message（Message 的 callback 为 action），然后将其投入 UI 线程的消息循环中。
     * 在 Handle 再次处理该 Message 时，有一条分支（为解释的那条）就是它所设，直接调用 runnable 的
     * run 方法。而此时，已经路由到 UI 线程里，因此，我们可以毫无顾忌的来更新 UI
     * @param v
     */
    @Override
    public final void onClick(View v) {
        if (enabled) {
            enabled = false;
            v.post(ENABLE_AGAIN);
            doClick(v);
        }
    }

    public abstract void doClick(View v);
}
