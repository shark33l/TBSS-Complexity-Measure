public class Mountain extends Fractal {
	private Point p1;
	private Point p2;
	private Point p3;
	private int dev;
	private List<Side> sides;
	private int level;

public Mountain(int dev, Point p1, Point p2, Point p3) {
	super();
	this.dev = dev;
	this.p1 = p1;
	this.p2 = p2;
	this.p3 = p3;
	sides = new LinkedList<Side>();
	level = 0;
}

@Override
public String getTitle() {
	return "Snett berg";
}	
private void fractalTriangle(TurtleGraphics turtle, int order, int dev, Point p1, Point p2, Point p3, int level) {
if (order == 0) {
	turtle.moveTo(p1.getX(), p1.getY());
	turtle.penDown();
	turtle.forwardTo(p2.getX(), p2.getY());
	turtle.forwardTo(p3.getX(), p3.getY());
	turtle.forwardTo(p1.getX(), p1.getY());
	turtle.penUp();
	setLevel(level);
	System.out.println(sides.size());
} else {
	double offset1 = RandomUtilities.randFunc(dev);
	double offset2 = RandomUtilities.randFunc(dev);
	double offset3 = RandomUtilities.randFunc(dev);
	dev /= 2;
	Point mLeft;
	Point mRight;
	Point mBottom;

	mLeft = getMPoint(p1, p2, offset1, level);
	mRight = getMPoint(p2, p3, offset2, level);
	mBottom = getMPoint(p1, p3, offset3, level);
	fractalTriangle(turtle, order - 1, dev, mLeft, p2, mRight, level + 1);
	fractalTriangle(turtle, order - 1, dev, mLeft, mRight, mBottom, level + 1);
	fractalTriangle(turtle, order - 1, dev, p1, mLeft, mBottom, level + 1);
	fractalTriangle(turtle, order - 1, dev, mBottom, mRight, p3, level + 1);
}


private Point getMPoint(Point p1, Point p2, double offset, int level){
	Side test = new Side(p1, p2);
	int index = sides.indexOf(test);
	if (index >= 0 ) {
		Side temp = sides.get(index);
		sides.remove(index);
		return temp.getMPoint();
	} else {
		Point temp = new Point((p1.getX() + p2.getX()) / 2.0,
					((p1.getY() + p2.getY()) / 2.0) + offset);
		sides.add(new Side(p1, p2, temp, level));
		return temp;
}

private void setLevel(int level){
	Iterator<Side> itr = sides.iterator();
	while(itr.hasNext()){
		Side temp = itr.next();
		if(temp.getLevel() >= level){
			itr.remove();
}
}
}
}