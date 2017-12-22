package packagename;

import hsa_ufa.Console;
import java.awt.*;

public class trace_class3 {

	static Console c = new Console(800, 600, 18, "Console");

	public static void main(String[] args) throws InterruptedException {
		
		
		int lives = 100;
		int level = 1;//player's current level
		int starx = 10;//star's x variable
		int stary = 300;//star's y variable
		//int varx = 0;//variation in enemy shots
		//int vary = 0;//variation in enemy shots
		int enemybx[] = {};//enemy bullet x locations
		int enemyby[] = {};//enemy bullet y locations
		int bspdx[] = {};//enemy bullet speed for x
		int bspdy[] = {};//enemy bullet speed for y
		int ebdead[] = {};//enemy bullet dead
		int moving[] = {0, 0, 0, 0};//time enemies spend moving
		String direction[] = {"null", "null", "null", "null"};//direction enemies move
		int ereload[]={10, 0, 20, 30};//enemy reload timer
		int enemyx[] = {300, 300, 500, 500};//enemie's x value
		int enemyy[] = {10, 540, 10, 540};//enemie's y value
		int enemydead[] ={1, 1, 1, 1};//whether or not an enemy is shot 
		String enemyai[] = {"turret", "turret", "turret", "turret"};//what the enemy does
		int rectx[] = {200, 200, 400, 400, 600, 600};//rectangle x value
		int recty[] = {0, 400, 0, 400, 0, 350};//rectangle y value
		int rectw[] = {50, 50, 50, 50, 50, 50};//rectangle width
		int recth[] = {200, 200, 200, 200, 250, 250};//rectangle height
		double  w = 0;//math variables for bullets
		double  h = 0;
		double z = 0;
		double  w2 = 0;
		double  h2 = 0;
		double z2;
		int bulletspeedx[]={};//player bullet speed for x
		int bulletspeedy[]={};//player bullet speed for y
		int bulletdead[]= {};//whether or not the bullet has hit anything
		int bulletx[]= {};  
		int bullety[]= {};
		int reload = 0;
		int foot = 0;
		int x = 700;//player x location
		int y = 500;//player y location
		int mx;//mouse x location
		int my;//mouse y location
		int owch=0;
		c.enableMouse();
		c.enableMouseMotion();
		while (true) {
			synchronized (c) {
				c.clear();
				c.println("LIVES : "+lives);
				if(owch>0){
					c.setColor(Color.RED);
				}
				c.fillRect(x, y, 50, 50);
				c.setColor(Color.BLACK);
				for(int i = 0; i<enemyx.length;i++){
					if(enemydead[i]==1)
				c.fillRect(enemyx[i],enemyy[i],50,50);
				}
				for(int i = 0; i<rectx.length; i++){
					c.fillRect(rectx[i], recty[i], rectw[i], recth[i]);
				}
				for(int i = 0; i<bulletx.length; i++){
					if(bulletdead[i]==1)
					c.fillOval(bulletx[i], bullety[i], 10, 10);
				}
				for(int i = 0; i<enemybx.length; i++){
					if(ebdead[i]==1)
					c.fillOval(enemybx[i], enemyby[i], 10, 10);
				}
				c.fillRect(starx, stary, 50, 50);
				c.setColor(Color.YELLOW);
				c.fillStar(starx, stary, 50, 50);
				c.setColor(Color.BLACK);
			}
			mx = c.getMouseX();
			my = c.getMouseY();
			
			if(c.isKeyDown('W')){
				y-=5;
				for(int i = 0; i<rectx.length; i++){
					if(x+50>rectx[i]&&y+50>recty[i]&&rectx[i]+rectw[i]>x&&recty[i]+recth[i]>y) {
						y+=5;
					}
				}
				if(y<0){
					y+=5;
				}
			}
			if(c.isKeyDown('A')){
				x-=5;
				for(int i = 0; i<rectx.length; i++){
					if(x+50>rectx[i]&&y+50>recty[i]&&rectx[i]+rectw[i]>x&&recty[i]+recth[i]>y) {
						x+=5;
					}
				}
				if(x<0){
					x+=5;
				}
			}
			if(c.isKeyDown('S')){
				y+=5;
				for(int i = 0; i<rectx.length; i++){
					if(x+50>rectx[i]&&y+50>recty[i]&&rectx[i]+rectw[i]>x&&recty[i]+recth[i]>y) {
						y-=5;
					}
				}
				if(y>550){
					y-=5;
				}
			}
			if(c.isKeyDown('D')){
				x+=5;
				for(int i = 0; i<rectx.length; i++){
					if(x+50>rectx[i]&&y+50>recty[i]&&rectx[i]+rectw[i]>x&&recty[i]+recth[i]>y) {
						x-=5;
					}
				}
				if(x>750){
					x-=5;
				}
			}
				
			if(c.getMouseButton(0)&&reload<=0) {//space = shoot
				int tempbx[] = new int[bulletx.length + 1];
				for(int i=0;i<bulletx.length;i++)
					tempbx[i]=bulletx[i];
				tempbx[tempbx.length-1]=x+15;
				bulletx=tempbx;
				
				int tempby[] = new int[bullety.length + 1];
				for(int i=0;i<bullety.length;i++)
					tempby[i]=bullety[i];
				tempby[tempby.length-1]=y+15;
				bullety=tempby;				
				int temp[] = new int[bulletdead.length + 1];
				for(int i=0;i<bulletdead.length;i++)
					temp[i]=bulletdead[i];
				temp[temp.length-1]=1;
				bulletdead=temp;
				
				int tempspdx[] = new int[bulletspeedx.length + 1];
				for(int i = 0; i<bulletspeedx.length; i++)
					tempspdx[i] = bulletspeedx[i];
				
				int tempspdy[] = new int[bulletspeedy.length + 1];
				for(int i = 0; i<bulletspeedy.length; i++)
					tempspdy[i] = bulletspeedy[i];
				
			w2 = mx- bulletx[bulletx.length-1];
			h2 = my- bullety[bullety.length-1];
			z2 =  Math.sqrt(w2*w2+h2*h2);
			z2 = z2/10;

			tempspdx[tempspdx.length-1]= (int) (Math.round(w2/z2)*3);
			tempspdy[tempspdy.length-1]= (int) (Math.round(h2/z2)*3 );	
			bulletspeedx=tempspdx;
			bulletspeedy=tempspdy;
			reload=20;
			}
			
			
			for(int i = 0; i<bulletx.length; i++){
			if(bulletdead[i]==1){
			bulletx[i]+=bulletspeedx[i];
			bullety[i]+=bulletspeedy[i];
				}
			}
			
			for(int j = 0; j<enemyx.length; j ++){
				if(enemyai[j].equals("trap")){
					
				}
			else if(enemyai[j].equals("turret")||enemyai[j].equals("soldier")){
			if(ereload[j]<=0&&enemydead[j]==1){
			int tempenx[] = new int[enemybx.length + 1];
			for(int i = 0; i<enemybx.length; i++)
				tempenx[i] = enemybx[i];
			tempenx[tempenx.length-1] = enemyx[j]+15;
			enemybx = tempenx;
			
			int tempship[] = new int[ebdead.length + 1];
			for(int i = 0; i<ebdead.length; i++)
				tempship[i] = ebdead[i];
			tempship[tempship.length-1] = 1;
			ebdead = tempship;
			
			int tempbeny[] = new int[enemyby.length + 1];
			for(int i = 0; i<enemyby.length; i++)
				tempbeny[i] = enemyby[i];
			tempbeny[tempbeny.length-1] = enemyy[j]+15;
			enemyby = tempbeny;
			
			int tempbspdx[] = new int[bspdx.length + 1];
			for(int i = 0; i<bspdx.length; i++)
				tempbspdx[i] = bspdx[i];
			int tempbspdy[] = new int[bspdy.length + 1];
			for(int i = 0; i<bspdy.length; i++)
				tempbspdy[i] = bspdy[i];
/*			do{
			vary =(int)Math.round(Math.random()*100);
			}while(vary>50);
			vary -= 10;
			do{
			varx =(int)Math.round(Math.random()*100);
			}while(varx>50);
			varx -= 10;*/
			w = x+15 - enemyx[j];
			h = y+15 - enemyy[j];
			z =  Math.sqrt(w*w+h*h);
			z = z/10;
			tempbspdx[tempbspdx.length-1]= (int) (Math.round(w/z)*3);
			tempbspdy[tempbspdy.length-1]= (int) (Math.round(h/z)*3);	
			bspdx=tempbspdx;
			bspdy=tempbspdy;
			ereload[j] = 45;
			}
			}
			if(enemyai[j].equals("soldier")&&enemydead[j]==1){
				if(moving[j]<=0){
				do{
					foot =(int)Math.round(Math.random()*10);
					}while(foot>4);
				
				if(foot == 1){
					direction[j]="right";
				}
				else if(foot == 2) {
					direction[j]="left";
				}
				else if(foot == 3) {
					direction[j]="down";
				}
				else if(foot ==4) {
					direction[j]="up";
				}
				moving[j] = 15;
				}
				if(direction[j].equals("up")){
					enemyy[j]-=5;
					if(enemyy[j]<=0){
						direction[j]="down";
						moving[j]=15;
					}
					for(int i = 0; i<enemyx.length; i++){
						if(enemyx[j]+50>enemyx[i]&&enemyy[j]+50>enemyy[i]&&enemyx[i]+50>enemyx[j]&&enemyy[i]+50>enemyy[j]&&enemyx[i]!=enemyx[j]){
							direction[j]="down";
							moving[j]=15;
						}
					}
					for(int i = 0; i<rectx.length; i++){
						if(enemyx[j]+50>rectx[i]&&enemyy[j]+50>recty[i]&&rectx[i]+rectw[i]>enemyx[j]&&recty[i]+recth[i]>enemyy[j]){
							direction[j]="down";
							moving[j]=15;
						}
					}
				}
				else if(direction[j].equals("down")){
					enemyy[j]+=5;
					if(enemyy[j]>=550){
						direction[j]="up";
						moving[j]=15;
					}
					for(int i = 0; i<enemyx.length; i++){
						if(enemyx[j]+50>enemyx[i]&&enemyy[j]+50>enemyy[i]&&enemyx[i]+50>enemyx[j]&&enemyy[i]+50>enemyy[j]&&enemyx[i]!=enemyx[j]){
							direction[j]="up";
							moving[j]=15;
						}
					}
					for(int i = 0; i<rectx.length; i++){
						if(enemyx[j]+50>rectx[i]&&enemyy[j]+50>recty[i]&&rectx[i]+rectw[i]>enemyx[j]&&recty[i]+recth[i]>enemyy[j]){
							direction[j]="up";
							moving[j]=15;
						}
					}
				}
				else if(direction[j].equals("right")){
					enemyx[j]+=5;
					if(enemyx[j]>=750){
						direction[j]="left";
						moving[j]=15;
					}
					for(int i = 0; i<enemyx.length; i++){
						if(enemyx[j]+50>enemyx[i]&&enemyy[j]+50>enemyy[i]&&enemyx[i]+50>enemyx[j]&&enemyy[i]+50>enemyy[j]&&enemyx[i]!=enemyx[j]){
							direction[j]="left";
							moving[j]=15;
						}
					}
					for(int i = 0; i<rectx.length; i++){
						if(enemyx[j]+50>rectx[i]&&enemyy[j]+50>recty[i]&&rectx[i]+rectw[i]>enemyx[j]&&recty[i]+recth[i]>enemyy[j]){
							direction[j]="left";
							moving[j]=15;
						}
					}
				}
				else if(direction[j].equals("left")){
					enemyx[j]-=5;
					if(enemyx[j]<=0){
						direction[j]="right";
						moving[j]=15;
					}
					for(int i = 0; i<enemyx.length; i++){
						if(enemyx[j]+50>enemyx[i]&&enemyy[j]+50>enemyy[i]&&enemyx[i]+50>enemyx[j]&&enemyy[i]+50>enemyy[j]&&enemyx[i]!=enemyx[j]){
							direction[j]="right";
							moving[j]=15;
						}
					}
					for(int i = 0; i<rectx.length; i++){
						if(enemyx[j]+50>rectx[i]&&enemyy[j]+50>recty[i]&&rectx[i]+rectw[i]>enemyx[j]&&recty[i]+recth[i]>enemyy[j]){
							direction[j]="right";
							moving[j]=15;
						}
					}
				}
				
			
			moving[j]-=1;
			}//me soldier
				else if(enemyai[j].equals("patrolr"){
				enemyx+=4;
				if(enemyx>=550)
					enemyai[j] = "patroll";		
				for(int i = 0; i<rectx.length; i++){
					if(enemyx[j]+50>rectx[i]&&enemyy[j]+50>recty[i]&&rectx[i]+rectw[i]>enemyx[j]&&recty[i]+recth[i]>enemyy[j]){
						enemyai[j] = "patroll"
						i = rectx.length;
					}
				}
				}//patrolr bracket
					else if(enemyai[j].equals("patroll"){
				enemyx-=4;
				if(enemyx<=0)
					enemyai[j] = "patrolr";		
				for(int i = 0; i<rectx.length; i++){
					if(enemyx[j]+50>rectx[i]&&enemyy[j]+50>recty[i]&&rectx[i]+rectw[i]>enemyx[j]&&recty[i]+recth[i]>enemyy[j]){
						enemyai[j] = "patrolr"
						i = rectx.length;
					}
				}
				}//patroll bracket
					
			}//all enemies for loop
			
			for(int j = 0; j<enemyx.length; i++){
			if(enemyx[j]+50>rectx[i]&&enemyy[j]+50>recty[i]&&rectx[i]+rectw[i]>enemyx[j]&&recty[i]+recth[i]>enemyy[j]&&owch<=0){
				owch=15;
				lives -= 1;
				}
			}
			
			for(int i =0; i<enemybx.length;i++){
				if(enemybx[i]+10>x&&enemyby[i]+10>y&&x+50>enemybx[i]&&y+50>enemyby[i]&&owch<=0){
					owch=15;
					lives-=1;
				}
			}
			for(int i = 0; i<bspdx.length; i++){
					if(ebdead[i]==1){
				enemybx[i]+=bspdx[i];
				enemyby[i]+=bspdy[i];
					}
				}

			for(int i = 0;i<bulletdead.length;i++){
				for(int j = 0;j<rectx.length;j++)
				if(bulletx[i]+10>rectx[j]&&bullety[i]+10>recty[j]&&rectx[j]+rectw[j]>bulletx[i]&&recty[j]+recth[j]>bullety[i]){
					bulletdead[i]=0;
					bulletx[i]=-15;
				}
			}
			for(int i = 0;i<ebdead.length;i++){
				for(int j = 0;j<rectx.length;j++)
				if(enemybx[i]+10>rectx[j]&&enemyby[i]+10>recty[j]&&rectx[j]+rectw[j]>enemybx[i]&&recty[j]+recth[j]>enemyby[i]){
					ebdead[i]=0;
					enemybx[i]=-15;
				}
			}
			for(int i = 0; i<bulletx.length;i++){
				for(int j = 0; j<enemyx.length;j++){
					if(bulletx[i]+10>enemyx[j]&&bullety[i]+10>enemyy[j]&&enemyx[j]+50>bulletx[i]&&enemyy[j]+50>bullety[i]){
					enemydead[j]=0;	
					enemyx[j] = -50;
					enemyy[j] = -50;
					bulletdead[i] = 0;
					bulletx[i]= -15;
				}
				}
			}
			if(starx+50>x&&stary+50>y&&x+50>starx&&y+50>stary&&level==1){
				level = 2;
				for(int i = 0; i<bulletdead.length; i++){
					bulletdead[i] = 0;
					bulletx[i] = -15;
				}
        
           		     int tempdelete[] = new int[0];
				enemybx = tempdelete;
				enemyby = tempdelete;
				bspdx = tempdelete;
				bspdy = tempdelete;
				ebdead = tempdelete;
				
				int temprectx[] = new int[1];
				temprectx[0]=100;
				rectx=temprectx;
				
				int temprecty[] = new int[1];
				temprecty[0]=400;
				recty=temprecty;
				
				int temprectw[] = new int[1];
				temprectw[0]=600;
				rectw=temprectw;
				
				int temprecth[] = new int[1];
				temprecth[0]=50;
				recth=temprecth;
				
				x = 400;
				y = 525;
				
				enemyx[0] = 200;
				enemyx[1] = 280;
				enemyx[2] = 340;
				enemyx[3] = 500;
				enemyy[0] = 100;
				enemyy[1] = 120;
				enemyy[2] = 110;
				enemyy[3] = 130;
				for(int i = 0; i<enemydead.length; i++){
					enemydead[i] = 1;
					enemyai[i] = "soldier";
				}
				
				starx = 400;
				stary = 0;
			}
			else if(starx+50>x&&stary+50>y&&x+50>starx&&y+50>stary&&level==2){
				level = 3;
				for(int i = 0; i<bulletdead.length; i++){
					bulletdead[i] = 0;
					bulletx[i] = -15;
				}
				     int tempdelete[] = new int[0];
				enemybx = tempdelete;
				enemyby = tempdelete;
				bspdx = tempdelete;
				bspdy = tempdelete;
				ebdead = tempdelete;
				
				int temprectx[] = new int[5];
				temprectx[0]=100;
				temprectx[1]=200;
				temprectx[2]=400;
				temprectx[3]=500;
				temprectx[4]=550;
				rectx=temprectx;
				
				int temprecty[] = new int[5];
				temprecty[0]=100;
				temprecty[1]=500;
				temprecty[2]=150;
				temprecty[3]=500;
				temprecty[4]=350;				
				recty=temprecty;
				
				int temprectw[] = new int[5];
				temprectw[0]=50;
				temprectw[1]=200;
				temprectw[2]=50;
				temprectw[3]=200;
				temprectw[4]=200;
				rectw=temprectw;
				
				int temprecth[] = new int[5];
				temprecth[0]=400;
				temprecth[1]=50;
				temprecth[2]=200;
				temprecth[3]=50;
				temprecth[4]=50;
				recth=temprecth;
				
				x = 20;
				y = 515;
				starx = 705;
				stary = 35;
				//for enemies, x, y, moving, direction, ai, reload
				int tempex[] = new int[7];
				tempex[0] = 235;
				tempex[1] = 305;
				tempex[2] = 435;
				tempex[3] = 500;
				tempex[4] = 735;
				tempex[5] = 720;
				tempex[6] = 360;
				enemyx = tempex;
				int tempey[] = new int[7];
				tempey[0] = 275;
				tempey[1] = 140;
				tempey[2] = 410;
				tempey[3] = 245;
				tempey[4] = 430;
				tempey[5] = 160;
				tempey[6] = 10;
				enemyy = tempey;
				int tempmv[] = new int[7];
				tempmv[0] = 0;
				tempmv[1] = 0;
				tempmv[2] = 0;
				tempmv[3] = 0;
				tempmv[4] = 0;
				tempmv[5] = 0;
				tempmv[6] = 0;
				moving = tempmv;
				String tempdi[] = new String[7];
				tempdi[0] = "null";
				tempdi[1] = "null";
				tempdi[2] = "null";
				tempdi[3] = "null";
				tempdi[4] = "null";
				tempdi[5] = "null";
				tempdi[6] = "null";
				direction = tempdi;
				String tempai[] = new String[7];
				tempai[0] = "soldier";
				tempai[1] = "soldier";
				tempai[2] = "soldier";
				tempai[3] = "soldier";
				tempai[4] = "turret";
				tempai[5] = "turret";
				tempai[6] = "turret";
				enemyai = tempai;
				int temprel[] = new int[7];
				temprel[0] = 0;
				temprel[1] = 5;
				temprel[2] = 10;
				temprel[3] = 15;
				temprel[4] = 20;
				temprel[5] = 25;
				temprel[6] = 30;
				ereload = temprel;
				
				int tempedead[] = new int[7];
				for(int i = 0; i<tempedead.length; i++)
					tempedead[i] = 1;
				enemydead = tempedead;
			}
			if(starx+50>x&&stary+50>y&&x+50>starx&&y+50>stary&&level==3){
				level = 4;
				for(int i = 0; i<bulletdead.length; i++){
					bulletdead[i] = 0;
					bulletx[i] = -15;
				}
				     int tempdelete[] = new int[0];
				enemybx = tempdelete;
				enemyby = tempdelete;
				bspdx = tempdelete;
				bspdy = tempdelete;
				ebdead = tempdelete;
				
				int temprectx[] = new int[3];
				temprectx[0]=200;
				temprectx[1]=400;
				temprectx[2]=600;
				rectx=temprectx;
				
				int temprecty[] = new int[3];
				temprecty[0]=200;
				temprecty[1]=200;
				temprecty[2]=200;
				recty=temprecty;
				
				int temprectw[] = new int[3];
				temprectw[0]=50;
				temprectw[1]=50;
				temprectw[2]=50;
				rectw=temprectw;
				
				int temprecth[] = new int[3];
				temprecth[0]=200;
				temprecth[1]=200;
				temprecth[2]=200;
				recth=temprecth;
				
				x = 100;
				y = 300;
				starx = 700;
				stary = 300;
				
				int tempex[]=new int [8];
				tempex[0] = 300;
				tempex[1] = 300;
				tempex[2] = 300;
				tempex[3] = 300;
				tempex[4] = 500;
				tempex[5] = 500;
				tempex[6] = 500;
				tempex[7] = 500;
				enemyx = tempex;
				
				int tempey[]=new int [8];
				tempey[0] = 150;
				tempey[1] = 250;
				tempey[2] = 350;
				tempey[3] = 450;
				tempey[4] = 150;
			    tempey[5] = 250;
				tempey[6] = 350;
				tempey[7] = 450;
				enemyy=tempey;
				
				int tempmv[] = new int[8];
				tempmv[0] = 0;
				tempmv[1] = 0;
				tempmv[2] = 0;
				tempmv[3] = 0;
				tempmv[4] = 0;
				tempmv[5] = 0;
				tempmv[6] = 0;
				tempmv[7]=0;
				moving = tempmv;
				
				String tempdi[] = new String[8];
				tempdi[0] = "null";
				tempdi[1] = "null";
				tempdi[2] = "null";
				tempdi[3] = "null";
				tempdi[4] = "null";
				tempdi[5] = "null";
				tempdi[6] = "null";
				tempdi[7]="null";
				direction = tempdi;
				
				String tempai[] = new String[8];
				tempai[0] = "soldier";
				tempai[1] = "soldier";
				tempai[2] = "soldier";
				tempai[3] = "soldier";
				tempai[4] = "turret";
				tempai[5] = "turret";
				tempai[6] = "turret";
				tempai[7]="soldier";
				enemyai = tempai;
				
				int temprel[] = new int[8];
				temprel[0] = 0;
				temprel[1] = 5;
				temprel[2] = 10;
				temprel[3] = 15;
				temprel[4] = 20;
				temprel[5] = 25;
				temprel[6] = 30;
				temprel[7]=35;
				ereload = temprel;
				
				int tempedead[] = new int[8];
				for(int i = 0; i<tempedead.length; i++)
					tempedead[i] = 1;
				enemydead = tempedead;
				
				}

			if(c.isKeyDown(Console.VK_END)){
				x=starx;
				y=stary;
			}
			owch-=1;
			
			for(int i = 0; i<ereload.length;i++){
			ereload[i]-=1;
			}
			
			reload-=1;
			Thread.sleep(30);
		}//main big important while loop bracket
		//c.print(false);
						//Sup
	}
}
