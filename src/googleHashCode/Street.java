package googleHashCode;

import java.util.Objects;
import java.util.Queue;

public class Street {
	String name;
	int begin;
	int end;
	int length;
	Queue<Car> carsWaiting;
	int carsToPass;
	
	Street(String name, int begin, int end, int length, Queue<Car> carsWaiting, int carsToPass) {
		this.name = name;
		this.begin = begin;
		this.end = end;
		this.length = length;
		this.carsWaiting = carsWaiting;
		this.carsToPass = carsToPass;
	}

	@Override
	public int hashCode() {
		return Objects.hash(begin, end, length, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Street))
			return false;
		Street other = (Street) obj;
		return begin == other.begin && end == other.end && length == other.length && Objects.equals(name, other.name);
	}
	
	
}
