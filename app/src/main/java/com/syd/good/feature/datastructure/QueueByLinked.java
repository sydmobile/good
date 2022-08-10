package com.syd.good.feature.datastructure;

import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * <pre>
 *     @author Mobile
 *     e-mail : mobilesun516@gmail.com
 *     time   : 2021/12/3 14:03
 *     desc   : 链表队列
 *     version: 1.0
 * </pre>
 */
public class QueueByLinked<T> {

    static class Note<T> {
        T element;
        Note<T> next;

        public Note(T element) {
            this.element = element;
        }
    }

    Note<T> first;

    Note<T> last;

    int size = 0;

    public QueueByLinked() {
        Note note = new Note(null);
        this.first = this.last = note;
        size = 0;
    }

    public void offer(T v) {
        Note<T> note = new Note<>(v);
        this.last = this.last.next = note;
        size++;
    }

    public T poll() {
        if (size == 0) {
            return null;
        }
        size--;
        this.first = this.first.next;

        ArrayBlockingQueue queue = new ArrayBlockingQueue(122);
//        queue.poll();
        ArrayList list = new ArrayList();
        return this.first.element;
    }
    void test(){
        LinkedList linkedList = new LinkedList();
        linkedList.remove();

    }



}
