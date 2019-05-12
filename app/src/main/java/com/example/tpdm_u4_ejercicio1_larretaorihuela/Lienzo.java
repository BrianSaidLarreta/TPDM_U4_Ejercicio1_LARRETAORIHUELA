package com.example.tpdm_u4_ejercicio1_larretaorihuela;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Lienzo extends View {
    String posicion;
    Bitmap icono,icono2;
    Icono flecha, lentes, puntero;


    public Lienzo(Context context) {
        super(context);

        flecha = new Icono(this,R.drawable.senalflecha,200,400);
        flecha = new Icono(this,R.drawable.lentes,400,400);
        puntero = null;
    }

    protected void onDraw(Canvas c){
        Paint p = new Paint();
        // EEl evento onDraw permite dibujar en la pantalla del celular
        // El objeto Canvas ejecuta el dibujo
        // El objeto Paint indica sus caracteristicas como tamaño, color, etc
        c.drawColor(Color.BLUE);
        p.setColor(Color.WHITE);
        p.setTextSize(60f);
        c.drawText("Brian Said Larreta Orihuela",50,50,p);

        c.drawRect(100,200,350,400,p);

        p.setColor(Color.YELLOW);
        c.drawOval(100,300,410,100,p);

        p.setColor(Color.RED);
        c.drawCircle(100,200,200,p);

        p.setTextSize(40f);
        c.drawText(posicion,100,300,p);

        //c.drawBitmap(flecha.imagen,flecha.x,flecha.y,p);
        //c.drawBitmap(lentes.imagen,lentes.x,lentes.y,p);

        flecha.pintar(c,p);
        lentes.pintar(c,p);

    }

    public boolean onTouchEvent(MotionEvent me){
        // El evento onTouchEvent permite detectar los toques
        // de uno o más dedos que se hace en el área de dibujo


        /*me.getAction() = accion, presiono, soltar, mover
                        pos x, pos y  */
        int accion = me.getAction();
        int posx = (int) me.getX();
        int posy = (int) me.getY();

        posicion = posx+" , " + posy;

        switch(accion){
            case MotionEvent.ACTION_DOWN:
                //presionó
                if(flecha.estaEnArea(posx,posy)){
                    puntero = flecha;
                    if(puntero.estaEnColision(lentes)){
                        Toast.makeText(getContext(),"SI",Toast.LENGTH_SHORT).show();
                    }
                }
                if(lentes.estaEnArea(posx,posy)){
                    puntero = lentes;
                    if(puntero.estaEnColision(flecha)){
                        Toast.makeText(getContext(),"SI",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //Movio
                if(puntero != null){
                    puntero.mover(posx,posy);
                }
                break;
            case MotionEvent.ACTION_UP:
                //Soltó
                puntero =  null;
                break;
        }
        invalidate();
        return true;
    }

}
