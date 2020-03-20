package com.example.dartsgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

public class EscenaCreditos extends Escena {

    public Rect[] HitBoxBotones = new Rect[1];//Array por si en un futuro son necesarios mas rects

    public EscenaCreditos(Context context, int numEscena, int screenWidth, int screenHeight) {
        super(context, numEscena, screenWidth, screenHeight);
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
        c.drawText("ESCENA CREDITOS: " + numEscena, screenWidth /10*3, screenHeight /20*1, pText);
        c.drawRect(HitBoxBotones[0],p);
    }

    public void actualizarFisica(){

    }

    public int onTouchEvent(MotionEvent event) {
        int accion = event.getAction();
        int nuevaEscena;
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch(accion) {
            case MotionEvent.ACTION_DOWN:
                if (HitBoxBotones[0].contains(x, y)) return nuevaEscena = 0;
                break;
        }
        return numEscena;
    }

    public void setHitBoxBotones() {
        //Boton volver
        HitBoxBotones[0] = new Rect((int) (screenWidth /10*1),
                (int) (screenHeight /20*1),
                (int) (screenWidth /10*2),
                (int) (screenHeight /20*2));
    }
}
