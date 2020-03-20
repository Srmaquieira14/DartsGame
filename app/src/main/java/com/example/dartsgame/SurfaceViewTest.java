package com.example.dartsgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

public class SurfaceViewTest extends SurfaceView implements SurfaceHolder.Callback {
    Escena escenaActual;
    private Context context;
    private SurfaceHolder surfaceHolder;
    private Hilo fisicas;
    private boolean funcionando;
    private int screenWidth;
    private int screenHeight;

    public SurfaceViewTest(Context context){
        super(context);

        this.context = context;
        fisicas = new Hilo();
        setFocusable(true);
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getRealMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;
    }

//    public void actualizarFisica(){
//
//    }
//
//    public void dibujar(Canvas c){
//        c.drawColor(Color.RED);
//    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        fisicas.setFuncionando(true);
        if(fisicas.getState()==Thread.State.NEW)fisicas.start();
        if(fisicas.getState()==Thread.State.TERMINATED){
            fisicas = new Hilo();
            fisicas.start();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int nuevaEscena = escenaActual.onTouchEvent(event);

        if (nuevaEscena != escenaActual.getNumEscena()) {
            switch (nuevaEscena) {
                case 0://MENU
                    escenaActual = new EscenaMenu(context,0, screenWidth, screenHeight);
                    break;
                case 1://JUGAR
                    escenaActual = new EscenaJugar(context,1, screenWidth, screenHeight);
                    break;
                case 2://CREDITOS
                    escenaActual = new EscenaCreditos(context,2, screenWidth, screenHeight);
                    break;
            }
        }
        return true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        //fisicas.setSurfaceSize(screenWidth,screenHeight);
        escenaActual = new EscenaMenu(context,0,screenWidth,screenHeight);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        fisicas.setFuncionando(false);
        try{
            fisicas.join();
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }

    public class Hilo extends Thread{

        public Hilo(){

        }

        public boolean isFuncionando() {
            return funcionando;
        }

        public void setFuncionando(boolean estado) {
            funcionando = estado;
        }

        public void setSurfaceSize(int width, int height){

        }

        @Override
        public void run() {
            while (funcionando) {
                Canvas c = null;
                try {
                    if (!surfaceHolder.getSurface().isValid()) continue;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        c = surfaceHolder.lockHardwareCanvas();
                    } else c = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder) {
                        escenaActual.actualizarFisica();
                        escenaActual.dibujar(c);
                    }
                } finally {
                    if (c != null) {
                        surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }
}

