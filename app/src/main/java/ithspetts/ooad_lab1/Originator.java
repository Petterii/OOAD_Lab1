package ithspetts.ooad_lab1;

import android.support.v4.util.Consumer;

import java.util.ArrayList;
import java.util.List;

public class Originator<T> {

    private T state;
    private Consumer<T> action;
    private List<Memento> undoStack;
    private List<Originator.Memento> redoStack;

    public Originator(){
        undoStack = new ArrayList<>();
        redoStack = new ArrayList<>();
    }


    public void addUndoStack(T yourinput, Consumer<T> function){
        set(yourinput, function);
        undoStack.add(createMemento());
    }

    public void UndoInStack(Consumer<T> function){
        if (undoStack.size() > 1) {
            restore(undoStack.get(undoStack.size() - 2));
            redoStack.add(undoStack.get(undoStack.size() - 1));
            undoStack.remove(undoStack.get(undoStack.size() - 1));
        }

    }

    public void RedoInStack(Consumer<T> function){
        if (redoStack.size() >= 1) {
            restore(redoStack.get(redoStack.size() - 1));
            undoStack.add(redoStack.get(redoStack.size() - 1));
            redoStack.remove(redoStack.get(redoStack.size() - 1));
        }

    }

    public void set(T state, Consumer<T> action) {
        this.state = state;
        this.action = action;
    }

    public Memento createMemento(){
        return new Memento(state, action);
    }

    public void restore(Memento memento){
        memento.action.accept(memento.getState());
    }


    public class Memento {
       private T state;
        private Consumer<T> action;

       private Memento(T state, Consumer<T> action){
           this.action = action;
           this.state = state;
       }

        public T getState() {
            return state;
        }

        public void setState(T state) {
            this.state = state;
        }
    }

}
