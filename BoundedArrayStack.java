package stack;

import collection.StackADT;

public class BoundedArrayStack<T> implements StackADT<T> {

	private int top;
	private T[] stack;
	
	public BoundedArrayStack(int capacity){
		if(capacity <= 0)
			throw new IllegalArgumentException("Stack's capacity must be positive");
		
		top = -1;
		stack = (T[]) new Object[capacity];
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

	public T pop() {

		if(top == -1)
			throw new IllegalStateException("Stack is empty");
		
		T element = stack[top];
		stack[top] = null;
		top = top - 1;
		
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
	public int size(){
		return top + 1;
	}
	
	public String toString(){
		
		String str = "{";
		
		for(int i=0; i<top; i++){
			str += stack[i] + ", ";
		}
		return str += stack[top] + "}";
	}
}
