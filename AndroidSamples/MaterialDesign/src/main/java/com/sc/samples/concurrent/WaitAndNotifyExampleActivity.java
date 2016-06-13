package com.sc.samples.concurrent;

import com.sc.samples.BaseActivity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 这里使用运动员起跑的例子来说明
 * wait()、notify()、notifyAll()是三个定义在Object类里的方法，可以用来控制线程的状态。
 * <p/>
 * 这三个方法最终调用的都是jvm级的native方法。随着jvm运行平台的不同可能有些许差异。
 * 如果对象调用了wait方法就会使持有该对象的线程把该对象的控制权交出去，然后处于等待状态。
 * 如果对象调用了notify方法就会通知某个正在等待这个对象的控制权的线程可以继续运行。
 * 如果对象调用了notifyAll方法就会通知所有等待这个对象控制权的线程继续运行。
 * <p/>
 * wait notify notifyAll 这几个方法必须在synchronized的代码块或方法中调用，否则会抛异常
 */
public class WaitAndNotifyExampleActivity extends BaseActivity {

    @Override
    protected void click() {
        test();
    }

    private void test() {
        Game game = new Game();
        for (int i = 0; i < 10; i++) {
            game.addPlayer(new Athlete(i, game));
        }
        new Thread(game).start();
    }

    /**
     * 运动员
     */
    private class Athlete implements Runnable {
        private final int id;
        private Game game;

        public Athlete(int id, Game game) {
            this.id = id;
            this.game = game;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Athlete)) return false;
            Athlete athlete = (Athlete) o;
            return this.id == athlete.id;
        }

        public int hashCode() {
            return new Integer(id).hashCode();
        }

        public String toString() {
            return "Athlete<" + id + ">";
        }

        @Override
        public void run() {
            try {
                game.prepare(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 运动
     */
    private class Game implements Runnable {

        private Set<Athlete> players = new HashSet<>();
        private boolean start = false;

        public void addPlayer(Athlete one) {
            players.add(one);
        }

        public void removePlayer(Athlete one) {
            players.remove(one);
        }

        public Collection<Athlete> getPlayers() {
            return Collections.unmodifiableSet(players);
        }

        public void prepare(Athlete athlete) throws InterruptedException {
            printlnToTextView(mResultTextView, athlete + " ready!");
            synchronized (this) {
                while (!start) wait();
                if (start) printlnToTextView(mResultTextView, athlete + " go!");
            }
        }

        public synchronized void go() {
            notifyAll();
        }

        public void ready() {
            Iterator<Athlete> iter = getPlayers().iterator();
            while (iter.hasNext())
                new Thread(iter.next()).start();
        }

        @Override
        public void run() {
            start = false;
            printlnToTextView(mResultTextView, "Ready...... 3");
            printlnToTextView(mResultTextView, "Ready...... 2");
            printlnToTextView(mResultTextView, "Ready...... 1");
            ready();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            start = true;
            printlnToTextView(mResultTextView, "Go!");
            go();
        }
    }
}
