package com.example.tpdm_u4_ejercicio1_larretaorihuela;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class Icono {
    Bitmap imagen;
    int x,y;

    public Icono(Lienzo este, int imagen, int posx, int posy){
        this.imagen = BitmapFactory.decodeResource(este.getResources(),imagen);
        x = posx;
        y = posy;
    }

    public void mover(int dedox, int dedoy){
        x = dedox-(imagen.getWidth()/2);
        y = dedoy-(imagen.getHeight()/2);
    }

    public void pintar(Canvas c, Paint p){
        c.drawBitmap(imagen,x,y,p);
    }

    public boolean estaEnArea(int Xdato, int Ydato){
        if(Xdato >= x && Xdato <= (x + imagen.getWidth())){
            if(Ydato >= y &&  Ydato <= (y + + imagen.getHeight())){
                return true;
            }
        }

        return false;
    }

    public boolean estaEnColision(Icono objetoB){
        int x2 = x + imagen.getWidth();
        int y2 = y + imagen.getHeight();

        if(objetoB.estaEnArea(x2,y2)){
            return true;
        }
        if(objetoB.estaEnArea(x2,y)){
            return true;
        }
        if(objetoB.estaEnArea(x,y)){
            return true;
        }
        if(objetoB.estaEnArea(x,y2)){
            return true;
        }
        return false;
    }

}
