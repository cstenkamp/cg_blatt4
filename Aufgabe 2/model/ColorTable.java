package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Collection;
import java.util.HashMap;
import java.util.ArrayList;

public class ColorTable {

    private Color[] ReducedColors;

    /**
     * Generiert eine neue ColorTable mit reduzierten Farben des Originalbildes
     * @param img Das Originalbild
     * @param numColors Anzahl der Farben
     */
    public ColorTable(BufferedImage img, int numColors) {
        //ReducedColors = new Color[numColors];
        HashMap origColors = originalColors(img);
        //System.out.println(origColors.toString());
        if(origColors.size() <= numColors){//Falls weniger Farben im Bild verwendet werden als angegeben
            Collection cols = origColors.values();
            ReducedColors = (Color[]) cols.toArray(new Color[origColors.size()]);
            for(int i = 0; i < ReducedColors.length; i++){
                System.out.println(ReducedColors[i]);
            }
        }
        else{
            ArrayList Boxes = new ArrayList();
            ThreeDBox box = new ThreeDBox(0,origColors); //Legt erste Box an die alle Farben umschliesst
            Boxes.add(box);
            int num = 1;//Aktuelle Nummer an Boxen

            boolean done = false;

            while(num < numColors && !done){//Solange noch Farben benoetigt werden

                ThreeDBox next = findSplittable(Boxes);//Sucht nach moeglichen neuen Boxen
                if(next != null){//Bricht ab sobald Boxen nicht mehr verkleinert werden koennen

                    ThreeDBox[] newBoxes = split(next);//Teilt Box an der laengsten Farbachse

                    Boxes.remove(next);
                    Boxes.add(newBoxes[0]);
                    Boxes.add(newBoxes[1]);
                    num++;//Fuegt neue Boxen hinzu

                }else{
                    done = true;
                }
            }
            //for(int i = 0; i < Boxes.size(); i++){
            //    System.out.println(Boxes.get(i).toString());
            //}



            ReducedColors = new Color[numColors];
            for(int i=0; i<ReducedColors.length; i++){
                ThreeDBox redbox = (ThreeDBox)Boxes.get(i);
                ReducedColors[i] = averageColor(redbox);//Fuegt mitte der Box dem Array hinzu
            }


        }
		//System.out.println("With: " + numColors);
	}
	
	/**
	 * Gibt die Farbe der erstellten Farbtabelle zurück, mit der
	 * die originale Farbe dargestellt werden soll.
	 * @param orig Die originale Farbe
	 * @return
	 */
	public Color getReducedColor(Color orig) {
		int index = 0;
        int best = calcdist(orig, ReducedColors[0]);
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
     * Berechnet distanz zwischen zwei Farbwerten
     * @param colorA Erste Farbe
     * @param colorB Zweite Farbe
     * @return int Abstand
     */
    private int calcdist(Color colorA, Color colorB){
        int red = Math.abs(colorA.getRed() - colorB.getRed());
        int green = Math.abs(colorA.getGreen() - colorB.getGreen());
        int blue = Math.abs(colorA.getBlue() - colorB.getBlue());
        return (red+green+blue)/3;
    }

    /**
     * Wandelt ein image in eine HashMap um
     * @param img Das Bild
     * @return Eine Hashmap
     */
    private HashMap originalColors(BufferedImage img){
        HashMap Colors = new HashMap();
        int height = img.getHeight();
        int width = img.getWidth();//Setzt Groesse
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(Colors.containsKey(img.getRGB(i,j))){//Falls schon vorhanden
                    AdvColor col = (AdvColor) Colors.get(img.getRGB(i,j));
                    col.add();//Erhoehe anzahl
                }
                else{//Falls noch nicht vorhanden
                    AdvColor col = new AdvColor(img.getRGB(i,j));
                    Colors.put(img.getRGB(i,j),col);//Fuege hinzu
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
     * Teilt eine Box entlang der Laengsten Seite
     * @param box Zu teilende Box
     * @return Zwei neue Boxen
     */
    ThreeDBox [] split(ThreeDBox box){

        int channel = biggestSide(box);//Bestimmt laengste Seite
        int depth = box.getDepth();

        AdvColor[] Colors = box.getColors();
        int sum = 0;

        for(int i = 0; i < Colors.length; i++ ){//Bestimmt Mittelwert der laengsten Kante
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

        for(int i = 0; i < Colors.length; i++){//Baut neue Boxen

            AdvColor color = Colors[i];

            if(channel == 0){//Benutzt laengste Seite
                val = color.getRed();
            }
            else if(channel == 1){
                val =  color.getGreen();
            }
            else{
                val =  color.getBlue();
            }

            if(val < median){//Prueft ob Wert in linker oder rechter Haelfte liegt
                boxone.put(color.getRGB(),color);
            }else{
                boxtwo.put(color.getRGB(),color);
            }
        }

        ThreeDBox [] boxes = new ThreeDBox[2];//Gibt beide Boxen zurueck
        boxes[0] = new ThreeDBox(depth + 1, boxone); //the 'level' has increased
        boxes[1] = new ThreeDBox(depth + 1, boxtwo);

        return boxes;

    }

    /**
     * Bestimmt den Mittelwert der Farbe einer Box
     * @param box Eine Box
     * @return Den Mittelwert
     */
    Color averageColor(ThreeDBox box){
        AdvColor[] Colors = box.getColors();
        int Red = 0;
        int Blue = 0;
        int Green = 0;
        //int count = 0;
        for(int i = 0; i < Colors.length; i++){//Zaehlt vorkommen
            //count = Colors[i].num;
            Red += Colors[i].getRed() /** * count*/;
            Green += Colors[i].getGreen() /** * count*/;
            Blue += Colors[i].getBlue() /** * count*/;
        }
        Red /= (Colors.length );
        Green /= (Colors.length );
        Blue /= (Colors.length );
        //System.out.println(Red + " " + Green + " " + Blue);
        return new Color(Red,Green,Blue);//Berechnet neue mittlere Farbe
    }

    /**
     * Bestimmt laengste Seite
     * @param box Eine Box
     * @return Laengste Seite ( 0 == Red, 1 == Green 2 == Blue)
     */
    int biggestSide(ThreeDBox box){

        int rdiff = box.rmax - box.rmin;
        int gdiff = box.gmax - box.gmin;
        int bdiff = box.bmax - box.bmin;
        if(rdiff > gdiff && rdiff > bdiff){//Falls Rot laengste
            return 0;
        }
        else if(gdiff > rdiff && gdiff > bdiff){//Falls Gruen Laengste
            return 1;
        }
        else{//Falls Blau laengste
            return 2;
        }
    }

}
