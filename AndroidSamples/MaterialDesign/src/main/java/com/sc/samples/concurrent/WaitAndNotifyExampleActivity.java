package com.sc.samples.concurrent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sc.samples.BaseActivity;
import com.sc.samples.R;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 这里使用运动员起跑的例子来说明
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
