package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;

public class ColorTable {

    private Color[] ReducedColors;
	
	public ColorTable(BufferedImage img, int numColors) {
        //ReducedColors = new Color[numColors];
        HashMap origColors = originalColors(img);
        //System.out.println(origColors.toString());
        if(origColors.size() <= numColors){
            Collection cols = origColors.values();
            ReducedColors = (Color[]) cols.toArray(new Color[origColors.size()]);
            for(int i = 0; i < ReducedColors.length; i++){
                System.out.println(ReducedColors[i]);
            }
        }
        else{
            ArrayList Boxes = new ArrayList();
            ThreeDBox box = new ThreeDBox(0,origColors); //the largest box (level 0)
            Boxes.add(box);
            int num = 1;

            boolean done = false;

            while(num < numColors && !done){

                ThreeDBox next = findSplittable(Boxes);
                if(next != null){

                    ThreeDBox[] newBoxes = split(next);

                    Boxes.remove(next);
                    Boxes.add(newBoxes[0]);
                    Boxes.add(newBoxes[1]);
                    num++;

                }else{
                    done = true;
                }
            }

            //get the average colour from each of the boxes in the arraylist
            ReducedColors = new Color[numColors];
            for(int i=0; i<ReducedColors.length; i++){
                ThreeDBox redbox = (ThreeDBox)Boxes.get(i);
                ReducedColors[i] = averageColor(redbox);
            }


        }
		System.out.println("With: " + numColors);
	}
	
	/**
	 * Gibt die Farbe der erstellten Farbtabelle zurück, mit der
	 * die originale Farbe dargestellt werden soll.
	 * @param orig Die originale Farbe
	 * @return
	 */
	public Color getReducedColor(Color orig) {
		int index = 0;
        float best = calcdist(orig, ReducedColors[0]);
        for(int i = 1; i < ReducedColors.length; i++){
            if(best > calcdist(orig, ReducedColors[i])){
                best = calcdist(orig, ReducedColors[i]);
                index = i;
            }
        }
        return ReducedColors[index];
		
		// return dummy colors
		//if (orig.getGreen() > 100) {
		//	return Color.WHITE;
		//} else {
		//	return Color.BLUE;
		//}
	}

    /**
     * @param colorA
     * @param colorB
     * @return
     */
    private float calcdist(Color colorA, Color colorB){
        float red = Math.abs(colorA.getRed() - colorB.getRed());
        float green = Math.abs(colorA.getGreen() - colorB.getGreen());
        float blue = Math.abs(colorA.getBlue() - colorB.getBlue());
        return (float) ((red+green+blue)/3.0);
    }

    /**
     * @param img
     * @return
     */
    private HashMap originalColors(BufferedImage img){
        HashMap Colors = new HashMap();
        int height = img.getHeight();
        int width = img.getWidth();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(Colors.containsKey(img.getRGB(i,j))){
                    AdvColor col = (AdvColor) Colors.get(img.getRGB(i,j));
                    col.add();
                }
                else{
                    AdvColor col = new AdvColor(img.getRGB(i,j));
                    Colors.put(img.getRGB(i,j),col);
                }
            }
        }
        return Colors;
    }

    /**
     *Sucht die groesste Box und gibt diese zurueck
     * @param Boxes In Frage kommende Boxen
     * @return Beste Box
     *
     */
    private ThreeDBox findSplittable(ArrayList Boxes) {

        ArrayList Splittable = new ArrayList();

        for (int i = 0; i < Boxes.size(); i++) {//Sucht nach geeigneten Boxen
            ThreeDBox box = (ThreeDBox) Boxes.get(i);
            if (box.getLength() > 1) {//Mehr als eine Farbe
                Splittable.add(box);//Fuege moeglichen hinzu
            }
        }

        if(Splittable.size() > 0){
            ThreeDBox best = (ThreeDBox) Splittable.get(0);//NImmt erste als beste an
            int bestDepth = best.getDepth();
            for(int i = 1; i < Splittable.size(); i++){
                ThreeDBox newbox = (ThreeDBox) Splittable.get(i);

                if(bestDepth > newbox.getDepth()){//Falls groessere Box gefunden
                    bestDepth = newbox.getDepth();
                    best = newbox;//nimm als neue beste an
                }

            }
            return best;
        }
        else{
            return null;
        }
    }

    /**
     * @param box
     * @return
     */
    ThreeDBox [] split(ThreeDBox box){

        int channel = biggestSide(box);
        int depth = box.getDepth();

        //get the median only counting along the longest RGB dimension
        HashMap colors = box.getHashMap();
        AdvColor[] Colors = box.getColors();
        int sum = 0;

        for(int i = 0; i < Colors.length; i++ ){
            AdvColor color = Colors[i];
            if(channel == 0){
                sum += color.getRed();
            }
            else if(channel == 1){
                sum += color.getGreen();
            }
            else{
                sum += color.getBlue();
            }

        }


        int median = sum / Colors.length;

        HashMap boxone = new HashMap();
        HashMap boxtwo = new HashMap();
        int val;

        for(int i = 0; i < Colors.length; i++){

            AdvColor color = Colors[i];

            if(channel == 0){
                val = color.getRed();
            }
            else if(channel == 1){
                val =  color.getGreen();
            }
            else{
                val =  color.getBlue();
            }

            if(val < median){
                boxone.put(color.getRGB(),color);
            }else{
                boxtwo.put(color.getRGB(),color);
            }
        }

        ThreeDBox [] boxes = new ThreeDBox[2];
        boxes[0] = new ThreeDBox(depth + 1, boxone); //the 'level' has increased
        boxes[1] = new ThreeDBox(depth + 1, boxtwo);

        return boxes;

    }

    /**
     * @param box
     * @return
     */
    Color averageColor(ThreeDBox box){
        AdvColor[] Colors = box.getColors();
        int Red = 0;
        int Blue = 0;
        int Green = 0;
        //int count = 0;
        for(int i = 0; i < Colors.length; i++){
            //count = Colors[i].num;
            Red += Colors[i].getRed() /** * count*/;
            Green += Colors[i].getGreen() /** * count*/;
            Blue += Colors[i].getRed() /** * count*/;
        }
        Red /= (Colors.length );
        Green /= (Colors.length );
        Blue /= (Colors.length );

        return new Color(Red,Green,Blue);
    }

    /**
     * @param box
     * @return
     */
    int biggestSide(ThreeDBox box){

        int rdiff = box.rmax - box.rmin;
        int gdiff = box.gmax - box.gmin;
        int bdiff = box.bmax - box.bmin;
        if(rdiff > gdiff && rdiff > bdiff){
            return 0;
        }
        else if(gdiff > rdiff && gdiff > bdiff){
            return 1;
        }
        else{
            return 2;
        }
    }

}
