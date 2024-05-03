import java.awt.*;
import java.applet.*;
import java.util.*;
import java.io.*;

public class shapedraw extends Applet{

	public void paint(Graphics g){

		drawtriangle(g, 200, 0, 300, 0, 250, 100);
		translatetriangle(g, 200, 0, 300, 0, 250, 100, 0, 200);
		translatetriangle(g, 200, 0, 300, 0, 250, 100, 0, 400);
		translatetriangle(g, 200, 0, 300, 0, 250, 100, 0, 600);
		
		drawsquare(g, 0, 0, 100, 0, 100, 100, 0, 100);
		translatesquare(g,0, 0, 100, 0, 100, 100, 0, 100, 0, 400);
		translatesquare(g,0, 0, 100, 0, 100, 100, 0, 100, 0, 200);
		translatesquare(g,0, 0, 100, 0, 100, 100, 0, 100, 0, 600);
		
		circle(g, 400, 50, 50);
		translatecircle(g, 400, 50, 50, 0, 200);
		translatecircle(g, 400, 50, 50, 0, 400);
		translatecircle(g, 400, 50, 50, 0, 600);
	}
	
	//bresenham line drawing
	void drawline(Graphics g, int x0, int y0, int x1, int y1)  
{  
    int dx, dy, p, x, y;  
    dx=x1-x0;  
    dy=y1-y0;  
    x=x0;  
    y=y0;  
    p=2*dy-dx;  
    while(x<x1)  
    {  
        if(p>=0)  
        {  
            g.fillOval((int)x,(int)y,5,5);  
            y=y+1;  
            p=p+2*dy-2*dx;  
        }  
        else  
        {  
            g.fillOval((int)x,(int)y,5,5); 
            p=p+2*dy;}  
            x=x+1;  
        }  
}  
	
	//dda line algorithm
	public void line(Graphics g, double x1, double y1, double x2, double y2) {
			
		double dx,dy,steps,x,y,k;
		double xc,yc;
		dx=x2-x1;
		dy=y2-y1;
		
		if(Math.abs(dx)>Math.abs(dy))
			steps=Math.abs(dx);
		else
			steps=Math.abs(dy);
			xc=(dx/steps);
			yc=(dy/steps);
			x=x1;
			y=y1;
			g.fillOval((int)x1,(int)y1,5,5);

		for(k=1;k<=steps;k++){
			x=x+xc;
			y=y+yc;
			g.fillOval((int)x,(int)y,5,5);
		}
	}
	
	
	public void drawtriangle(Graphics g, double x1, double y1, double x2, double y2, double x3, double y3){
		line(g, x1,y1,x2,y2);
		line(g, x2, y2, x3,y3);
		line(g, x3, y3, x1, y1);
	}
	
	
	public void translatetriangle(Graphics g, double x1, double y1, double x2, double y2, double x3, double y3, double dx, double dy){
		line(g, x1+dx,y1+dy,x2+dx,y2+dy);
		line(g, x2+dx, y2+dy, x3+dx,y3+dy);
		line(g, x3+dx, y3+dy, x1+dx, y1+dy);
	}


	public void drawsquare(Graphics g, double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4){
		line(g, x1,y1,x2,y2);
		line(g, x2, y2, x3,y3);
		line(g, x3, y3, x4, y4);
		line(g, x4, y4, x1, y1);
		
	}

	public void translatesquare(Graphics g, double x1, double y1, double x2, double y2, double x3, double y3,double x4, double y4,  double dx, double dy){
		line(g, x1+dx,y1+dy,x2+dx,y2+dy);
		line(g, x2+dx, y2+dy, x3+dx,y3+dy);
		line(g, x3+dx, y3+dy, x4+dx, y4+dy);
		line(g, x4+dx, y4+dy, x1+dx, y1+dy);
	}
	
	//midpoint circle
  public void plot(Graphics g, int x, int y, int cx, int cy) {
        g.fillOval(cx + x, cy + y,5,5);
        g.fillOval(cx - x, cy + y,5,5);
		g.fillOval(cx + x, cy - y,5,5);
        g.fillOval(cx - x, cy - y,5,5);
		g.fillOval(cx + y, cy + x,5,5);
        g.fillOval(cx - y, cy + x,5,5);
		g.fillOval(cx + y, cy - x,5,5);
		g.fillOval(cx - y, cy - x,5,5);
    }
	
	//midpoint circle
	public void circle(Graphics g, int cx, int cy, int radius) {
        int x = 0;
        int y = radius;
        int p = 1 - radius;

        plot(g, x, y, cx, cy);

        while (x < y) {
            x++;
			
            if (p < 0) {
                p += 2 * x + 1;
            } else {
                y--;
                p += 2 * (x - y) + 1;
            }
            plot(g, x, y, cx, cy);
        }
    }
	
	public void translatecircle(Graphics g, int cx, int cy, int radius, int dx , int dy) {
		circle(g, cx + dx, cy + dy, radius);
	}

	// using Bresenham's algorithm
void circleBres(Graphics g, int xc, int yc, int r)
{
    int x = 0, y = r;
    int d = 3 - 2 * r;
    plotcircle(g, xc, yc, x, y);
    while (y >= x)
    {
        
        x++;
        if (d > 0)
        {
            y--; 
            d = d + 4 * (x - y) + 10;
        }
        else
            d = d + 4 * x + 6;
        plotcircle(g, xc, yc, x, y);
        
    }
}

public void plotcircle(Graphics g, int x, int y, int cx, int cy) {
        g.fillOval(cx + x, cy + y,5,5);
        g.fillOval(cx - x, cy + y,5,5);
		g.fillOval(cx + x, cy - y,5,5);
        g.fillOval(cx - x, cy - y,5,5);
		g.fillOval(cx + y, cy + x,5,5);
        g.fillOval(cx - y, cy + x,5,5);
		g.fillOval(cx + y, cy - x,5,5);
		g.fillOval(cx - y, cy - x,5,5);
    }

}

/*<applet code="triangle.class" width="800" height="800"></applet>*/