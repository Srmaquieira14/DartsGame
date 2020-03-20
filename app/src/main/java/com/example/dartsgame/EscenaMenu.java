package com.example.dartsgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class EscenaMenu extends Escena {
    public Rect[] HitBoxBotones = new Rect[3];

    public EscenaMenu(Context context, int numEscena, int screenWidth, int screenHeight){
        super(context,numEscena,screenWidth,screenHeight);
        this.setHitBoxBotones();
    }

    public void dibujar(Canvas c){
        Paint pText = new Paint();
        pText.setColor(Color.BLACK);
        pText.setTextSize(50);

        Paint p = new Paint();
        p.setColor(Color.RED);
        p.setStyle(Paint.Style.STROKE);
        p.setStrokeWidth(5);

        c.drawColor(Color.BLUE);
        c.drawText("ESCENA MENU: " + numEscena, screenWidth /10*3, screenHeight /20*1, pText);
        c.drawRect(HitBoxBotones[0],p);
        c.drawRect(HitBoxBotones[1],p);
        c.drawRect(HitBoxBotones[2],p);

    }

    public void actualizarFisica(){

    }

    public int onTouchEvent(MotionEvent event) {
        int accion = event.getAction();
        int nuevaEscena;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch(accion){
            case MotionEvent.ACTION_DOWN:
                if(HitBoxBotones[0].contains(x,y)) return nuevaEscena = 1;
                if(HitBoxBotones[1].contains(x,y)) return nuevaEscena = 2;
                break;
        }
        return numEscena;
    }

    public void setHitBoxBotones(){
        //Boton jugar
        HitBoxBotones[0] = new Rect((int)(screenWidth/10*2),
                (int)(screenHeight/20*5),
                (int)(screenWidth/10*8),
                (int)(screenHeight/20*6));
        //Boton creditos
        HitBoxBotones[1] = new Rect((int)(screenWidth/10*2),
                (int)(screenHeight/20*7),
                (int)(screenWidth/10*8),
                (int)(screenHeight/20*8));
        //Boton salir
        HitBoxBotones[2] = new Rect((int)(screenWidth/10*2),
                (int)(screenHeight/20*9),
                (int)(screenWidth/10*8),
                (int)(screenHeight/20*10));
    }
}
