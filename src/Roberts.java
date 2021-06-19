import java.awt.*;
    import java.awt.image.BufferedImage;
    import java.io.*;
    import javax.imageio.ImageIO;
    import javax.swing.JFrame;

    public class Roberts {

      BufferedImage image;
      int width;
      int height;

      

      public Roberts() {


       int[] tablica_red = new int[256];

       try {


         File input = new File("Zdjęcie_przed.jpg");

         image = ImageIO.read(input);
         width = image.getWidth();
         height = image.getHeight();

      
         int[][] maska = new int[3][3];

        
         maska[0][0] = 0;
         maska[0][1] = 0;
         maska[0][2] = 0;
         maska[1][0] = 0;
         maska[1][1] = 1;  
         maska[1][2] = -1;
         maska[2][0] = 0;
         maska[2][1] = 0;
         maska[2][2] = 0;
        

         int pomoc_r, pomoc_g, pomoc_b;
         int red_prym, green_prym, blue_prym;                      
         int red_wy, green_wy, blue_wy;                                



         for(int i=1; i<height-1; i++){
               for(int j=1; j<width-1; j++){
                

            pomoc_r = 0;
            pomoc_g = 0;
            pomoc_b = 0;
                 

            for(int k=-1; k<=1; k++){
             for(int l=-1; l<=1; l++){



            Color c = new Color(image.getRGB(j+k, i+l));

            int red = (int)(c.getRed());
            int green = (int)(c.getGreen());
            int blue = (int)(c.getBlue());


           pomoc_r += red * maska[k+1][l+1];
            pomoc_g += green * maska[k+1][l+1];
           pomoc_b += blue * maska[k+1][l+1];


             }

            }
          
        System.out.printf("\n pixel[" + i + j + "]= " + pomoc_r + pomoc_g + pomoc_b);  
            
        red_wy = (int) Math.abs(pomoc_r);
        green_wy = (int) Math.abs(pomoc_g);
        blue_wy = (int) Math.abs(pomoc_b);
    

        
    if (pomoc_r>=0 && pomoc_r <=255) red_wy = pomoc_r;
    else     red_wy = 0;
        

    if (pomoc_g>=0 && pomoc_g <=255) green_wy = pomoc_g;
    else     green_wy = 0;
              

    if (pomoc_b>=0 && pomoc_b <=255) blue_wy = pomoc_b;
    else     blue_wy = 0;


        Color newColor = new Color(red_wy, green_wy, blue_wy);
        image.setRGB(j,i,newColor.getRGB());


      }
     }


     File ouptut = new File("Zdjęcie_po_roberts'ie.jpg");
     ImageIO.write(image, "jpg", ouptut);

     

   } catch (Exception e) {}
   
  }

  static public void main(String args[]) throws Exception 

  {

   Roberts obj = new Roberts();

  }

}
