package com.example.dartsgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

public class Escena {

    int numEscena;
    Context context;
    int screenWidth,screenHeight;

    public Escena(Context context, int numEscena, int screenWidth, int screenHeight){
        this.context = context;
        this.numEscena = numEscena;
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    public int getNumEscena() {
        return numEscena;
    }

    public int onTouchEvent(MotionEvent event) {
        return numEscena;
    }

    public void actualizarFisica(){
    }

    public void dibujar(Canvas c){
    }
}
