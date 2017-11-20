package stack;

import collection.StackADT;

public class ArrayStack<T> implements StackADT<T> {

	private static final int DEFAULT_CAPACITY = 100;
	
	private int top;
	private T[] stack;
	
	public ArrayStack(int initialCapacity){
		if(initialCapacity <= 0)
			throw new IllegalArgumentException("Stack's capacity must be positive");

		top = -1;
		stack = (T[]) new Object[initialCapacity];
	}
	
	public ArrayStack(){
		stack = (T[]) new Object[DEFAULT_CAPACITY];
		top = -1;
	}
	
	@Override
	public void push(T element) {
		
		if(stack.length-1 == top)
			throw new IllegalStateException("Stack is full");
		if(element == null)
			throw new IllegalArgumentException("Null element not allowed");
		
		top++;
		stack[top] = element;
	}

	@Override
	public T pop() {

		if(top == -1)
			throw new IllegalStateException("Stack is empty");
		
		T element = stack[top];
		stack[top] = null;
		top--;
		
		return element;
	}

	@Override
	public T peek() {
		
		if(top == -1)
			throw new IllegalStateException("Stack is empty");
		
		return stack[top];
	}

	@Override
	public int indexOf(T element) {
		
		if(element != null){
			for(int i=0; i<stack.length; i++){
				if(stack[i] == element)
					return i;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty() {
		return (top == -1);
	}

	@Override
	public int size() {
		return top + 1;
	}
	
	private void expandCapacity(){
		T[] newStack = (T[]) new Object[stack.length*2];
		for(int i = 0; i<stack.length; i++){
			newStack[i] = stack[i];
		}
		stack = newStack;
	}
	
	public String toString(){
		
		String str = "{";
		
		for(int i=0; i<top; i++){
			str += stack[i] + ", ";
		}
		return str += stack[top] + "}";
	}
}
