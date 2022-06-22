package com.coderscampus.testDrivenDevelopment;

import java.util.Optional;

public class CustomArrayList<T> implements CustomList<T> {

	Object[] items = new Object[10];
	int itemCounter = 0;

	@Override
	public boolean add(T item) {

		/**
		 * This method should add a new item into the <code>CustomList</code> and should
		 * return <code>true</code> if it was successfully able to insert an item.
		 * 
		 * @param item the item to be added to the <code>CustomList</code>
		 * @return <code>true</code> if item was successfully added, <code>false</code>
		 *         if the item was not successfully added (note: it should always be
		 *         able to add an item to the list)
		 */
		add(itemCounter, item);

		return true;
	}

	@Override
	public boolean add(int index, Object item) throws IndexOutOfBoundsException {
		/**
		 * This method should add a new item into the <code>CustomList</code> at the
		 * specified index (thus shuffling the other items to the right). If the index
		 * doesn't yet exist, then you should throw an
		 * <code>IndexOutOfBoundsException</code>.
		 * 
		 * @param index the spot in the zero-based array where you'd like to insert your
		 *              new item
		 * @param item  the item that will be inserted into the <code>CustomList</code>
		 * @return <code>true</code> when the item is added
		 * @throws IndexOutOfBoundsException
		 */
		if (index > itemCounter) {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
		}
		if (itemCounter == items.length) {
			items = growArray();
		}

		for (int i = itemCounter - 1; i > index - 1; i--) {
			items[i + 1] = items[i];
		}
		items[index] = item;
		itemCounter++;

		return true;
	}

	@Override
	public int getSize() {
		return itemCounter;
	}

	@Override
	public T get(int index) throws IndexOutOfBoundsException {
		
		for(int i = 0; i < items.length; i++) {
			if(items[i] == null) {
			items[i] = Optional.empty();
		}
		}
		// cast the arraylist you want to return to T
		return (T) items[index];
	}

	@Override
	public T remove(int index) throws IndexOutOfBoundsException {
		// System.out.println(items.length);
		
		if (index <= items.length - 1 && index >= 0) {
			T removedItem = (T) items[index];
			
			if (index == items.length-1) {
				for (int i = index; i > 0; i--) {
					items[i] = items[i-1];
				}
				items = checkArrayforDuplicatesandReplaceArray(items);
				itemCounter--;
				return removedItem;
			} else {

				for (int i = index; i < itemCounter + 1; i++) {
					items[i] = items[i + 1];
				}
				itemCounter--;
				return removedItem;
			}
		} else {
			throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
		}
	 
	}

	private Object[] growArray() {
		// this method grows the array if the amount of values to fill the
		// indexes exceeds the size of the array
		Object[] backingArray = new Object[items.length * 2];
		int counter = 0;
		while (counter < items.length) {
			backingArray[counter] = items[counter];
			counter++;
		}
		return backingArray;

	}
	private Object[] checkArrayforDuplicatesandReplaceArray(Object[]array) {
		
		for(int i = 0; i < array.length; i++) {
			
			if(array[i]==array[i+1]) {
				
				Object[]copy = new Object[array.length-1];
				System.arraycopy(array, 0, copy, 0, i);
				System.arraycopy(array, i+1, copy, i, array.length-i-1);
				return copy;
				
			}
			
		}
		
		return array;
	}
}
	
